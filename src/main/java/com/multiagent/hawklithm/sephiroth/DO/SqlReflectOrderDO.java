package com.multiagent.hawklithm.sephiroth.DO;

import java.util.Date;
/**
 * ������ tb_reflect_order
reflet_order_id				int
gmt_create					date				���������
gmt_modified				date				�����޸�����
Parent_id						int					������ID
item_type						int					��е��������
item_amount					int					��е����
 * @author hawklithm
 *
 */
public class SqlReflectOrderDO {
	private int reflectOrderId;
	private Date gmtCreate;
	private Date gmtModified;
	private int parentId;
	private int itemType;
	private int itemAmount;
	public int getReflectOrderId() {
		return reflectOrderId;
	}
	public void setReflectOrderId(int reflectOrderId) {
		this.reflectOrderId = reflectOrderId;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public int getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
}
