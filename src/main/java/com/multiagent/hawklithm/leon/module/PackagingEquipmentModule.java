package com.multiagent.hawklithm.leon.module;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.manager.RFIDMachineFlowRecordManager;
import com.multiagent.hawklithm.leon.module.plugin.RFIDTypeAllocation;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PackagingEquipmentProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

/**
 * 打包设备模块
 * 
 * @author hawklithm 2013-12-27下午7:56:46
 */
public class PackagingEquipmentModule extends EquipmentObject<PackagingEquipmentProperty> {

	private Gson gson = new Gson();
	private RFIDMachineFlowRecordManager rfidManager;
	private RFIDTypeAllocation rfidTypeAllocation;
	private String packEnter="进入打包设备";
	private String packExit="离开打包设备";

//	private static int RFID_TYPE_ITEM = 0, RFID_TYPE_PACKAGE = 1;

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				addItem(SqlRFIDOriginalObject.getRfids(rfidObject));
				sendDataToDataBase(rfidObject, RFIDTypeAllocation.RFID_TYPE_ITEM,packEnter);
			}
		});
		/**
		 * 出口读卡器扫描到的数据类型:KIND_RFID_FROM_READER+DIR_EXIT
		 * 因为在本设备中会有手术包生成，所以扫描到的数据包含手术包和器械
		 */
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_RFID_FROM_READER + WardenMessage.DIR_EXIT) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				SqlRFIDOriginalObject[][] div = divideByRFIDType(rfidObject);
				removeItem(SqlRFIDOriginalObject.getRfids(div[0]));
				sendDataToDataBase(div[0], RFIDTypeAllocation.RFID_TYPE_ITEM,packExit);
				sendDataToDataBase(div[1], RFIDTypeAllocation.RFID_TYPE_PACKAGE,packExit);
			}
			

		});
		/**
		 * 打包好的手术包的手术包RFID和内部器械的RFID会被同一读卡器扫到，然后一起发送过来处理，
		 * 消息类型为KIND_ITEM_TO_PACKAGE 数据存储到tb_equipment_history设备处理流水表，用于信息追溯
		 */
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_MACHINE, WardenMessage.KIND_ITEM_TO_PACKAGE) {

			@Override
			public void asynchronizedProcess(String message) {
				SqlRFIDOriginalObject[] rfidObject = gson.fromJson(message, SqlRFIDOriginalObject[].class);
				rfidObject = makePackageRFIDBeTheFirstOne(rfidObject);
				sendPackagingDataToDataBase(rfidObject);
			}

		});
		propertyBuffer.init(getRfid(), getStaffRFID());
	}

	private SqlRFIDOriginalObject[] makePackageRFIDBeTheFirstOne(SqlRFIDOriginalObject[] rfids) {
		for (int i = 0; i < rfids.length; i++) {
			if (rfidTypeAllocation.isPacRFID(rfids[i].getRfid())) {
				SqlRFIDOriginalObject tmp = rfids[i];
				rfids[i] = rfids[0];
				rfids[0] = tmp;
				break;
			}
		}
		return rfids;
	}

	private SqlRFIDOriginalObject[][] divideByRFIDType(SqlRFIDOriginalObject[] rfids) {
		SqlRFIDOriginalObject[][] ans = new SqlRFIDOriginalObject[2][];
		List<SqlRFIDOriginalObject> l1 = new ArrayList<SqlRFIDOriginalObject>();
		List<SqlRFIDOriginalObject> l2 = new ArrayList<SqlRFIDOriginalObject>();
		for (int i = 0; i < rfids.length; i++) {
			switch (rfidTypeAllocation.getRFIDType(rfids[i].getRfid())) {
			case RFIDTypeAllocation.RFID_TYPE_ITEM:
				l1.add(rfids[i]);
				break;
			case RFIDTypeAllocation.RFID_TYPE_PACKAGE:
				l2.add(rfids[i]);
				break;
			}
		}
		ans[0] = l1.toArray(new SqlRFIDOriginalObject[l1.size()]);
		ans[1] = l2.toArray(new SqlRFIDOriginalObject[l2.size()]);
		return ans;
	}
	private void sendPackagingDataToDataBase(SqlRFIDOriginalObject[] infos) {
		for (int i = 1; i < infos.length; i++) {
			RFIDDataPac dataPac = new RFIDDataPac();
			dataPac.setEquipmentId(this.getRfid());
			dataPac.setStaffId(this.getStaffRFID());
			dataPac.setOriginalInfo(infos[i]);
			rfidManager.storeItemPackageMappingIntoDataBase(dataPac, infos[0].getRfid());
		}
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

	public RFIDMachineFlowRecordManager getRfidManager() {
		return rfidManager;
	}

	public void setRfidManager(RFIDMachineFlowRecordManager rfidManager) {
		this.rfidManager = rfidManager;
	}

	@Override
	public void doSetEquipmentParameter(PackagingEquipmentProperty parameter) {
		setModuleProperty(parameter);
	}

	@Override
	public PackagingEquipmentProperty doGetEquipmentSummaryInfo() {
		return getModuleProperty();
	}

	public RFIDTypeAllocation getRfidTypeAllocation() {
		return rfidTypeAllocation;
	}

	public void setRfidTypeAllocation(RFIDTypeAllocation rfidTypeAllocation) {
		this.rfidTypeAllocation = rfidTypeAllocation;
	}

	@Override
	public PropertyCollector doGetEquipmentSummaryInfoUnifiedInterface() {
		return doGetEquipmentSummaryInfo();
	}

	@Override
	public ChangerAnnouncerProperty[] getBufferedProperty() {
		return propertyBuffer.getBuffer();
	}

	public String getPackEnter() {
		return packEnter;
	}

	public void setPackEnter(String packEnter) {
		this.packEnter = packEnter;
	}

	public String getPackExit() {
		return packExit;
	}

	public void setPackExit(String packExit) {
		this.packExit = packExit;
	}

}