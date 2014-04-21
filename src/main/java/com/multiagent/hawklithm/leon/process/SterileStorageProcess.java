package com.multiagent.hawklithm.leon.process;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.module.Gate;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;
import com.multiagent.hawklithm.leon.module.property.DO.SterileStorageEquipmentProperty;

/**
 * 分拣过程模块
 * 
 * @author hawklithm 2013-12-26下午4:12:11
 */
public class SterileStorageProcess implements IProcessModule {

	private static String DefaultName = "SterileStorageProcess";
	private String name = DefaultName;
	private List<EquipmentObject<? extends ModuleProperty>> equipmentList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();
	private List<EquipmentObject<? extends ModuleProperty>> readerList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public EquipmentObject<SterileStorageEquipmentProperty> getEquipmentByRFID(int id) throws EquipmentNotFoundException {
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			if (object.getRfid() == id) {
				return (EquipmentObject<SterileStorageEquipmentProperty>) object;
			}
		}
		throw new EquipmentNotFoundException();
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public List<SterileStorageEquipmentProperty> doGetEquipmentSummaryInfo() {
		List<SterileStorageEquipmentProperty> ret = new ArrayList<SterileStorageEquipmentProperty>();
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			ret.add((SterileStorageEquipmentProperty) object.doGetEquipmentSummaryInfo());
		}
		return ret;
	}

	public List<EquipmentObject<? extends ModuleProperty>> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentObject<? extends ModuleProperty>> equipmentList) {
		this.equipmentList = equipmentList;
	}
	
	@Override
	public List<ChangerAnnouncerProperty> getBufferedPropertyList() {
		List<ChangerAnnouncerProperty> ret = new ArrayList<ChangerAnnouncerProperty>();
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			ChangerAnnouncerProperty property=object.getBufferedProperty();
			if(object instanceof Gate){
				property.setSourceType(Gate.GATE_TAG);
			}
			ret.add(property);
		}
		return ret;
	}

	public List<EquipmentObject<? extends ModuleProperty>> getReaderList() {
		return readerList;
	}

	public void setReaderList(List<EquipmentObject<? extends ModuleProperty>> readerList) {
		this.readerList = readerList;
	}

}
