package com.multiagent.hawklithm.davinci.eventDao;
/*
 * �Ӷ�ȡ���ж�ȡ�����ݣ�
 * �����������ݲ������ݿ�
 * RFID����id,���ͣ���ϸ��Ϣ
 */
public interface IRFIDDataFromReaderDAO {
	public boolean submitRFIDData(long RFID,String type,String detail);
}
