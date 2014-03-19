package com.multiagent.hawklithm.leon.DO;

import java.util.Date;

/**
 * 读卡器流水表tb_reader_history
 * id						int	
 * time					date				信息产生时间
 * gmt_create			date				表项创建时间
 * gmt_modified		date				表项修改时间
 * RFID					int					读取到的RFID
 * reader_id			int					读卡器id
 * state					int					字段状态
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
