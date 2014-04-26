package com.multiagent.hawklithm.leon.module.property.DO;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;



//属性模块,包括RFID,器械Rfid,包裹的RFID
public abstract class ModuleProperty implements PropertyCollector {
	protected int rfid = (int) (Math.random() * 100000);
	protected Set<ItemInfoDO> itemRFIDs=new HashSet<ItemInfoDO>();
	protected Set<Integer> packageRFIDs=new HashSet<Integer>();
	protected int staffRFID;
//	protected int cubage;
	//具体信息
	protected String detail;
	//生产厂家
	protected String manufacture;
	protected Gson gson = new Gson();

	public void addItem(ItemInfoDO rfid) {
		synchronized (itemRFIDs) {
			getItemRFIDs().add(rfid);
		}
	}

	public void addPackage(Integer rfid) {
		synchronized (packageRFIDs) {
			getPackageRFIDs().add(rfid);
		}
	}

	public boolean removeItem(ItemInfoDO rfid) {
		synchronized (itemRFIDs) {
		return getItemRFIDs().remove(rfid);
		}
	}

	public boolean removePackage(Integer rfid) {
		synchronized (packageRFIDs) {
		return getPackageRFIDs().remove(rfid);
		}
	}

	public Set<Integer> getPackageRFIDs() {
		return packageRFIDs;
	}

	public void setPackageRFIDs(Set<Integer> packageRFIDs) {
		this.packageRFIDs = packageRFIDs;
	}

	public int getRfid() {
		return rfid;
	}

	public void setRfid(int rfid) {
		this.rfid = rfid;
	}

	public Set<ItemInfoDO> getItemRFIDs() {
		return itemRFIDs;
	}

	public void setItemRFIDs(Set<ItemInfoDO> itemRFIDs) {
		this.itemRFIDs = itemRFIDs;
	}

	public int getStaffRFID() {
		return staffRFID;
	}

	public void setStaffRFID(int staffRFID) {
		this.staffRFID = staffRFID;
	}
//
//	public int getCubage() {
//		return cubage;
//	}
//
//	public void setCubage(int cubage) {
//		this.cubage = cubage;
//	}

	protected String getInfoInStringFormat() {
//		String msg = "module RFID: " + this.getRfid() + ", staffRFID: " + this.getStaffRFID()
//				+ ", Cubage: " + this.getCubage();
//		if (!CollectionUtils.isEmpty(getItemRFIDs())) {
//			msg += ", item amount: " + this.getItemRFIDs().size();
//			msg += ", item RFID: " + gson.toJson(this.getItemRFIDs());
//		}
//		if (!CollectionUtils.isEmpty(getPackageRFIDs())) {
//			msg += ", package amount: ";
//			msg += ", package RFID: " + gson.toJson(this.getPackageRFIDs());
//		}
//		return msg;
		return detail;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
