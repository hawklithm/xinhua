package com.multiagent.hawklithm.leon.module;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.manager.RFIDMachineFlowRecordManager;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.leon.module.property.DO.SortingEquipmentProperty;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

/**
 * 分拣设备模块
 * 
 * @author hawklithm 2013-12-26下午4:11:43
 */
public class SortingEquipmentModule extends EquipmentObject<SortingEquipmentProperty> {

	private Gson gson = new Gson();
	private RFIDMachineFlowRecordManager rfidManager;
	private String sortingEnter="进入分拣设备";
	private String sortingExit="离开分拣设备";

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				addItem(SqlRFIDOriginalObject.getRfids(rfidObject));
				sendDataToDataBase(rfidObject,sortingEnter);
			}

		});
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_EXIT) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				removeItem(SqlRFIDOriginalObject.getRfids(rfidObject));
				sendDataToDataBase(rfidObject,sortingExit);
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

	public RFIDMachineFlowRecordManager getRfidManager() {
		return rfidManager;
	}

	public void setRfidManager(RFIDMachineFlowRecordManager rfidManager) {
		this.rfidManager = rfidManager;
	}

	@Override
	public void doSetEquipmentParameter(SortingEquipmentProperty parameter) {
		setModuleProperty(parameter);
	}

	@Override
	public SortingEquipmentProperty doGetEquipmentSummaryInfo() {
		return getModuleProperty();
	}

	@Override
	public PropertyCollector doGetEquipmentSummaryInfoUnifiedInterface() {
		return doGetEquipmentSummaryInfo();
	}
	@Override
	public ChangerAnnouncerProperty getBufferedProperty() {
		propertyBuffer.markDown(moduleProperty.getRfid(), moduleProperty.getStaffRFID());
		return propertyBuffer.getBuffer();
	}
}
