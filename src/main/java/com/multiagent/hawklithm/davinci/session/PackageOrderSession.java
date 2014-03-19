package com.multiagent.hawklithm.davinci.session;

import org.springframework.beans.factory.annotation.Configurable;

import com.multiagent.hawklithm.sephiroth.DO.TempPackageOrderClusterDO;
import com.multiagent.hawklithm.sephiroth.DO.TempSubPackageOrderDO;
import com.multiagent.hawklithm.sephiroth.manager.OrderManager;
/**
 * 订单会话，每个用户登陆后会创建一个这样的session
 * @author hawklithm
 *
 */
//TODO 这个类应该写在client端了
@Configurable("packageOrderSession")
public class PackageOrderSession {
	private OrderManager orderManager;
	private TempPackageOrderClusterDO orderCluster=new TempPackageOrderClusterDO();

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	public void submit(){
	}
	public void addSubOrder(TempSubPackageOrderDO subOrder){
		orderCluster.getSubOrders().add(subOrder);
	}
}
