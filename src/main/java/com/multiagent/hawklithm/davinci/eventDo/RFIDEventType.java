package com.multiagent.hawklithm.davinci.eventDo;

public enum RFIDEventType {
	ItemEvent,
	EquipmentEvent,
	PackageEvent;
	private RFIDEventType type;

	public RFIDEventType getType() {
		return type;
	}

	public void setType(RFIDEventType type) {
		this.type = type;
	}
}
