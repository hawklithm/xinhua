package com.multiagent.hawklithm.davinci.rpc.Server;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.RPCInterfaceNotFoundException;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemProtocol;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemServerProxy;
import com.multiagent.hawklithm.davinci.rpc.acl.RPCACLManager;
/**
 * 
 * @author hawklithm
 *
 */
public class RPCRegManager {
	private List<RPCSystemServerProxy> regList = new ArrayList<RPCSystemServerProxy>();
	private RPCACLManager aclManager;

	public boolean regist(RPCSystemServerProxy object) {
		System.out.println("RPC×¢²á" + object.getInterfaceName()+":"+object.getClassName());
		aclManager.ManagerRPCInterfaceInfo(object,object.isVisible());
		if (regList.contains(object)) {
			return false;
		}
		regList.add(object);
		return true;
	}

	public List<RPCSystemServerProxy> getRegList() {
		return regList;
	}
	
	public Object getTarget(RPCSystemProtocol info) throws RPCInterfaceNotFoundException{
		for (RPCSystemServerProxy object:regList){
			if (object.getInterfaceName().equals(info.getClassName())){
				if(object.getVersion().equals(info.getVersion())){
					return object.getTarget();
				}
				System.out.println("you get the wrong version");
			}
		}
		throw new RPCInterfaceNotFoundException();
	}

	public RPCACLManager getAclManager() {
		return aclManager;
	}

	public void setAclManager(RPCACLManager aclManager) {
		this.aclManager = aclManager;
	}
}