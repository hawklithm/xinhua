package com.multiagent.hawklithm.leon.manager;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.davinci.exceptioin.ProcessModuleNotFoundException;
import com.multiagent.hawklithm.global.register.IRegisterManager;
import com.multiagent.hawklithm.leon.module.EquipmentObject;
import com.multiagent.hawklithm.leon.process.IProcessModule;

/**
 * ��������ģ��ע���
 * 
 * @author hawklithm
 * 
 */
public class ProcessModuleRegisterManager implements IRegisterManager {
	private List<IProcessModule> regList = new ArrayList<IProcessModule>();

	public boolean regist(Object thing) {
		IProcessModule object = (IProcessModule) thing;
		System.out.println("����ģ��ע��" + object.getName());
		if (regList.contains(object)) {
			return false;
		}
		regList.add(object);
		return true;
	}

	public List<IProcessModule> getRegList() {
		return regList;
	}

	public IProcessModule getProcessModuleByName(String name) throws ProcessModuleNotFoundException {
		for (IProcessModule object : regList) {
			if (object.getName().equals(name)) {
				return object;
			}
		}
		// TODO �׳�δ���ҵ��豸�쳣
		System.out.println("there's no ProcessModule with Name: " + name + " exist");
		throw new ProcessModuleNotFoundException();
	}

	public EquipmentObject getEquipmentByRFID(int id) throws ProcessModuleNotFoundException {
		for (IProcessModule object : regList) {
			try {
				EquipmentObject tmpEquipmentHandler = object.getEquipmentByRFID(id);
				if (tmpEquipmentHandler != null) {
					return tmpEquipmentHandler;
				}
			} catch (EquipmentNotFoundException e) {

			}
		}
		System.out.println("there's not Equipment with id: " + id + " exsit");
		throw new ProcessModuleNotFoundException();
	}
}
