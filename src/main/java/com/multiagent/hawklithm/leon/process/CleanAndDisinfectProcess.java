package com.multiagent.hawklithm.leon.process;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.module.Gate;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.CleanAndDisinfectEquipmentProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;

/**
 * 分拣过程模块
 * 清洗消毒模块
 * 
 * 每一个清洗消毒流程都有一个equipmentList保存的是设备列表、readerList保存的是读卡器列表
 * 几乎每个都是进来的门、第一个设备、第二个设备
 * 读卡器列表是七个读卡器
 * 
 * @author hawklithm 2013-12-26下午4:12:11
 */
public class CleanAndDisinfectProcess implements IProcessModule {

	private static String DefaultName = "Clean&DisinfectProcess";
	private String name = DefaultName;
/*
 * 在spring-module-clean-and-disinfect.xml里面注入equipmentList的值
 * equipmentList包括清洗消毒门、清洗消毒设备一、清洗消毒设备二
 * 和readerList的值
 */
	private List<EquipmentObject<? extends ModuleProperty>> equipmentList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();
	private List<EquipmentObject<? extends ModuleProperty>> readerList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public EquipmentObject<? extends ModuleProperty> getEquipmentByRFID(int id) throws EquipmentNotFoundException {
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			if (object.getRfid() == id) {
				return object;
			}
		}
		throw new EquipmentNotFoundException();
	}

	public void setName(String name) {
		this.name = name;
	}

/*
 * 查询得到相应的EquipmentObject(non-Javadoc)
 * @see com.multiagent.hawklithm.leon.process.IProcessModule#doGetEquipmentSummaryInfo()
 */
	@Override
	public List<? extends ModuleProperty> doGetEquipmentSummaryInfo() {
		List<CleanAndDisinfectEquipmentProperty> ret = new ArrayList<CleanAndDisinfectEquipmentProperty>();
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			ret.add((CleanAndDisinfectEquipmentProperty) object.doGetEquipmentSummaryInfo());
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
