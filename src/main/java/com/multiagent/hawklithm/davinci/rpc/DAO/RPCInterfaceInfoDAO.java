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
		//TODO ��ibatisManager�����ݴ洢�����ݿ�
		
	}
	public List getData(String interfaceName,String version,String userName) {
		//TODO ����ibatis��ѯ�ӿڵ���Ȩ�ޱ� 
		return null;
		
	}
	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}
	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}
}
