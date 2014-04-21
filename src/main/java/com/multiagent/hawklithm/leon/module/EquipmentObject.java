package com.multiagent.hawklithm.leon.module;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.multiagent.hawklithm.item.dao.IbatisItemInfoDAO;
import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;
import com.multiagent.hawklithm.leon.module.property.DO.PropertyCollector;
import com.multiagent.hawklithm.leon.plugin.EquipmentPropertyChangerAnnouncerBuffer;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

public abstract class EquipmentObject<T extends ModuleProperty> implements Module<T>, WardenOperator<WardenMessage> {
	private WardenManager wardenManager;
	protected EquipmentPropertyChangerAnnouncerBuffer propertyBuffer=new EquipmentPropertyChangerAnnouncerBuffer();
	protected T moduleProperty;
	protected IbatisItemInfoDAO ibatisItemInfoDao;

	/**
	 * 设置设备属性
	 * 
	 * 设备模块中的rfid集合使用的是concurrent hash set，复制时勿直接将set赋给成员变量
	 */
	abstract public void doSetEquipmentParameter(T parameter);

	/**
	 * 获取设备属性
	 * 
	 * @return
	 */
	abstract public T doGetEquipmentSummaryInfo();

	
	/**
	 * 以统一接口返回属性
	 * @return
	 */
	abstract public PropertyCollector doGetEquipmentSummaryInfoUnifiedInterface();
	
	abstract public ChangerAnnouncerProperty getBufferedProperty();
	

	/**
	 * 初始化Warden
	 */
	abstract public void initWarden();

	/**
	 * 获取模块属性，这里和{@link EquipmentObject#doGetEquipmentSummaryInfo()} 一样
	 */
	public T doGetModuleSummaryInfo() {
		return doGetEquipmentSummaryInfo();

	}

	public void sendOutMessage(WardenMessage message) {
		wardenManager.push(message);
	}

	public void registWarden(Warden warden) {
		wardenManager.regist(warden);
	}

	public void addItem(int RFID) {
//		itemRFIDs.add(Integer.valueOf(RFID));
//		moduleProperty.getItemRFIDs().add(Integer.valueOf(RFID));
		ItemInfoDO item=ibatisItemInfoDao.queryById(RFID);
		StringBuilder builder=new StringBuilder(item.getRemark());
		builder.append(';').append(new Date()).append(',').append(moduleProperty.getRfid()).append(',').append(moduleProperty.getStaffRFID());
		moduleProperty.addItem(item);
		propertyBuffer.addItem(item);
	}
	
	public void addItem(ItemInfoDO item){
		moduleProperty.addItem(item);
		propertyBuffer.addItem(item);
	}

	public void addItem(int[] RFIDs) {
		if (moduleProperty.getItemRFIDs() == null) {
			moduleProperty.setItemRFIDs(Collections.newSetFromMap(new ConcurrentHashMap<ItemInfoDO, Boolean>()));
		}
		for (int rfid : RFIDs) {
			addItem(rfid);
		}
	}

	public void addPackage(int[] RFIDs) {
		for (int rfid : RFIDs) {
			addPackage(rfid);
		}
	}

	public void addPackage(int RFID) {
//		moduleProperty.getPackageRFIDs().add(Integer.valueOf(RFID));
		moduleProperty.addPackage(Integer.valueOf(RFID));
		propertyBuffer.addPackage(RFID);
	}

	public boolean removeItem(int RFID) {
		ItemInfoDO item=ibatisItemInfoDao.queryById(RFID);
		propertyBuffer.removeItem(item);
		return moduleProperty.removeItem(item);
//		return moduleProperty.getItemRFIDs().remove(Integer.valueOf(RFID));
//		return itemRFIDs.remove(Integer.valueOf(RFID));
	}

	public boolean removeItem(int[] RFIDs) {
		boolean ret = true;
		for (int i = 0; i < RFIDs.length; i++) {
			ret &= removeItem(RFIDs[i]);
		}
		return ret;
	}

	public boolean removePackage(int[] RFIDs) {
		boolean ret = true;
		for (int i = 0; i < RFIDs.length; i++) {
			ret &= removePackage(RFIDs[i]);
		}
		return ret;
	}

	public boolean removePackage(int RFID) {
//		return packageRFIDs.remove(Integer.valueOf(RFID));
		propertyBuffer.removePackage(RFID);
		return moduleProperty.removePackage(Integer.valueOf(RFID));
	}

	public int getStaffRFID() {
		return moduleProperty.getStaffRFID();
//		return staffRFID;
	}

	public void setStaffRFID(int staffRFID) {
//		this.staffRFID = staffRFID;
		moduleProperty.setStaffRFID(staffRFID);
//		propertyBuffer.add(moduleProperty);
	}

	public WardenManager getWardenManager() {
		return wardenManager;
	}

	public void setWardenManager(WardenManager wardenManager) {
		this.wardenManager = wardenManager;
	}

	public Set<ItemInfoDO> getItemRFIDs() {
//		return itemRFIDs;
		return moduleProperty.getItemRFIDs();
	}

	public void setItemRFIDs(Set<ItemInfoDO> itemRFIDs) {
		Set<ItemInfoDO> items=moduleProperty.getItemRFIDs();
		if (items == null) {
			moduleProperty.setItemRFIDs(Collections.newSetFromMap(new ConcurrentHashMap<ItemInfoDO, Boolean>()));
			items=moduleProperty.getItemRFIDs();
		} else {
			items.clear();
		}
		for (ItemInfoDO i : itemRFIDs) {
			addItem(i);
//			this.itemRFIDs.add(i);
		}
	}

	public Set<Integer> getPackageRFIDs() {
		return moduleProperty.getPackageRFIDs();
	}

	public void setPackageRFIDs(Set<Integer> packageRFIDs) {
		Set<Integer> pacs=moduleProperty.getPackageRFIDs();
		if (pacs == null) {
			moduleProperty.setPackageRFIDs(Collections.newSetFromMap(new ConcurrentHashMap<Integer, Boolean>()));
		} else {
			pacs.clear();
		}
		for (Integer i : packageRFIDs) {
//			this.moduleProperty.getPackageRFIDs().add(i);
			addPackage(i);
//			this.packageRFIDs.add(i);
		}
	}

	public int getRfid() {
//		return rfid;
		return moduleProperty.getRfid();
	}

	public void setRfid(int rfid) {
//		this.rfid = rfid;
		moduleProperty.setRfid(rfid);
//		propertyBuffer.add(moduleProperty);
	}

	public T getModuleProperty() {
		return moduleProperty;
	}

	public void setModuleProperty(T moduleProperty) {
		this.moduleProperty = moduleProperty;
	}

	public IbatisItemInfoDAO getIbatisItemInfoDao() {
		return ibatisItemInfoDao;
	}

	public void setIbatisItemInfoDao(IbatisItemInfoDAO ibatisItemInfoDao) {
		this.ibatisItemInfoDao = ibatisItemInfoDao;
	}


}
