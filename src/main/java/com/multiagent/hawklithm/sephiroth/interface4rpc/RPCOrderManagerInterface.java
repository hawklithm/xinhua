package com.multiagent.hawklithm.sephiroth.interface4rpc;

import java.util.Date;

import com.multiagent.hawklithm.sephiroth.DO.SqlPackageOrderClusterDO;
import com.multiagent.hawklithm.sephiroth.DO.TempPackageOrderClusterDO;

/**
 * RPC订单接口，服务器执行
 * 
 * @author hawklithm
 * 
 */
public interface RPCOrderManagerInterface {
	public static String ORDER_STATUS_RECLAIM = "order_state_reclaim",
			ORDER_STATUS_STORAGED = "order_state_storaged",
			ORDER_STATUS_ASSORTING = "order_state_assorting",
			oRDER_STATUS_SIGNED = "order_state_signed";

	public void submitOrder(TempPackageOrderClusterDO clusterDO);

	public double calculateOrderClusterPrice(TempPackageOrderClusterDO clusterDO);

	public void deleteOrder(int orderId);

	SqlPackageOrderClusterDO[] selectOrdersByUserId(Integer userId, Integer offset, Integer length);

	SqlPackageOrderClusterDO selectOrdersByOrderId(Integer orderId);

	SqlPackageOrderClusterDO[] selectOrdersByInfo(Integer userId, Date startDay, Date endDay,
			String orderStatus, Integer offset, Integer length);
}
