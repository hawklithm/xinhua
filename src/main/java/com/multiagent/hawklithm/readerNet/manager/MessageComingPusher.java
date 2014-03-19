package com.multiagent.hawklithm.readerNet.manager;

import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

/**
 * "�������������յ�"��Ϣ����
 * @author hawklithm
 *
 */
public class MessageComingPusher implements WardenOperator<WardenMessage>{

	private WardenManager wardenManager;
	@Override
	public void sendOutMessage(WardenMessage message) {
		// TODO ����Ϣ���͵���Ϣ����ʹ���м�����ģ���ܹ����յ�
		wardenManager.push(message);
	}
	public WardenManager getWardenManager() {
		return wardenManager;
	}
	public void setWardenManager(WardenManager wardenManager) {
		this.wardenManager = wardenManager;
	}

}
