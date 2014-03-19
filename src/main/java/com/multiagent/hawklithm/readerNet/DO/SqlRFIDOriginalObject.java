package com.multiagent.hawklithm.readerNet.DO;

import java.util.Date;
/**
 *读卡器原始数据 
 * @author hawklithm
 *
 */
public class SqlRFIDOriginalObject {
	private int rfid;
	private int readerId;
	private Date date;
	public static int[] getRfids(SqlRFIDOriginalObject[] objects){
		int[] result=new int[objects.length];
		for (int i=0;i<objects.length;i++){
			result[i]=objects[i].getRfid();
		}
		return result;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	public int getRfid() {
		return rfid;
	}
	public void setRfid(int rfid) {
		this.rfid = rfid;
	}
}
