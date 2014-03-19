package com.multiagent.hawklithm.sephiroth.DO;

/**
 * 子订单临时数据信息
 * @author hawklithm
 *
 */
public class TempSubPackageOrderDO {
	private int parentId;
	private int packageType;
	private int packageAmount;
	public int getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(int packageAmount) {
		this.packageAmount = packageAmount;
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
