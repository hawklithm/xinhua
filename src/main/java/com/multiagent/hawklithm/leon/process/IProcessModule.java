package com.multiagent.hawklithm.leon.process;

import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;

public interface IProcessModule {
	/**
	 * 获取模块名称
	 * @return 模块名称
	 */
	String getName();
	/**
	 * 根据设备RFID从过程模块中获取设备
	 * @param id 设备RFID
	 * @return
	 * @throws EquipmentNotFoundException
	 */
	EquipmentObject<?> getEquipmentByRFID(int id) throws EquipmentNotFoundException;
	<T extends ModuleProperty> List<T>   doGetEquipmentSummaryInfo();
	List<? extends EquipmentObject<?>> getEquipmentList();
	List<ChangerAnnouncerProperty> getBufferedPropertyList();
}
