package com.multiagent.hawklithm.leon.process;

import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;

public interface IProcessModule {
	/**
	 * ��ȡģ������
	 * @return ģ������
	 */
	String getName();
	/**
	 * �����豸RFID�ӹ���ģ���л�ȡ�豸
	 * @param id �豸RFID
	 * @return
	 * @throws EquipmentNotFoundException
	 */
	EquipmentObject<?> getEquipmentByRFID(int id) throws EquipmentNotFoundException;
	<T extends ModuleProperty> List<T>   doGetEquipmentSummaryInfo();
	List<? extends EquipmentObject<?>> getEquipmentList();
	List<ChangerAnnouncerProperty> getBufferedPropertyList();
}
