package com.multiagent.hawklithm.leon.module.property.DO;

import com.hawklithm.utils.Jsoner;

/**
 * ��ϴ����ģ������
 * @author hawklithm
 * 2013-12-28����5:35:52
 */
public class CleanAndDisinfectEquipmentProperty extends ModuleProperty {
	private String disinfectantName;
	private String concentration;
	public String getDisinfectantName() {
		return disinfectantName;
	}
	public void setDisinfectantName(String disinfectantName) {
		this.disinfectantName = disinfectantName;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	@Override
	public String getProperty() {
//		return this.getInfoInStringFormat();
		return Jsoner.toJson(this);
	}
}
