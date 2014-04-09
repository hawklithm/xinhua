package com.multiagent.hawklithm.leon.module;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.manager.RFIDMachineFlowRecordManager;
import com.multiagent.hawklithm.leon.module.plugin.OriginalRFIDDataClassifier;
import com.multiagent.hawklithm.leon.module.plugin.RFIDTypeAllocation;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.GateProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

public class Gate extends EquipmentObject<GateProperty>{

	private Gson gson = new Gson();
	private OriginalRFIDDataClassifier rfidClassifier;
	private RFIDMachineFlowRecordManager rfidManager;
	/**
	 * 进入状态
	 */
	private String gateEnter="进入";
	/**
	 * 退出状态
	 */
	private String gateExit="离开";

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid())+this.getWorkspaceName() + WardenMessage.TARGET_TYPE_ENTRY, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				SqlRFIDOriginalObject[][] div = rfidClassifier.divideByRFIDType(rfidObject);
				addPackage(SqlRFIDOriginalObject.getRfids(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE]));
				addItem(SqlRFIDOriginalObject.getRfids(div[RFIDTypeAllocation.RFID_TYPE_ITEM]));
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE], RFIDTypeAllocation.RFID_TYPE_PACKAGE,gateEnter+getWorkspaceNameToUser());
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_ITEM], RFIDTypeAllocation.RFID_TYPE_ITEM,gateEnter+getWorkspaceNameToUser());
				System.out.println(moduleProperty.getWorkspaceNameToUser()+"模块接收到进入数据，并存储到缓存中: "+gson.toJson(propertyBuffer));
			}

		});
		this.registWarden(new Warden(this.getWorkspaceName() + WardenMessage.TARGET_TYPE_ENTRY, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_EXIT) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				SqlRFIDOriginalObject[][] div = rfidClassifier.divideByRFIDType(rfidObject);
				removePackage(SqlRFIDOriginalObject.getRfids(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE]));
				removeItem(SqlRFIDOriginalObject.getRfids(div[RFIDTypeAllocation.RFID_TYPE_ITEM]));
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_PACKAGE], RFIDTypeAllocation.RFID_TYPE_PACKAGE,gateExit+getWorkspaceNameToUser());
				sendDataToDataBase(div[RFIDTypeAllocation.RFID_TYPE_ITEM], RFIDTypeAllocation.RFID_TYPE_ITEM,gateExit+getWorkspaceNameToUser());
				System.out.println(moduleProperty.getWorkspaceNameToUser()+"模块接收到离开数据，并存储到缓存中: "+gson.toJson(propertyBuffer));
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
	public GateProperty doGetEquipmentSummaryInfo() {
		return moduleProperty;
	}

	@Override
	public void doSetEquipmentParameter(GateProperty parameter) {
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


	@Override
	public ChangerAnnouncerProperty getBufferedProperty() {
		propertyBuffer.markDown(moduleProperty.getRfid(), moduleProperty.getStaffRFID());
		return propertyBuffer.getBuffer();
	}


	public String getWorkspaceName() {
		return moduleProperty.getWorkspaceName();
	}

	public void setWorkspaceName(String workspaceName) {
		moduleProperty.setWorkspaceName(workspaceName);
	}
	
	public String getWorkspaceNameToUser() {
		return moduleProperty.getWorkspaceNameToUser();
	}
	public void setWorkspaceNameToUser(String workspaceNameToUser) {
		moduleProperty.setWorkspaceNameToUser(workspaceNameToUser);
	}

	public String getGateEnter() {
		return gateEnter;
	}

	public void setGateEnter(String gateEnter) {
		this.gateEnter = gateEnter;
	}

	public String getGateExit() {
		return gateExit;
	}

	public void setGateExit(String gateExit) {
		this.gateExit = gateExit;
	}

	public OriginalRFIDDataClassifier getRfidClassifier() {
		return rfidClassifier;
	}

	public void setRfidClassifier(OriginalRFIDDataClassifier rfidClassifier) {
		this.rfidClassifier = rfidClassifier;
	}

}
