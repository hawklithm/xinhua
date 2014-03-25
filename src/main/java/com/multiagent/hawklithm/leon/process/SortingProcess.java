package com.multiagent.hawklithm.leon.process;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;
import com.multiagent.hawklithm.leon.module.property.DO.SortingEquipmentProperty;

/**
 * 分拣过程模块
 * 
 * @author hawklithm 2013-12-26下午4:12:11
 */
public class SortingProcess implements IProcessModule {

	private static String DefaultName = "SortingProcess";
	private String name = DefaultName;
	private List<EquipmentObject<? extends ModuleProperty>> equipmentList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();
	private List<EquipmentObject<? extends ModuleProperty>> readerList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public EquipmentObject<SortingEquipmentProperty> getEquipmentByRFID(int id) throws EquipmentNotFoundException {
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			if (object.getRfid() == id) {
				return (EquipmentObject<SortingEquipmentProperty>) object;
			}
		}
		throw new EquipmentNotFoundException();
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public  List<SortingEquipmentProperty> doGetEquipmentSummaryInfo() {
		List<SortingEquipmentProperty> ret=new ArrayList<SortingEquipmentProperty>();
		for (EquipmentObject<? extends ModuleProperty> object:equipmentList){
			ret.add((SortingEquipmentProperty) object.doGetEquipmentSummaryInfo());
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
			ret.add(object.getBufferedProperty());
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
