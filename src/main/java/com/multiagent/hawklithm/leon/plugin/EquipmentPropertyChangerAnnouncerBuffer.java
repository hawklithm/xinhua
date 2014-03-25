package com.multiagent.hawklithm.leon.plugin;

import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;

public class EquipmentPropertyChangerAnnouncerBuffer {
//	private ChangerAnnouncerProperty[] buffer;
//	private int defaultCapacity = 100;
//	private ItemInfoDO[] itemAdd=new ItemInfoDO[defaultCapacity];
//	private ItemInfoDO[] itemRemove=new ItemInfoDO[defaultCapacity];
//	private Integer[] packageAdd=new Integer[defaultCapacity];
//	private Integer[] packageRmove=new Integer[defaultCapacity];
//	private Integer machineRFID;
//	private Integer staffRFID;	
	private ChangerAnnouncerProperty buffer=new ChangerAnnouncerProperty();
	
//	private int headPointer = 0;
//	private int tailPointer = headPointer + 1;
//	private int preRfid, preStaffRfid;

	public void init(int rfid, int staffRfid) {
//		buffer = new ChangerAnnouncerProperty[defaultCapacity];
		// add(new ChangerAnnouncerProperty(rfid, staffRfid));
//		if (defaultCapacity < 10) {
//			defaultCapacity = 10;
//		}
//		preRfid = rfid;
//		preStaffRfid = rfid;
		buffer.setStaffRFID(staffRfid);
		buffer.setMachineRFID(rfid);
		// for (int i = 0; i < defaultCapacity; i++) {
		// add(new ChangerAnnouncerProperty(rfid, staffRfid));
		// }
	}
	
	public void markDown(int rfid,int staffRFID){
		buffer.setStaffRFID(staffRFID);
		buffer.setMachineRFID(rfid);
	}

//	public void push(ModuleProperty property) {
//		buffer[tailForward()] = new ChangerAnnouncerProperty(property);
		
//	}

//	public ChangerAnnouncerProperty pop() {
//		return buffer[headForward()];
//	}

//	public boolean isEmpty() {
//		if ((headPointer + 1) % defaultCapacity == tailPointer) {
//			return true;
//		}
//		return false;
//	}

//	public int headForward() {
//		synchronized (this.getClass()) {
//			int testHeadPointer = (headPointer + 1) % defaultCapacity;
//			if (testHeadPointer != tailPointer) {
//				headPointer = testHeadPointer;
//			}
//			return headPointer;
//		}
//	}
//
//	public int tailForward() {
//		synchronized (this.getClass()) {
//			int ret = tailPointer;
//			tailPointer = (tailPointer + 1) % defaultCapacity;
//			if (tailPointer == headPointer) {
//				headPointer++;
//			}
//			return ret;
//		}
//	}

//	public int getLast() {
//		if (isEmpty()) {
//			return -1;
//		}
//		return (tailPointer + defaultCapacity - 1) % defaultCapacity;
//	}

//	public void add(ModuleProperty property) {
//		push(property);
//	}

	public void addItem(ItemInfoDO item) {
		synchronized (this.getClass()) {
//			if (isEmpty()) {
//				add(new ChangerAnnouncerProperty(preRfid, preStaffRfid));
//			}
//			int last = getLast();
//			if (buffer[last] != null) {
//				buffer[last].addItem(rfid);
//			}
			buffer.addItem(item);
		}
	}

	public void removeItem(ItemInfoDO item) {
		synchronized (this.getClass()) {
//			if (isEmpty()) {
//				add(new ChangerAnnouncerProperty(preRfid, preStaffRfid));
//			}
//			int last = getLast();
//			if (buffer[last] != null) {
//				buffer[last].removeItem(item);
//			}
			buffer.removeItem(item);
		}
	}

	public void addPackage(int rfid) {
		synchronized (this.getClass()) {
//			if (isEmpty()) {
//				add(new ChangerAnnouncerProperty(preRfid, preStaffRfid));
//			}
//			int last = getLast();
//			if (buffer[last] != null) {
//				buffer[last].addPackage(rfid);
//			}
			buffer.addPackage(rfid);
		}
	}

	public void removePackage(int rfid) {
		synchronized (this.getClass()) {
//			if (isEmpty()) {
//				add(new ChangerAnnouncerProperty(preRfid, preStaffRfid));
//			}
//			int last = getLast();
//			if (buffer[last] != null) {
//				buffer[last].removePackage(rfid);
//			}
			buffer.removePackage(rfid);
		}
	}

	public ChangerAnnouncerProperty getBuffer() {
		synchronized (this.getClass()) {
//			List<ChangerAnnouncerProperty> ret = new ArrayList<ChangerAnnouncerProperty>();
//			while (!isEmpty()) {
//				ret.add(pop());
//			}
//			return ret.toArray(new ChangerAnnouncerProperty[ret.size()]);
			ChangerAnnouncerProperty ret=buffer;
			buffer=new ChangerAnnouncerProperty();
			return ret;
		}
	}

//	public int getDefaultCapacity() {
//		return defaultCapacity;
//	}
//
//	public void setDefaultCapacity(int capacity) {
//		this.defaultCapacity = capacity + 1;
//	}
}
