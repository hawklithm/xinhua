package com.multiagent.hawklithm.davinci.eventDo;

public enum RFIDEventType {
	ItemEvent,    //手术器械
	EquipmentEvent,   //设备事件
	PackageEvent;        //手术包事件
	private RFIDEventType type;

	public RFIDEventType getType() {
		return type;
	}

	public void setType(RFIDEventType type) {
		this.type = type;
	}
}
