package com.multiagent.hawklithm.leon.module;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.leon.module.property.DO.ReaderEquipmentProperty;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

public class Reader extends EquipmentObject<ReaderEquipmentProperty> {

//	private int targetRFID = 0;
//	private String targetMessageKind = "";
//	private String targetKind = "";
//	private String targetMessageDir = "";

	// private MachineFlowRecordDAO machineDataDao;
	// private Gson gson = new Gson();
	// private int lastId = 0;
	// private Integer bakLastId = 0;
	// private AtomicBoolean dataDealing=false;
	// private boolean onceMore = false;
	// private Lock lock = new ReentrantLock();
//	private Gson gson=new Gson();

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_READER, WardenMessage.KIND_NEW_DATA_COMING + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				System.out.println("读卡器接收到数据，转发给设备: ");
				WardenMessage wardenMessage = new WardenMessage();
				wardenMessage.setNote(message);
				wardenMessage.setTarget(String.valueOf(moduleProperty.getTargetRFID()) + moduleProperty.getTargetKind());
				wardenMessage.setKind(moduleProperty.getTargetMessageKind() + moduleProperty.getTargetMessageDir());
//				System.out.println(gson.toJson(wardenMessage));
				sendOutMessage(wardenMessage);
			}

		});
	}

	public int getTargetRFID() {
		return moduleProperty.getTargetRFID();
	}

	public void setTargetRFID(int targetRFID) {
		moduleProperty.setTargetRFID(targetRFID);
	}

	public String getTargetMessageKind() {
		return moduleProperty.getTargetMessageKind();
	}

	public void setTargetMessageKind(String targetMessageKind) {
		moduleProperty.setTargetMessageKind(targetMessageKind);
	}

	// public MachineFlowRecordDAO getMachineDataDao() {
	// return machineDataDao;
	// }
	//
	// public void setMachineDataDao(MachineFlowRecordDAO machineDataDao) {
	// this.machineDataDao = machineDataDao;
	// }

	public String getTargetKind() {
		return moduleProperty.getTargetKind();
	}

	public void setTargetKind(String targetKind) {
		moduleProperty.setTargetKind(targetKind);
	}

	public String getTargetMessageDir() {
		return moduleProperty.getTargetMessageDir();
	}

	public void setTargetMessageDir(String targetMessageDir) {
		moduleProperty.setTargetMessageDir(targetMessageDir);
	}

	@Override
	public void doSetEquipmentParameter(ReaderEquipmentProperty parameter) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReaderEquipmentProperty doGetEquipmentSummaryInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyCollector doGetEquipmentSummaryInfoUnifiedInterface() {
		return doGetEquipmentSummaryInfo();
	}
	
	@Override
	public ChangerAnnouncerProperty[] getBufferedProperty() {
		return propertyBuffer.getBuffer();
	}
}
