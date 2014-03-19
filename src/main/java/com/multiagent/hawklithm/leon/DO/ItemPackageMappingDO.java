package com.multiagent.hawklithm.leon.DO;

import java.util.Date;

/**
 * tb_item_package_map器械手术包对应表
 * @author hawklithm
 * 2013-12-27下午8:14:58
 */
public class ItemPackageMappingDO {
	private Integer id;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer itemId;
	private Integer packageId;
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
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
}
