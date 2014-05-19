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
 * RPC接口服务管理器
 * List里面包含很多的提供服务的接口的具体信息及对应的具体类名
 *
 */
public class RPCRegManager {
	private List<RPCSystemServerProxy> regList = new ArrayList<RPCSystemServerProxy>();
	private RPCACLManager aclManager;

	public boolean regist(RPCSystemServerProxy object) {
		System.out.println("RPC注册" + object.getInterfaceName()+":"+object.getClassName());
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
			//RPCSystemProtocol的类名实际上等于RPCSystemServerProxy的接口名
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