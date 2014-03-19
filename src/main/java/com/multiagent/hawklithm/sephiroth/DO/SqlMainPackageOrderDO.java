package com.multiagent.hawklithm.sephiroth.DO;

import java.util.Date;
/**
 * ������������ tb_order
Order_id						int	
gmt_create					date				���������
gmt_modified				date				�����޸�����
User_id							int					�µ��û�ID
order_time						Date				�µ�ʱ��
order_status					varchar			����״̬,��Ӧ�����ļ��в���
expect_receive_time		date				�����ջ�ʱ��
level								int					�������ȼ�
order_note					varchar			��ע
 * @author hawklithm
 *
 */
public class SqlMainPackageOrderDO {
	private Integer orderId;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer UserId;
	private Date orderTime;
	private String orderStatus;
	private Date expectReceiveTime;
	private Integer level;
	private String orderNote;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getExpectReceiveTime() {
		return expectReceiveTime;
	}
	public void setExpectReceiveTime(Date expectReceiveTime) {
		this.expectReceiveTime = expectReceiveTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
}
