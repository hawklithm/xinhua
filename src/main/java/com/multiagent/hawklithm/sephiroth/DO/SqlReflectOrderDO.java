package com.multiagent.hawklithm.sephiroth.DO;

import java.util.Date;
/**
 * 返件单 tb_reflect_order
reflet_order_id				int
gmt_create					date				表项创建日期
gmt_modified				date				表项修改日期
Parent_id						int					父订单ID
item_type						int					器械类型类型
item_amount					int					器械数量
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
