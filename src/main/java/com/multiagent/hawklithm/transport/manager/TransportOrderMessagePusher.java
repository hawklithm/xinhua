package com.multiagent.hawklithm.transport.manager;

import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

public class TransportOrderMessagePusher implements WardenOperator<Integer>{

	private WardenManager wardenManager;
	/**
	 * ���͵���Ϣ����{@link WardenMessage#KIND_ORDER_READY_FOR_TRANSPORT}+{@link WardenMessage#DIR_ENTER}
	 * ��ϢĿ��{@link WardenMessage#TARGET_TYPE_SYSTEM_MODULE}
	 * ��Ϣ����ֻ�ж���ID
	 */
	@Override
	public void sendOutMessage(Integer id) {
		// TODO Auto-generated method stub
		WardenMessage message =new WardenMessage();
		message.setKind(WardenMessage.KIND_ORDER_READY_FOR_TRANSPORT+WardenMessage.DIR_ENTER);
		message.setTarget(WardenMessage.TARGET_TYPE_SYSTEM_MODULE);
		message.setNote(String.valueOf(id));
		wardenManager.push(message);
	}
	public WardenManager getWardenManager() {
		return wardenManager;
	}
	public void setWardenManager(WardenManager wardenManager) {
		this.wardenManager = wardenManager;
	}

}
