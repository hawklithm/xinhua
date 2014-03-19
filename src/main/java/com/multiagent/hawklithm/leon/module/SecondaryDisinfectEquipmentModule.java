package com.multiagent.hawklithm.leon.module;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.manager.RFIDMachineFlowRecordManager;
import com.multiagent.hawklithm.leon.module.plugin.OriginalRFIDDataClassifier;
import com.multiagent.hawklithm.leon.module.plugin.RFIDTypeAllocation;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.leon.module.property.DO.SecondaryDisinfectEquipmentProperty;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

/**
 * 打包后进行二次消毒设备模块
 * 
 * @author hawklithm 2013-12-26下午4:11:43
 */
public class SecondaryDisinfectEquipmentModule extends EquipmentObject<SecondaryDisinfectEquipmentProperty> {

	private Gson gson = new Gson();
	private RFIDMachineFlowRecordManager rfidManager;
	private OriginalRFIDDataClassifier rfidClassifier;
	private String sdEnter="进入二次消毒设备";
	private String sdExit="离开二次消毒设备";

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				SqlRFIDOriginalObject[][] div = rfidClassifier.divideByRFIDType(rfidObject);
				addPackage(SqlRFIDOriginalObject.getRfids(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE]));
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE], RFIDTypeAllocation.RFID_TYPE_PACKAGE,sdEnter);
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_ITEM], RFIDTypeAllocation.RFID_TYPE_ITEM,sdEnter);
			}

		});
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_EXIT) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				SqlRFIDOriginalObject[][] div = rfidClassifier.divideByRFIDType(rfidObject);
				removePackage(SqlRFIDOriginalObject.getRfids(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE]));
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE], RFIDTypeAllocation.RFID_TYPE_PACKAGE,sdExit);
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_ITEM], RFIDTypeAllocation.RFID_TYPE_ITEM,sdExit);
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

	private void sendDataToDataBase(SqlRFIDOriginalObject[] infos, int rfidType,String status) {
		for (int i = 0; i < infos.length; i++) {
			sendDataToDataBase(infos[i], rfidType,status);
		}
	}

	private void sendDataToDataBase(SqlRFIDOriginalObject info, int rfidType,String status) {
		RFIDDataPac dataPac = new RFIDDataPac();
		dataPac.setEquipmentId(this.getRfid());
		dataPac.setStaffId(this.getStaffRFID());
		dataPac.setOriginalInfo(info);
		if (rfidType == RFIDTypeAllocation.RFID_TYPE_ITEM) {
			rfidManager.storeItemIntoDataBase(dataPac);
			rfidManager.storeItemHistoryToDataBase(info.getDate(), info.getRfid(), info.getReaderId(), null, status, moduleProperty.getRfid());
		} else if (rfidType == RFIDTypeAllocation.RFID_TYPE_PACKAGE) {
			rfidManager.storePackageIntoDataBase(dataPac);
			rfidManager.storePackageHistoryToDataBase(info.getDate(), info.getRfid(), info.getReaderId(), null, status, moduleProperty.getRfid());
		}
	}

	@Override
	public SecondaryDisinfectEquipmentProperty doGetEquipmentSummaryInfo() {
		return getModuleProperty();
	}

	@Override
	public PropertyCollector doGetEquipmentSummaryInfoUnifiedInterface() {
		return doGetEquipmentSummaryInfo();
	}

	@Override
	public void doSetEquipmentParameter(SecondaryDisinfectEquipmentProperty parameter) {
		setModuleProperty(parameter);
	}
	
	@Override
	public ChangerAnnouncerProperty[] getBufferedProperty() {
		return propertyBuffer.getBuffer();
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

	public OriginalRFIDDataClassifier getRfidClassifier() {
		return rfidClassifier;
	}

	public void setRfidClassifier(OriginalRFIDDataClassifier rfidClassifier) {
		this.rfidClassifier = rfidClassifier;
	}

	public String getSdEnter() {
		return sdEnter;
	}

	public void setSdEnter(String sdEnter) {
		this.sdEnter = sdEnter;
	}

	public String getSdExit() {
		return sdExit;
	}

	public void setSdExit(String sdExit) {
		this.sdExit = sdExit;
	}

}
