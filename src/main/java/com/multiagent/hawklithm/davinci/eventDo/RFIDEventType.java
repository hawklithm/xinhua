package com.multiagent.hawklithm.davinci.eventDo;

public enum RFIDEventType {
	ItemEvent,    //������е
	EquipmentEvent,   //�豸�¼�
	PackageEvent;        //�������¼�
	private RFIDEventType type;

	public RFIDEventType getType() {
		return type;
	}

	public void setType(RFIDEventType type) {
		this.type = type;
	}
}
