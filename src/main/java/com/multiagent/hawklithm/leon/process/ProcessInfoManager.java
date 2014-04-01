package com.multiagent.hawklithm.leon.process;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.multiagent.hawklithm.davinci.exceptioin.EquipmentNotFoundException;
import com.multiagent.hawklithm.davinci.exceptioin.ModuleNotFoundException;
import com.multiagent.hawklithm.davinci.exceptioin.ProcessModuleNotFoundException;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerPropertyArrayVersion;
import com.multiagent.hawklithm.leon.module.property.DO.ModuleProperty;
import com.multiagent.hawklithm.leon.process.interface4rpc.RPCProcessInfoManagerInterface;

public class ProcessInfoManager implements RPCProcessInfoManagerInterface {
	public Map<String, IProcessModule> ProcessMap;
	private Gson gson=new Gson();

	@Override
	public String[] getProcessInfo(String processName) throws ModuleNotFoundException {
		if (!ProcessMap.containsKey(processName)) {
			throw new ProcessModuleNotFoundException(" ", processName);
		}
		IProcessModule process = ProcessMap.get(processName);
		return getPropertyMessage(process.doGetEquipmentSummaryInfo());
	}

	@Override
	public String getEquipmentInfoById(String processName, Integer id)
			throws ModuleNotFoundException {
		if (!ProcessMap.containsKey(processName)) {
			throw new EquipmentNotFoundException(" ", processName, id);
		}
		IProcessModule process = ProcessMap.get(processName);
		return process.getEquipmentByRFID(id).doGetEquipmentSummaryInfoUnifiedInterface()
				.getProperty();
	}

	protected String[] getPropertyMessage(List<ModuleProperty> properties) {
		String[] msgs = new String[properties.size()];
		int index = 0;
		for (ModuleProperty property : properties) {
			msgs[index] = property.getProperty();
		}
		return msgs;
	}

	public Map<String, IProcessModule> getProcessMap() {
		return ProcessMap;
	}

	public void setProcessMap(Map<String, IProcessModule> processMap) {
		ProcessMap = processMap;
	}

	@Override
	public ChangerAnnouncerPropertyArrayVersion[] getBufferedPropertyList(String processName)  throws ModuleNotFoundException{
		if (!ProcessMap.containsKey(processName)) {
			throw new ProcessModuleNotFoundException(" ", processName);
		}
		IProcessModule process = ProcessMap.get(processName);
		List<ChangerAnnouncerProperty> list= process.getBufferedPropertyList();
		System.out.println("getBufferedPropertyList()调用: "+gson.toJson(list));
		//TODO 这里要把list从ChangerAnnouncerProperty转成ChangerAnnouncerPropertyArrayVersion，防止RPC调用参数出现问题
		ChangerAnnouncerPropertyArrayVersion[] ret=new ChangerAnnouncerPropertyArrayVersion[list.size()];
		int length=list.size();
		for (int i = 0; i < length; i++) {
			ret[i] = new ChangerAnnouncerPropertyArrayVersion(list.get(i));
			ret[i].setTimeStamp(new Date());
		}
		return ret;
	}
}
