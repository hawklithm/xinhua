package com.multiagent.hawklithm.davinci.rpc.acl;

import com.multiagent.hawklithm.davinci.rpc.DAO.RPCInterfaceInfoDAO;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemProtocol;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemServerProxy;
/*
 * RPCInterfaceInfoDAO主要来操作代理接口的一些信息
 */
public class RPCACLManager {
	private RPCInterfaceInfoDAO interfaceInfoDAO;
	public void ManagerRPCInterfaceInfo(RPCSystemServerProxy info,boolean visible){
		interfaceInfoDAO.submitData(info.getInterfaceName(), info.getVersion(), info.getClassName(), info.getBeanId(), info.getComment(), visible);
	}
	public boolean verifyRPCInterfaceCall(RPCSystemProtocol info){
//		if (interfaceInfoDAO.getData(info.getClassName(), info.getVersion(), info.getUserName())==null) return false;
		return true;
	}
	public RPCInterfaceInfoDAO getInterfaceInfoDAO() {
		return interfaceInfoDAO;
	}
	public void setInterfaceInfoDAO(RPCInterfaceInfoDAO interfaceInfoDAO) {
		this.interfaceInfoDAO = interfaceInfoDAO;
	}
}
