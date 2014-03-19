package com.multiagent.hawklithm.leon.module;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.manager.RFIDMachineFlowRecordManager;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.CleanAndDisinfectEquipmentProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

/**
 * 清洗消毒设备模块
 * 
 * @author hawklithm 2013-12-26下午4:11:43
 */
public class CleanAndDisinfectEquipmentModule extends EquipmentObject<CleanAndDisinfectEquipmentProperty> {

	private Gson gson = new Gson();
	private RFIDMachineFlowRecordManager rfidManager;
	/**
	 * RFID清洗消毒进入状态
	 */
	private String cadEnter="进入清洗消毒设备";
	/**
	 * RFID清洗消毒退出状态
	 */
	private String cadExit="离开清洗消毒设备";

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				addItem(SqlRFIDOriginalObject.getRfids(rfidObject));
				sendDataToDataBase(rfidObject,cadEnter);
				System.out.println("清洗消毒设备模块接收到进入数据，并存储到缓存中: "+gson.toJson(propertyBuffer));
			}

		});
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_EXIT) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				removeItem(SqlRFIDOriginalObject.getRfids(rfidObject));
				sendDataToDataBase(rfidObject,cadExit);
				System.out.println("清洗消毒设备模块接收到离开数据，并存储到缓存中: "+gson.toJson(propertyBuffer));
			}

		});
		propertyBuffer.init(getRfid(), getStaffRFID());
	}

	/**
	 * 从设备， 摄像头，手术包对应表中获取相关信息
	 */
	public SqlEquipCamReaderMappingDO getRelateInfo() {
		return rfidManager.getMappingDataByEquipmentId(getRfid());
	}

	public void sendDataToDataBase(SqlRFIDOriginalObject[] infos,String status) {
		for (int i = 0; i < infos.length; i++) {
			sendDataToDataBase(infos[i],status);
		}
	}

	public void sendDataToDataBase(SqlRFIDOriginalObject info,String status) {
		RFIDDataPac dataPac = new RFIDDataPac();
		dataPac.setEquipmentId(this.getRfid());
		dataPac.setStaffId(this.getStaffRFID());
		dataPac.setOriginalInfo(info);
		rfidManager.storeItemIntoDataBase(dataPac);
		
		rfidManager.storeItemHistoryToDataBase(info.getDate(), info.getRfid(), info.getReaderId(), null, status, moduleProperty.getRfid());
	}

	@Override
	public CleanAndDisinfectEquipmentProperty doGetEquipmentSummaryInfo() {
		return moduleProperty;
	}

	@Override
	public void doSetEquipmentParameter(CleanAndDisinfectEquipmentProperty parameter) {
		setModuleProperty(parameter);
	}

	@Override
	public PropertyCollector doGetEquipmentSummaryInfoUnifiedInterface() {
		return doGetEquipmentSummaryInfo();
	}

	public RFIDMachineFlowRecordManager getRfidManager() {
		return rfidManager;
	}

	public void setRfidManager(RFIDMachineFlowRecordManager rfidManager) {
		this.rfidManager = rfidManager;
	}

	public String getDisinfectantName() {
		return moduleProperty.getDisinfectantName();
	}

	public void setDisinfectantName(String disinfectantName) {
		moduleProperty.setDisinfectantName(disinfectantName);
	}

	public String getConcentration() {
		return moduleProperty.getConcentration();
	}

	public void setConcentration(String concentration) {
		moduleProperty.setConcentration(concentration);
	}

	@Override
	public ChangerAnnouncerProperty[] getBufferedProperty() {
		return propertyBuffer.getBuffer();
	}

	public String getCadExit() {
		return cadExit;
	}

	public void setCadExit(String cadExit) {
		this.cadExit = cadExit;
	}

	public String getCadEnter() {
		return cadEnter;
	}

	public void setCadEnter(String cadEnter) {
		this.cadEnter = cadEnter;
	}

}
