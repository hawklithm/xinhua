package com.multiagent.hawklithm.leon.module.property.DO;

import com.hawklithm.utils.Jsoner;

/**
 * 打包模块属性
 * @author hawklithm
 * 2013-12-28下午5:36:11
 */
public class PackagingEquipmentProperty extends ModuleProperty{

	@Override
	public String getProperty() {
		return Jsoner.toJson(this);
	}

}
