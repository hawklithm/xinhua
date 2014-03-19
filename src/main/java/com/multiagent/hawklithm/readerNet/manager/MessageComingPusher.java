package com.multiagent.hawklithm.readerNet.manager;

import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

/**
 * "读卡器数据已收到"消息推送
 * @author hawklithm
 *
 */
public class MessageComingPusher implements WardenOperator<WardenMessage>{

	private WardenManager wardenManager;
	@Override
	public void sendOutMessage(WardenMessage message) {
		// TODO 将消息发送到消息中心使得有监听的模块能够接收到
		wardenManager.push(message);
	}
	public WardenManager getWardenManager() {
		return wardenManager;
	}
	public void setWardenManager(WardenManager wardenManager) {
		this.wardenManager = wardenManager;
	}

}
