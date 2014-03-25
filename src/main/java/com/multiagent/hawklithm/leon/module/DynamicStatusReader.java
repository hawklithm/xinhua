package com.multiagent.hawklithm.leon.module;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.module.plugin.DirGetter;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.leon.module.property.DO.ReaderEquipmentProperty;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

/**
 * 用于处理单个口进出的设备
 * 
 * @author hawklithm
 * 
 */
public class DynamicStatusReader extends EquipmentObject<ReaderEquipmentProperty> {

	private int targetRFID = 0;
	private String targetMessageKind = "";
	private String targetKind = "";
	// private String targetMessageDir = "";
	private DirGetter dirGetter;
	private Gson gson = new Gson();

	private class InOutPackage {
		private String inMessage;
		private String OutMessage;

		public String getInMessage() {
			return inMessage;
		}

		public void setInMessage(String inMessage) {
			this.inMessage = inMessage;
		}

		public String getOutMessage() {
			return OutMessage;
		}

		public void setOutMessage(String outMessage) {
			OutMessage = outMessage;
		}
	}

	private InOutPackage inOutDivide(String msg) {
		SqlRFIDOriginalObject[] tmpArray = gson.fromJson(msg, SqlRFIDOriginalObject[].class);
		int len = tmpArray.length;
		SqlRFIDOriginalObject[] dataIn = new SqlRFIDOriginalObject[len];
		SqlRFIDOriginalObject[] dataOut = new SqlRFIDOriginalObject[len];
		int inIndex = 0, outIndex = 0;
		for (int i = 0; i < len; i++) {
			if (dirGetter.getDir(tmpArray[i]).equals(WardenMessage.DIR_ENTER)) {
				dataIn[inIndex++] = tmpArray[i];
			} else {
				dataOut[outIndex++] = tmpArray[i];
			}
		}
		InOutPackage ret = new InOutPackage();
		SqlRFIDOriginalObject[] tOut, tIn;
		tOut = new SqlRFIDOriginalObject[outIndex];
		for (int i = 0; i < outIndex; i++) {
			tOut[i] = dataOut[i];
		}
		ret.setOutMessage(gson.toJson(tOut));
		tIn = new SqlRFIDOriginalObject[inIndex];
		for (int i = 0; i < inIndex; i++) {
			tIn[i] = dataIn[i];
		}
		ret.setInMessage(gson.toJson(tIn));
		return ret;
	}

	@Override
	public void initWarden() {
		this.registWarden(new Warden(String.valueOf(this.getRfid()) + WardenMessage.TARGET_TYPE_READER, WardenMessage.KIND_NEW_DATA_COMING + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				WardenMessage wardenInMessage = new WardenMessage();
				WardenMessage wardenOutMessage = new WardenMessage();
				InOutPackage inOut = inOutDivide(message);
				wardenInMessage.setNote(inOut.getInMessage());
				wardenInMessage.setTarget(String.valueOf(targetRFID) + targetKind);
				wardenInMessage.setKind(targetMessageKind + WardenMessage.DIR_ENTER);
				wardenOutMessage.setNote(inOut.getOutMessage());
				wardenOutMessage.setTarget(String.valueOf(targetRFID) + targetKind);
				wardenOutMessage.setKind(targetMessageKind + WardenMessage.DIR_EXIT);
				sendOutMessage(wardenInMessage);
				sendOutMessage(wardenOutMessage);
			}
		});
	}

	public DirGetter getDirGetter() {
		return dirGetter;
	}

	public void setDirGetter(DirGetter dirGetter) {
		this.dirGetter = dirGetter;
	}

	public int getTargetRFID() {
		return targetRFID;
	}

	public void setTargetRFID(int targetRFID) {
		this.targetRFID = targetRFID;
	}

	public String getTargetMessageKind() {
		return targetMessageKind;
	}

	public void setTargetMessageKind(String targetMessageKind) {
		this.targetMessageKind = targetMessageKind;
	}

	public String getTargetKind() {
		return targetKind;
	}

	public void setTargetKind(String targetKind) {
		this.targetKind = targetKind;
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
	public ChangerAnnouncerProperty getBufferedProperty() {
		return propertyBuffer.getBuffer();
	}

}
