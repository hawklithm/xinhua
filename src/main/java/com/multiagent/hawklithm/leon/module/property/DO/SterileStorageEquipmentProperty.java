package com.multiagent.hawklithm.leon.module.property.DO;

import com.hawklithm.utils.Jsoner;

/**
 * �޾�����ģ������
 * @author hawklithm
 * 2013-12-28����5:37:27
 */
public class SterileStorageEquipmentProperty extends ModuleProperty {
	@Override
	public String getProperty() {
		return Jsoner.toJson(this);
	}
}
