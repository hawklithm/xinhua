package com.multiagent.hawklithm.davinci.eventDao;

public interface IRFIDDataFromReaderDAO {
	public boolean submitRFIDData(long RFID,String type,String detail);
}
