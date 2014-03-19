package com.multiagent.hawklithm.leon.DO;

import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;


public class RFIDDataPac {
	private SqlRFIDOriginalObject originalInfo;
	private int equipmentId;
	private int cameraId;
	private int staffId;
	public SqlRFIDOriginalObject getOriginalInfo() {
		return originalInfo;
	}
	public void setOriginalInfo(SqlRFIDOriginalObject originalInfo) {
		this.originalInfo = originalInfo;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public int getCameraId() {
		return cameraId;
	}
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
}
