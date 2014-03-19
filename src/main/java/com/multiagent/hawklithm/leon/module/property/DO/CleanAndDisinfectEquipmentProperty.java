package com.multiagent.hawklithm.leon.module.property.DO;
/**
 * 清洗消毒模块属性
 * @author hawklithm
 * 2013-12-28下午5:35:52
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
		// TODO Auto-generated method stub
		return this.getInfoInStringFormat();
	}
}
