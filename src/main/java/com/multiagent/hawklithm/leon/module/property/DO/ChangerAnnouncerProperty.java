package com.multiagent.hawklithm.leon.module.property.DO;

import java.util.ArrayList;
import java.util.List;

public class ChangerAnnouncerProperty extends ModuleProperty{

	private List<Integer> itemAdd=new ArrayList<Integer>();
	private List<Integer> itemRemove=new ArrayList<Integer>();
	private List<Integer> packageAdd=new ArrayList<Integer>();
	private List<Integer> packageRmove=new ArrayList<Integer>();
	
	public ChangerAnnouncerProperty(){}
	public ChangerAnnouncerProperty(int rfid,int staffRfid){
		this.staffRFID=staffRfid;
		this.rfid=rfid;
	}
	public ChangerAnnouncerProperty(ModuleProperty property){
		this.staffRFID=property.getStaffRFID();
		this.rfid=property.getRfid();
	}
	
	@Override
	public String getProperty() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addItem(int rfid){
		itemAdd.add(rfid);
	}
	public void removeItem(int rfid){
		itemRemove.add(rfid);
	}
	public void addPackage(int rfid){
		packageAdd.add(rfid);
	}
	public void removePackage(int rfid){
		packageRmove.add(rfid);
	}
	public List<Integer> getItemAdd() {
		return itemAdd;
	}
	public void setItemAdd(List<Integer> itemAdd) {
		this.itemAdd = itemAdd;
	}
	public List<Integer> getItemRemove() {
		return itemRemove;
	}
	public void setItemRemove(List<Integer> itemRemove) {
		this.itemRemove = itemRemove;
	}
	public List<Integer> getPackageAdd() {
		return packageAdd;
	}
	public void setPackageAdd(List<Integer> packageAdd) {
		this.packageAdd = packageAdd;
	}
	public List<Integer> getPackageRmove() {
		return packageRmove;
	}
	public void setPackageRmove(List<Integer> packageRmove) {
		this.packageRmove = packageRmove;
	}

}
