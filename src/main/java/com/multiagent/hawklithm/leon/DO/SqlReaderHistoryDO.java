package com.multiagent.hawklithm.leon.DO;

import java.util.Date;

/**
 * ��������ˮ��tb_reader_history
 * id						int	
 * time					date				��Ϣ����ʱ��
 * gmt_create			date				�����ʱ��
 * gmt_modified		date				�����޸�ʱ��
 * RFID					int					��ȡ����RFID
 * reader_id			int					������id
 * state					int					�ֶ�״̬
 * 
 * 
 * @author hawklithm
 *
 */
public class SqlReaderHistoryDO {
	private Integer id;
	private Date time;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer rfid;
	private Integer readerId;
	private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Integer getRfid() {
		return rfid;
	}
	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}
	public Integer getReaderId() {
		return readerId;
	}
	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
