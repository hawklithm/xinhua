package com.multiagent.hawklithm.leon.module.property.DO;

import com.hawklithm.utils.Jsoner;

/**
 * ¶Á¿¨Æ÷Ä£¿éÊôĞÔ
 * @author hawklithm
 * 2013-12-28ÏÂÎç5:36:36
 */
public class ReaderEquipmentProperty extends ModuleProperty{
	
	private int targetRFID = 0;
	private String targetMessageKind = "";
	private String targetKind = "";
	private String targetMessageDir = "";
	@Override
	public String getProperty() {
		return Jsoner.toJson(this);
	}
	public int getTargetRFID() {
		return targetRFID;
	}
	public void setTargetRFID(int targetRFID) {
		this.targetRFID = targetRFID;
	}
	public String getTargetMessageKind() {
		return targetMessageKind;
	}
	public void setTargetMessageKind(String targetMessageKind) {
		this.targetMessageKind = targetMessageKind;
	}
	public String getTargetKind() {
		return targetKind;
	}
	public void setTargetKind(String targetKind) {
		this.targetKind = targetKind;
	}
	public String getTargetMessageDir() {
		return targetMessageDir;
	}
	public void setTargetMessageDir(String targetMessageDir) {
		this.targetMessageDir = targetMessageDir;
	}
}
