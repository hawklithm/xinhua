package com.multiagent.hawklithm.readerNet.DO;

/**
 * ������ԭʼ���ݷ�װ
 * @author hawklithm
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
