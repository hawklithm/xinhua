package com.multiagent.hawklithm.sephiroth.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.multiagent.hawklithm.davinci.exceptioin.OrderNotFoundException;
import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.sephiroth.DO.SqlMainPackageOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.SqlPackageOrderClusterDO;
import com.multiagent.hawklithm.sephiroth.DO.SqlReflectOrderDO;
import com.multiagent.hawklithm.sephiroth.DO.SqlSubPackageOrderDO;

/**
 * 只提供订单删除和添加操作，不允许修改，可查询
 * 
 * @author hawklithm
 * 
 */
public class IbatisOrderDataOperation {

	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;

	/**
	 * 删除订单
	 * 
	 * @param orderId
	 */
	public boolean delete(final int orderId) {
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					deleteSubOrderByParentId(orderId);
					deleteReflectOrderByParentId(orderId);
					deleteMainOrderById(orderId);
				} catch (Exception e) {
					status.setRollbackOnly();
					return false;
					// TODO 打印日志
				}
				return true;
			}

		});
	}

	/**
	 * 删除主订单
	 * 
	 * @param orderId
	 */
	private void deleteMainOrderById(int orderId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		ibatisManager.delete("IbatisOrderDataOperation.deleteMainOrderById", map);
	}

	/**
	 * 根据主订单id删除子订单
	 * 
	 * @param orderId
	 */
	private void deleteSubOrderByParentId(int orderId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", orderId);
		ibatisManager.delete("IbatisOrderDataOperation.deleteSubOrderByParentId", map);
	}

	/**
	 * 根据主订单id删除返件子订单
	 * 
	 * @param orderId
	 */
	private void deleteReflectOrderByParentId(int orderId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", orderId);
		ibatisManager.delete("IbatisOrderDataOperation.deleteReflectOrderByParentId", map);
	}

	/**
	 * 添加订单
	 * 
	 * @param orderCluster
	 */
	public int insert(final SqlPackageOrderClusterDO orderCluster) {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				int id = 0;
				try {
					SqlMainPackageOrderDO mainOrder = orderCluster.getMainOrder();
					SqlSubPackageOrderDO[] subOrders = orderCluster.getSubOrders();
					SqlReflectOrderDO[] reflectOrders = orderCluster.getReflectOrders();
					id = insertMainOrder(mainOrder);
					int len = subOrders.length;
					for (int i = 0; i < len; i++) {
						subOrders[i].setParentId(id);
						insertSubOrder(subOrders[i]);
					}
					len = reflectOrders.length;
					for (int i = 0; i < len; i++) {
						reflectOrders[i].setParentId(id);
						insertReflectOrder(reflectOrders[i]);
					}
				} catch (Exception e) {
					status.setRollbackOnly();
					return 0;
					// TODO 打印日志
				}
				return id;
			}

		});

	}

	/**
	 * 根据订单信息查询订单
	 * @param userId
	 * @param startDay
	 * @param endDay
	 * @param state
	 * @return
	 * @throws OrderNotFoundException
	 */
	public SqlPackageOrderClusterDO[] selectOrderByInfo(Integer userId,Date startDay, Date endDay,String orderStatus) throws OrderNotFoundException {
		List<SqlMainPackageOrderDO> mainOrders = selectMainOrder(null, userId, startDay, endDay, orderStatus);
		SqlPackageOrderClusterDO[] ret = new SqlPackageOrderClusterDO[mainOrders.size()];
		if (!CollectionUtils.isEmpty(mainOrders)) {
			int index = 0;
			for (SqlMainPackageOrderDO mainOrder : mainOrders) {
				List<SqlSubPackageOrderDO> subOrders = selectSubOrder(mainOrder.getOrderId());
				List<SqlReflectOrderDO> reflectOrders = selectReflectOrder(mainOrder.getOrderId());
				SqlPackageOrderClusterDO tmp = new SqlPackageOrderClusterDO();
				tmp.setMainOrder(mainOrder);
				tmp.setSubOrders(subOrders.toArray(new SqlSubPackageOrderDO[subOrders.size()]));
				tmp.setReflectOrders(reflectOrders.toArray(new SqlReflectOrderDO[reflectOrders.size()]));
				ret[index++] = tmp;
			}
		}
		return ret;
	}

	/**
	 * 根据订单标号查询
	 * 
	 * @param orderId
	 * @return
	 * @throws OrderNotFoundException
	 */
	public SqlPackageOrderClusterDO selectOrderByOrderId(Integer orderId) throws OrderNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		SqlMainPackageOrderDO mainOrder = null;
		SqlPackageOrderClusterDO ret = null;
		map.put("orderId", orderId);
		List<SqlMainPackageOrderDO> mainOrderList = selectMainOrder(orderId, null, null, null, null);
		if (!CollectionUtils.isEmpty(mainOrderList)) {
			mainOrder = mainOrderList.get(0);
			List<SqlSubPackageOrderDO> subOrders = selectSubOrder(mainOrder.getOrderId());
			List<SqlReflectOrderDO> reflectOrders = selectReflectOrder(mainOrder.getOrderId());
			ret = new SqlPackageOrderClusterDO();
			ret.setMainOrder(mainOrder);
			ret.setSubOrders(subOrders.toArray(new SqlSubPackageOrderDO[subOrders.size()]));
			ret.setReflectOrders(reflectOrders.toArray(new SqlReflectOrderDO[subOrders.size()]));
		}
		return ret;
	}

	/**
	 * 根据用户编号查询订单
	 * 
	 * @param userId
	 * @return
	 * @throws OrderNotFoundException
	 *  @deprecated 建议使用{@link IbatisOrderDataOperation#selectOrderByInfo(Integer, Date, Date, String)
	 */
	public SqlPackageOrderClusterDO[] selectOrderByUserId(Integer userId) throws OrderNotFoundException {
		List<SqlMainPackageOrderDO> mainOrders = selectMainOrder(null, userId, null, null, null);
		SqlPackageOrderClusterDO[] ret = null;
		if (CollectionUtils.isEmpty(mainOrders)) {
			ret = new SqlPackageOrderClusterDO[mainOrders.size()];
			int index = 0;
			for (SqlMainPackageOrderDO mainOrder : mainOrders) {
				List<SqlSubPackageOrderDO> subOrders = selectSubOrder(mainOrder.getOrderId());
				List<SqlReflectOrderDO> reflectOrders = selectReflectOrder(mainOrder.getOrderId());
				SqlPackageOrderClusterDO tmp = new SqlPackageOrderClusterDO();
				tmp.setMainOrder(mainOrder);
				tmp.setSubOrders(subOrders.toArray(new SqlSubPackageOrderDO[subOrders.size()]));
				tmp.setReflectOrders(reflectOrders.toArray(new SqlReflectOrderDO[reflectOrders.size()]));
				ret[index++] = tmp;
			}
		}
		return ret;
	}

	/**
	 * 查询主订单
	 * 
	 * @param orderId
	 * @param userId
	 * @param time
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SqlMainPackageOrderDO> selectMainOrder(Integer orderId, Integer userId, Date startTime, Date endTime, String orderStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("userId", userId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("orderStatus", orderStatus);
		List<SqlMainPackageOrderDO> list = (List<SqlMainPackageOrderDO>) ibatisManager.select("IbatisOrderDataOperation.selectMainOrder", map);
		return list;
	}

	/**
	 * 查询子订单
	 * 
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SqlSubPackageOrderDO> selectSubOrder(Integer parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		return (List<SqlSubPackageOrderDO>) ibatisManager.select("IbatisOrderDataOperation.selectSubOrder", map);
	}

	/**
	 * 查询返件子订单
	 * 
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SqlReflectOrderDO> selectReflectOrder(Integer parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		return (List<SqlReflectOrderDO>) ibatisManager.select("IbatisOrderDataOperation.selectReflectOrder", map);
	}

	/**
	 * 插入主订单
	 * 
	 * @param mainOrder
	 * @return
	 */
	private int insertMainOrder(SqlMainPackageOrderDO mainOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", mainOrder.getUserId());
		map.put("orderTime", mainOrder.getOrderTime());
		map.put("orderStatus", mainOrder.getOrderStatus());
		map.put("expectReceiveTime", mainOrder.getExpectReceiveTime());
		map.put("level", mainOrder.getLevel());
		map.put("orderNote", mainOrder.getOrderNote());
		return (int) ibatisManager.insert("IbatisOrderDataOperation.insertMainOrder", map);
	}

	/**
	 * 查询子订单
	 * 
	 * @param subOrder
	 */
	private void insertSubOrder(SqlSubPackageOrderDO subOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", subOrder.getParentId());
		map.put("packageType", subOrder.getPackageType());
		map.put("packageAmount", subOrder.getPackageAmount());
		ibatisManager.insert("IbatisOrderDataOperation.insertSubOrder", map);
	}

	/**
	 * 查询返件子订单
	 * 
	 * @param reflectOrder
	 */
	private void insertReflectOrder(SqlReflectOrderDO reflectOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", reflectOrder.getParentId());
		map.put("itemType", reflectOrder.getItemType());
		map.put("itemAmount", reflectOrder.getItemAmount());
		ibatisManager.insert("IbatisOrderDataOperation.insertReflectOrder", map);
	}

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
}
