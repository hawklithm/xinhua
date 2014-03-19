package com.multiagent.hawklithm.leon.process.interface4rpc;

import com.multiagent.hawklithm.davinci.exceptioin.ModuleNotFoundException;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerPropertyArrayVersion;

public interface RPCProcessInfoManagerInterface {

	String[] getProcessInfo(String processName) throws ModuleNotFoundException;

	String getEquipmentInfoById(String processName, Integer id) throws ModuleNotFoundException;
	
	/**
	 *  ��ȡbuffered���ݵĽӿ�
	 * @param processName
	 * @return
	 * @throws ModuleNotFoundException 
	 */
	ChangerAnnouncerPropertyArrayVersion[][] getBufferedPropertyList(String processName) throws ModuleNotFoundException;

}
