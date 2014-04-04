package com.multiagent.hawklithm.leon.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.module.SimpleMessageTransportBufferModule.SimpleMessage;
import com.multiagent.hawklithm.machineInfo.DO.MachineInfoDO;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

public class SimpleMessageTransportBufferModule implements Module<List<SimpleMessageTransportBufferModule.SimpleMessage>>, WardenOperator<SimpleMessage>{
	
	public class SimpleMessage{
		private Date timeStamp;
		private String message;
		public SimpleMessage() {
			
		}
		public SimpleMessage(Date time,String message){
			this.timeStamp=time;
			this.message=message;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Date getTimeStamp() {
			return timeStamp;
		}
		public void setTimeStamp(Date timeStamp) {
			this.timeStamp = timeStamp;
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


	@Override
	public void sendOutMessage(SimpleMessage message) {
		// TODO 完成推送消息发送功能，这里需要把message封装到WardenMessage里面
		
	}


}
