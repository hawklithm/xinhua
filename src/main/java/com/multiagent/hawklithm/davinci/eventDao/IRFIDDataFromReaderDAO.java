package com.multiagent.hawklithm.davinci.eventDao;
/*
 * 从读取卡中读取的数据，
 * 将读来的数据插入数据库
 * RFID包括id,类型，详细消息
 */
public interface IRFIDDataFromReaderDAO {
	public boolean submitRFIDData(long RFID,String type,String detail);
}
