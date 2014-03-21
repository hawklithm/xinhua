package com.multiagent.hawklithm.transport.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.transport.DO.SqlTransportMainOrderDO;
import com.multiagent.hawklithm.transport.DO.SqlTransportOrderClusterDO;
import com.multiagent.hawklithm.transport.DO.SqlTransportSubOrderDO;
import com.multiagent.hawklithm.transport.DO.TempTransportMainOrderDO;
import com.multiagent.hawklithm.transport.DO.TempTransportOrderClusterDO;
import com.multiagent.hawklithm.transport.DO.TempTransportSubOrderDO;

/**
 * 运单DAO
 * 
 * @author hawklithm 2013-12-19上午10:50:27
 */
public class TransportOrderDataOperation {
	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;

	/**
	 * 删除订单簇
	 * 
	 * @param orderId
	 * @return
	 */
	public boolean deleteTransportOrderClusterById(final Integer orderId) throws Exception {
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					deleteTransportSubOrderById(orderId);
					deleteTransportMainOrderById(orderId);
				} catch (Exception e) {
					status.setRollbackOnly();
					// return false;
					try {
						throw e;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return true;
			}

		});
	}

	/**
	 * 删除主订单
	 * 
	 * @param parentId
	 * @return
	 */
	private boolean deleteTransportSubOrderById(Integer parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		ibatisManager.delete("TransportOrderDataOperation.deleteTransportSubOrderById", map);
		return true;
	}

	/**
	 * 删除子订单
	 * 
	 * @param orderId
	 * @return
	 */
	private boolean deleteTransportMainOrderById(Integer orderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		ibatisManager.delete("TransportOrderDataOperation.deleteTransportMainOrderById", map);
		return true;
	}

	/**
	 * 根据订单号查询运单
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public SqlTransportOrderClusterDO selectTransportOrderClusterByOrderId(Integer orderId) throws Exception {
		List<SqlTransportMainOrderDO> mainOrders = selectTransportMainOrder(null, orderId, null, null, null, null);
		if (mainOrders.size() > 1) {
			throw new Exception("内部逻辑错误,运单号出现重复");
		}
		if (mainOrders.size() <= 0) {
			return new SqlTransportOrderClusterDO();
		}
		SqlTransportMainOrderDO mainOrder = mainOrders.get(0);
		List<SqlTransportSubOrderDO> subOrders = selectTransportSubOrder(mainOrder.getId(), null, null);
		SqlTransportOrderClusterDO cluster = new SqlTransportOrderClusterDO();
		cluster.setMainOrder(mainOrder);
		cluster.setSubOrder(subOrders);
		return cluster;
	}

	/**
	 * 根据运单ID查询运单
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public SqlTransportOrderClusterDO selectTransportOrderClusterById(Integer orderId) throws Exception {
		List<SqlTransportMainOrderDO> mainOrders = selectTransportMainOrder(orderId, null, null, null, null, null);
		if (mainOrders.size() > 1) {
			throw new Exception("内部逻辑错误,运单号出现重复");
		}
		if (mainOrders.size() <= 0) {
			return null;
		}
		SqlTransportMainOrderDO mainOrder = mainOrders.get(0);
		List<SqlTransportSubOrderDO> subOrders = selectTransportSubOrder(mainOrder.getId(), null, null);
		SqlTransportOrderClusterDO cluster = new SqlTransportOrderClusterDO();
		cluster.setMainOrder(mainOrder);
		cluster.setSubOrder(subOrders);
		return cluster;
	}

	public List<SqlTransportOrderClusterDO> selectTransportOrderClusterByInfo(Date startTime, Date finishTime, String startAddress, String endAddress, Integer vehicleId) throws Exception {
		List<SqlTransportOrderClusterDO> ret = new ArrayList<SqlTransportOrderClusterDO>();
		if (vehicleId != null) {
			List<SqlTransportSubOrderDO> subOrders = selectTransportSubOrder(null, vehicleId, null);
			Map<Integer, List<SqlTransportSubOrderDO>> transportOrderToSubOrderListMapping = new HashMap<Integer, List<SqlTransportSubOrderDO>>();
			for (SqlTransportSubOrderDO subOrder : subOrders) {
				if (!transportOrderToSubOrderListMapping.containsKey(subOrder.getParentId())) {
					transportOrderToSubOrderListMapping.put(subOrder.getParentId(), new ArrayList<SqlTransportSubOrderDO>());
				}
				transportOrderToSubOrderListMapping.get(subOrder.getParentId()).add(subOrder);
			}
			// List<SqlTransportMainOrderDO> retOrders = new
			// LinkedList<SqlTransportMainOrderDO>();
			for (Iterator<Entry<Integer, List<SqlTransportSubOrderDO>>> iter = transportOrderToSubOrderListMapping.entrySet().iterator(); iter.hasNext();) {
				Entry<Integer, List<SqlTransportSubOrderDO>> entry = iter.next();
				Integer transportOrderId = entry.getKey();
				List<SqlTransportSubOrderDO> subs = entry.getValue();
				List<SqlTransportMainOrderDO> mainOrder = selectTransportMainOrder(transportOrderId, null, startTime, finishTime, startAddress, endAddress);
				if (!mainOrder.isEmpty()) {
					if (mainOrder.size() == 1) {
						SqlTransportOrderClusterDO cluster = new SqlTransportOrderClusterDO();
						cluster.setMainOrder(mainOrder.get(0));
						cluster.setSubOrder(subs);
						ret.add(cluster);
					} else {
						throw new Exception("内部逻辑错误，运单号出现重复");
					}
				}
			}
		} else {
			List<SqlTransportMainOrderDO> mainOrders = selectTransportMainOrder(null, null, startTime, finishTime, startAddress, endAddress);
			for (SqlTransportMainOrderDO mainOrder : mainOrders) {
				List<SqlTransportSubOrderDO> subOrders = selectTransportSubOrder(mainOrder.getId(), null, null);
				SqlTransportOrderClusterDO cluster = new SqlTransportOrderClusterDO();
				cluster.setMainOrder(mainOrder);
				cluster.setSubOrder(subOrders);
				ret.add(cluster);
			}
		}
		return ret;
	}

	/**
	 * 查询主运单
	 * 
	 * @param id
	 * @param orderId
	 * @param startTime
	 * @param endTime
	 * @param startAddress
	 * @param endAddress
	 * @return
	 */
	private List<SqlTransportMainOrderDO> selectTransportMainOrder(Integer id, Integer orderId, Date startTime, Date endTime, String startAddress, String endAddress) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orderId", orderId);
		map.put("startTime", startTime);
		map.put("finishTime", endTime);
		map.put("startAddress", startAddress);
		map.put("endAddress", endAddress);
		return (List<SqlTransportMainOrderDO>) ibatisManager.select("TransportOrderDataOperation.selectTransportMainOrder", map);
	}

	/**
	 * 查询子运单
	 * 
	 * @param parentId
	 * @param vehicleId
	 * @param subOrderId
	 * @return
	 */
	private List<SqlTransportSubOrderDO> selectTransportSubOrder(Integer parentId, Integer vehicleId, Integer subOrderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("vehicleId", vehicleId);
		map.put("subOrderId", subOrderId);
		return (List<SqlTransportSubOrderDO>) ibatisManager.select("TransportOrderDataOperation.selectTransportSubOrder", map);
		// List<SqlTransportSubOrderDO> result = (List<SqlTransportSubOrderDO>)
		// ibatisManager.select("TransportOrderDataOperation.selectTransportSubOrder",
		// map);
		// List<SqlTransportSubOrderDO> ret = new
		// ArrayList<SqlTransportSubOrderDO>();
		// for (SqlTransportSubOrderDO index : result) {
		// ret.add(new SqlTransportSubOrderDO(index, null));
		// }
		// return ret;
	}

	/**
	 * 插入订单簇,先插入主订单，主订单的订单编号作为子订单的parentId
	 * 
	 * @param orderCluster
	 * @return
	 */
	public int insertTransportOrderCluster(final TempTransportOrderClusterDO orderCluster) throws Exception {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				TempTransportMainOrderDO mainOrder = orderCluster.getMainOrder();
				int transportOrderId;
				try {
					transportOrderId = insertTransportMainOrder(mainOrder.getOrderId(), mainOrder.getStartTime(), mainOrder.getFinishTime(), mainOrder.getStartAddress(), mainOrder.getEndAddress());
					for (TempTransportSubOrderDO subOrder : orderCluster.getSubOrder()) {
						insertTransportSubOrder(transportOrderId, subOrder.getVehicleId(), subOrder.getSubOrderId());
					}
				} catch (Exception e) {
					status.setRollbackOnly();
					// return -1;
					throw e;
				}
				return transportOrderId;
			}

		});

	}

	/**
	 * 插入主订单
	 * 
	 * @param orderId
	 * @param startTime
	 * @param finishTime
	 * @param startAddress
	 * @param endAddress
	 * @return
	 */
	private int insertTransportMainOrder(Integer orderId, Date startTime, Date finishTime, String startAddress, String endAddress) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("startTime", startTime);
		map.put("finishTime", finishTime);
		map.put("startAddress", startAddress);
		map.put("endAddress", endAddress);
		return (int) ibatisManager.insert("TransportOrderDataOperation.insertTransportMainOrder", map);
	}

	/**
	 * 插入子订单
	 * 
	 * @param parentId
	 * @param vehicleId
	 * @param subOrderId
	 * @return
	 */
	private int insertTransportSubOrder(Integer parentId, Integer vehicleId, Integer subOrderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("vehicleId", vehicleId);
		map.put("subOrderId", subOrderId);
		return (int) ibatisManager.insert("TransportOrderDataOperation.insertTransportSubOrder", map);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}
}
