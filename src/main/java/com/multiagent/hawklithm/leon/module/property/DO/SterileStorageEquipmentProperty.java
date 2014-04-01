package com.multiagent.hawklithm.leon.module.property.DO;

import com.hawklithm.utils.Jsoner;

/**
 * 无菌储藏模块属性
 * @author hawklithm
 * 2013-12-28下午5:37:27
 */
public class SterileStorageEquipmentProperty extends ModuleProperty {
	@Override
	public String getProperty() {
		return Jsoner.toJson(this);
	}
}
