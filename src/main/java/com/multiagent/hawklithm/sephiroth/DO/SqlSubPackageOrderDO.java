package com.multiagent.hawklithm.sephiroth.DO;

import java.util.Date;
/**
 * �������Ӷ��� tb_sub_order
sub_order_id					int
gmt_create					date				���������
gmt_modified				date				�����޸�����
Parent_id						int					������ID
package_type				int					ҽ�ư�����
package_amount			int					ҽ�ư�����
 * @author hawklithm
 *
 */
public class SqlSubPackageOrderDO {
	private int subOrderId;
	private Date gmtCreate;
	private Date gmtModified;
	private int parentId;
	private int packageType;
	private int packageAmount;
	public int getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(int packageAmount) {
		this.packageAmount = packageAmount;
	}
	public int getSubOrderId() {
		return subOrderId;
	}
	public void setSubOrderId(int subOrderId) {
		this.subOrderId = subOrderId;
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
	public int getPackageType() {
		return packageType;
	}
	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}
	
}
