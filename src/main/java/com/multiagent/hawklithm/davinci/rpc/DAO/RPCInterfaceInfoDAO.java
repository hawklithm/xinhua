package com.multiagent.hawklithm.davinci.rpc.DAO;

import java.util.List;

import com.multiagent.hawklithm.davinci.rpc.DO.RPCInterfaceInfoDO;
import com.multiagent.hawklithm.ibatis.IbatisManagerModule;

public class RPCInterfaceInfoDAO {
	private IbatisManagerModule ibatisManager;
	public void submitData(String interfaceName,String version,String className,String beanId,String comment,boolean visible){
		RPCInterfaceInfoDO data=new RPCInterfaceInfoDO();
		data.setInterfaceName(interfaceName);
		data.setVersion(version);
		data.setClassName(className);
		data.setBeanId(beanId);
		data.setComment(comment);
		data.setVisible(visible);
		//TODO 用ibatisManager将数据存储进数据库
		
	}
	public List getData(String interfaceName,String version,String userName) {
		//TODO 调用ibatis查询接口调用权限表 
		return null;
		
	}
	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}
	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}
}
