package com.multiagent.hawklithm.transport.DO;

import java.util.Date;
/**
 * 
Id							int
gmt_create			date				���������
gmt_modified		date				�����޸�����
order_id				int					����ID
Start_time				date				����ʱ��
Finish_time				date				����ʱ��
Start_address			varchar			�����ص�
End_address			varchar			�ջ��ص�
 * @author hawklithm
 * 2013-12-18����4:49:44
 */
public class SqlTransportMainOrderDO {
	private Integer id;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer orderId;
	private Date startTime;
	private Date finishTime;
	private String startAddress;
	private String endAddress;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
}
