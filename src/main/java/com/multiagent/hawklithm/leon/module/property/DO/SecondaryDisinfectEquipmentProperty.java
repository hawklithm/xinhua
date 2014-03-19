package com.multiagent.hawklithm.leon.module.property.DO;
/**
 * 二次消毒模块属性
 * @author hawklithm
 * 2013-12-28下午5:36:58
 */
public class SecondaryDisinfectEquipmentProperty extends ModuleProperty {
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
		return this.getInfoInStringFormat();
	}
}
