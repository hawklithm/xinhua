package com.multiagent.hawklithm.shadowsong.manager;

import java.util.List;

import com.multiagent.hawklithm.leon.module.SimpleMessageTransportBufferModule;
import com.multiagent.hawklithm.leon.module.SimpleMessageTransportBufferModule.SimpleMessage;
import com.multiagent.hawklithm.machineInfo.DO.MachineInfoDO;
import com.multiagent.hawklithm.shadowsong.manager.interface4rpc.RPCAnnouncementManager;

public class AnnouncementManager implements RPCAnnouncementManager{
	private SimpleMessageTransportBufferModule simpleMessageTransportBufferModule;
	
	@Override
	public SimpleMessage[] getAnnoucement(){
		List<SimpleMessage> list=simpleMessageTransportBufferModule.doGetModuleSummaryInfo();
		return list.toArray(new SimpleMessage[list.size()]);
	}
	
	@Override
	public void sendOutMachineInfoChangeAnnouncement(MachineInfoDO machineInfo){
		simpleMessageTransportBufferModule.sendOutMessage(machineInfo);
	}

	public SimpleMessageTransportBufferModule getSimpleMessageTransportBufferModule() {
		return simpleMessageTransportBufferModule;
	}

	public void setSimpleMessageTransportBufferModule(
			SimpleMessageTransportBufferModule simpleMessageTransportBufferModule) {
		this.simpleMessageTransportBufferModule = simpleMessageTransportBufferModule;
	}
}
