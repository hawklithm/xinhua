package com.multiagent.hawklithm.transport.manager;

import java.util.Date;
import java.util.List;

import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenManager;
import com.multiagent.hawklithm.transport.DAO.TransportOrderDataOperation;
import com.multiagent.hawklithm.transport.DO.SqlTransportOrderClusterDO;
import com.multiagent.hawklithm.transport.DO.TempTransportOrderClusterDO;
import com.multiagent.hawklithm.transport.interface4rpc.RPCTransportOrderManagerInterface;

/**
 * 使用前先调用init()进行初始化以及消息系统注册 接收由TransportOrderMessagePusher发送过来的消息，
 * 类型为WardenMessage.KIND_ORDER_READY_FOR_TRANSPORT+WardenMessage.DIR_ENTER，
 * 消息目标WardenMessage.TARGET_TYPE_SYSTEM_MODULE
 * 
 * @author hawklithm 2013-12-17下午4:59:51
 */
public class TransportOrderManager implements RPCTransportOrderManagerInterface {
	private WardenManager wardenManager;
	private TransportOrderDataOperation transportOrderDAO;

	public void init() {
		this.regist(new Warden(WardenMessage.TARGET_TYPE_SYSTEM_MODULE,
				WardenMessage.KIND_ORDER_READY_FOR_TRANSPORT + WardenMessage.DIR_ENTER) {

			@Override
			public void asynchronizedProcess(String message) {
				// // 消息内容只包含订单ID
				// Integer orderId = Integer.valueOf(message);
				// // 修改订单状态
			}

		});
	}

	@Override
	public SqlTransportOrderClusterDO[] queryOrderByInfo(Date startTime, Date finishTime,
			String startAddress, String endAddress, Integer vehicleId) throws Exception {
		List<SqlTransportOrderClusterDO> ret = transportOrderDAO.selectTransportOrderClusterByInfo(
				startTime, finishTime, startAddress, endAddress, vehicleId);
		return ret.toArray(new SqlTransportOrderClusterDO[ret.size()]);
	}

	@Override
	public SqlTransportOrderClusterDO queryOrderByTransportOrderId(int id) throws Exception {
		return transportOrderDAO.selectTransportOrderClusterById(id);
	}

	@Override
	public SqlTransportOrderClusterDO queryOrderByOrderId(int id) throws Exception {
		return transportOrderDAO.selectTransportOrderClusterByOrderId(id);
	}

	@Override
	public void submitOrder(TempTransportOrderClusterDO clusterDO) throws Exception {
		transportOrderDAO.insertTransportOrderCluster(clusterDO);
	}

	@Override
	public void deleteOrder(int orderId) throws Exception {
		transportOrderDAO.deleteTransportOrderClusterById(orderId);
	}

	private void regist(Warden waden) {
		wardenManager.regist(waden);
	}

	public WardenManager getWardenManager() {
		return wardenManager;
	}

	public void setWardenManager(WardenManager wardenManager) {
		this.wardenManager = wardenManager;
	}

	public TransportOrderDataOperation getTransportOrderDAO() {
		return transportOrderDAO;
	}

	public void setTransportOrderDAO(TransportOrderDataOperation transportOrderDAO) {
		this.transportOrderDAO = transportOrderDAO;
	}

}
