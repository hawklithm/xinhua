package com.multiagent.hawklithm.itempackmapping.dataobject;

import java.util.Date;

/**
 * 
 * @author hawklithm
 * 2014-5-19ÏÂÎç5:03:48
 */
public class ItemPackMappingInfoDO {
	private Integer id;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer itemId;
	private Date packageId;
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
	public Date getPackageId() {
		return packageId;
	}
	public void setPackageId(Date packageId) {
		this.packageId = packageId;
	}
}
