package com.multiagent.hawklithm.leon.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.gson.Gson;
import com.multiagent.hawklithm.machineInfo.DO.MachineInfoDO;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

public class SimpleMessageTransportBufferModule implements Module<List<SimpleMessageTransportBufferModule.SimpleMessage>>, WardenOperator<WardenMessage>{
	
	public class SimpleMessage{
		private Date time;
		private String message;
		public SimpleMessage() {
			
		}
		public SimpleMessage(Date time,String message){
			this.time=time;
			this.message=message;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	private Gson gson=new Gson();
	private WardenManager wardenManager;

	private ConcurrentLinkedQueue<SimpleMessage> buffer=new ConcurrentLinkedQueue<SimpleMessage>();
	
	public void initBuffer(){
		this.registWarden(new Warden(WardenMessage.TARGET_TYPE_SYSTEM_MODULE,WardenMessage.KIND_MACHINE_INFO_CHANGE){

			@Override
			public void asynchronizedProcess(String message) {
				MachineInfoDO machineInfo=gson.fromJson(message, MachineInfoDO.class);
				addSimpleMessage(new SimpleMessage(machineInfo.getGmtModified(),"设备参数修改"+machineInfo.getDetail()));
			}
			
		});
	}


	@Override
	public List<SimpleMessage> doGetModuleSummaryInfo() {
		List ans=new ArrayList<SimpleMessage>(buffer.size());
		while(!buffer.isEmpty()){
			ans.add(buffer.poll());
		}
		return ans;
	}
	
	public  void addSimpleMessage(SimpleMessage message){
		buffer.add(message);
	}

	public void sendOutMessage(WardenMessage message) {
		wardenManager.push(message);
	}

	public void registWarden(Warden warden) {
		wardenManager.regist(warden);
	}
	
	public boolean isEmpty(){
		return buffer.isEmpty();
	}


	public WardenManager getWardenManager() {
		return wardenManager;
	}

	public void setWardenManager(WardenManager wardenManager) {
		this.wardenManager = wardenManager;
	}


}
