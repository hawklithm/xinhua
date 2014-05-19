package com.multiagent.hawklithm.readerNet.DO;

/**
 * 读卡器原始数据封装
 * @author hawklithm
 *
 *SqlRFIDOriginalObject表示RFID消息的bean
 *targets表示目标RFID
 *
 */
public class RFIDOriginalInfos {
	private SqlRFIDOriginalObject infos[];
	private String targets[];

	public SqlRFIDOriginalObject[] getInfos() {
		return infos;
	}

	public void setInfos(SqlRFIDOriginalObject[] infos) {
		this.infos = infos;
	}

	public String[] getTargets() {
		return targets;
	}

	public void setTargets(String[] targets) {
		this.targets = targets;
	}

}
