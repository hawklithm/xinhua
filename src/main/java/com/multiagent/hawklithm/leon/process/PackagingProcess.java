package com.multiagent.hawklithm.leon.process;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.module.Gate;
import com.multiagent.hawklithm.leon.module.PackagingEquipmentModule;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PackagingEquipmentProperty;

public class PackagingProcess implements IProcessModule {
//	private int leastNumber;
//	private static int MaxNumber = 2000;
//	private static String ITEM_STATUS_UNWASHED = "item_unwashed";
//	private static String ITEM_ALL_WASHED = "item_all_washed";
	private static String DefaultName = "PackagingProcess";
	private String name = DefaultName;
	private List<EquipmentObject<? extends ModuleProperty>> equipmentList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();
	private List<EquipmentObject<? extends ModuleProperty>> readerList = new ArrayList<EquipmentObject<? extends ModuleProperty>>();
//	private Message message;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PackagingEquipmentModule getEquipmentByRFID(int id) throws EquipmentNotFoundException {
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			if (object.getRfid() == id) {
				return (PackagingEquipmentModule) object;
			}
		}
		throw new EquipmentNotFoundException();
	}

	/**
	 * 打包之前先进行器械的清洗检查 全部器械清洗完毕则返回成功信息 有未清洗完毕的器械则返回该器械的id信息
	 */
	// public WardenMessage ItemStatusListener(List<CleaningEquipmentModule>
	// cleaningEquipmentList){
	// WardenMessage message = new WardenMessage();
	// for(CleaningEquipmentModule object : cleaningEquipmentList){
	// if(object.GetItemStatus() == ITEM_STATUS_UNWASHED){
	// message.setNote(object.getRfid() + ITEM_STATUS_UNWASHED);
	// }
	// else{
	// message.setNote(ITEM_ALL_WASHED);
	// }
	// }
	// return message;
	// }
//
//	public PackagingEquipmentModule scheduling() {
//		PackagingEquipmentModule optimumObject = null;
//		leastNumber = MaxNumber;
//		for (PackagingEquipmentModule object : packagingEquipmentList) {
//			if (object.getItemNumber() < leastNumber) {
//				leastNumber = object.getItemNumber();
//				optimumObject = object;
//			}
//		}
//		return optimumObject;
//	}

//	/**
//	 * 打包过程的信息查询
//	 */
//	public Message FindPackageByItemId(int id) {
//		message = new Message();
//		message.setKind("find_packagae_by_item_id");
//		message.setTarget("target_type_system_module");
//		for (PackagingEquipmentModule object : packagingEquipmentList) {
//			// 没想好每个器械怎么和它所在的手术包对应上
//			message.setNote(String.valueOf(object.getPackageId()));
//		}
//		return message;
//	}

//	public Message GetPackagingHistoryBetweenPeriod(Date beginTime, Date endTime) {
//		message = new Message();
//		message.setKind("get_packaging_history_between_period");
//		message.setTarget("target_type_system_module");
//
//		return message;
//	}

	@Override
	public List<PackagingEquipmentProperty> doGetEquipmentSummaryInfo() {
		List<PackagingEquipmentProperty> ret = new ArrayList<PackagingEquipmentProperty>();
		for (EquipmentObject<? extends ModuleProperty> object : equipmentList) {
			PackagingEquipmentProperty property = (PackagingEquipmentProperty) object.doGetEquipmentSummaryInfo();
			ret.add(property);
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
