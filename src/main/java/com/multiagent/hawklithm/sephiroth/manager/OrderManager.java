package com.multiagent.hawklithm.sephiroth.manager;

import java.util.Date;
import java.util.List;

import com.multiagent.hawklithm.davinci.exceptioin.OrderNotFoundException;
import com.multiagent.hawklithm.davinci.global.IDataManager;
import com.multiagent.hawklithm.sephiroth.DAO.IbatisOrderDataOperation;
import com.multiagent.hawklithm.sephiroth.DO.SqlMainPackageOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.SqlPackageOrderClusterDO;
import com.multiagent.hawklithm.sephiroth.DO.SqlReflectOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.SqlSubPackageOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.TempMainPackageOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.TempPackageOrderClusterDO;
import com.multiagent.hawklithm.sephiroth.DO.TempReflectOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.TempSubPackageOrderDO;
import com.multiagent.hawklithm.sephiroth.interface4rpc.RPCOrderManagerInterface;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;

/**
 * 
 * @author hawklithm
 * 
 */
public class OrderManager implements IDataManager, RPCOrderManagerInterface {
	private IbatisOrderDataOperation orderStoreDAO;
	private WardenOperator<Integer> transportOrderMessagePusher;

	/**
	 * 计算订单金额
	 * 
	 * @param clusterDO
	 */
	public double calculateOrderClusterPrice(TempPackageOrderClusterDO clusterDO) {
		return 0;
	}

	/**
	 * 订单删除
	 * 
	 * @param orderId
	 *            订单号
	 */
	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		orderStoreDAO.delete(orderId);
	}

	/**
	 * 提交订单
	 */
	@Override
	public void submitOrder(TempPackageOrderClusterDO clusterDO) {
		int id = 0;
		if ((id = storeIntoDataBase(tempClusterToSqlCluster(clusterDO))) != 0) {
			/**
			 * 发送运单创建信息
			 */
			transportOrderMessagePusher.sendOutMessage(id);
		}
	}

	public int storeIntoDataBase(Object object) {
		if (!(object instanceof SqlPackageOrderClusterDO)) {
			// TODO 错误日志
			System.out.println("require PackageOrderClusterDO");
			return 0;
		}
		// TODO 用ibatis写入数据库的函数的调用
		return orderStoreDAO.insert((SqlPackageOrderClusterDO) object);
	}

	/**
	 * 根据用户ID查询
	 */
	@Override
	public SqlPackageOrderClusterDO[] selectOrdersByUserId(Integer userId ,Integer offset,Integer length) {
		try {
			return orderStoreDAO.selectOrderByUserId(userId);
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SqlPackageOrderClusterDO selectOrdersByOrderId(Integer orderId) {
		try {
			return orderStoreDAO.selectOrderByOrderId(orderId);
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public SqlPackageOrderClusterDO[] selectOrdersByInfo(Integer userId,Date startDay,Date endDay,String orderStatus, Integer offset,Integer length){
		try {
			return orderStoreDAO.selectOrderByInfo(userId, startDay, endDay, orderStatus);
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public IbatisOrderDataOperation getOrderStoreDAO() {
		return orderStoreDAO;
	}

	public void setOrderStoreDAO(IbatisOrderDataOperation orderStoreDAO) {
		this.orderStoreDAO = orderStoreDAO;
	}

	private SqlSubPackageOrderDO tempSubOrderToSqlSubOrder(TempSubPackageOrderDO tempSub) {
		SqlSubPackageOrderDO ret = new SqlSubPackageOrderDO();
		ret.setPackageAmount(tempSub.getPackageAmount());
		ret.setPackageType(tempSub.getPackageType());
		ret.setParentId(tempSub.getParentId());
		return ret;
	}

	private SqlReflectOrderDO tempReflectOrderToSqlReflectOrder(TempReflectOrderDO tempReflect) {
		SqlReflectOrderDO ret = new SqlReflectOrderDO();
		ret.setItemAmount(tempReflect.getItemAmount());
		ret.setItemType(tempReflect.getItemType());
		ret.setParentId(tempReflect.getParentId());
		return ret;
	}

	private SqlMainPackageOrderDO tempMainOrderToSqlMainOrder(TempMainPackageOrderDO tempMain) {
		SqlMainPackageOrderDO ret = new SqlMainPackageOrderDO();
		ret.setExpectReceiveTime(tempMain.getExpectReceiveTime());
		ret.setLevel(tempMain.getLevel());
		ret.setOrderNote(tempMain.getOrderNote());
		ret.setOrderStatus(tempMain.getOrderStatus());
		ret.setOrderTime(tempMain.getOrderTime());
		ret.setUserId(tempMain.getUserId());
		return ret;
	}

	private SqlSubPackageOrderDO[] tempSubOrderListToSqlSubOrderArray(List<TempSubPackageOrderDO> tempSubs) {
		SqlSubPackageOrderDO[] ret = new SqlSubPackageOrderDO[tempSubs.size()];
		int index = 0;
		for (TempSubPackageOrderDO tempSub : tempSubs) {
			ret[index++] = tempSubOrderToSqlSubOrder(tempSub);
		}
		return ret;
	}

	private SqlReflectOrderDO[] tempReflectOrderListToSqlReflectOrderArray(List<TempReflectOrderDO> tempReflects) {
		SqlReflectOrderDO[] ret = new SqlReflectOrderDO[tempReflects.size()];
		int index = 0;
		for (TempReflectOrderDO tempReflect : tempReflects) {
			ret[index++] = tempReflectOrderToSqlReflectOrder(tempReflect);
		}
		return ret;
	}

	private SqlPackageOrderClusterDO tempClusterToSqlCluster(TempPackageOrderClusterDO tempCluster) {
		SqlPackageOrderClusterDO ret = new SqlPackageOrderClusterDO();
		ret.setMainOrder(tempMainOrderToSqlMainOrder(tempCluster.getMainOrder()));
		ret.setSubOrders(tempSubOrderListToSqlSubOrderArray(tempCluster.getSubOrders()));
		ret.setReflectOrders(tempReflectOrderListToSqlReflectOrderArray(tempCluster.getReflectOrders()));
		return ret;
	}

	public WardenOperator<Integer> getTransportOrderMessagePusher() {
		return transportOrderMessagePusher;
	}

	public void setTransportOrderMessagePusher(WardenOperator<Integer> transportOrderMessagePusher) {
		this.transportOrderMessagePusher = transportOrderMessagePusher;
	}

}
