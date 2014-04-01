package com.multiagent.hawklithm.leon.module.property.DO;

import com.hawklithm.utils.Jsoner;

/**
 * 分拣模块属性
 * @author hawklithm
 * 2013-12-28下午5:37:12
 */
public class SortingEquipmentProperty extends ModuleProperty{
	@Override
	public String getProperty() {
		return Jsoner.toJson(this);
	}
}
