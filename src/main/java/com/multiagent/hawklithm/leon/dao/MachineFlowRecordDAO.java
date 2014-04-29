package com.multiagent.hawklithm.leon.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.DO.SqlReaderAtEquipmentDO;

/*
 * 流水线上仪器记录
 * RFID数据的插入数据库
 * 查询历史记录
 * 插入历史记录
 * 
 */

public class MachineFlowRecordDAO {
	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;

	public Integer insertOriginalRFIDData(final Date time, final int rfid, final int readerId)
			throws SQLException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("time", time);
					map.put("rfid", rfid);
					map.put("readerId", readerId);
					return (Integer) ibatisManager.insert(
							"MachineFlowRecordDAO.insertOriginalRFIDData", map);
				} catch (Exception e) {
					// TODO 打印日志
					status.setRollbackOnly();
					throw e;
				}
			}

		});

	}

	public List<SqlReaderAtEquipmentDO> selectHistoryInfo(Integer id, Date startTime, Date endTime,
			Integer equipmentId, Integer packageId, Integer itemId, Integer readerId,
			Integer cameraId, Integer staffId) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("equipmentId", equipmentId);
		map.put("packageId", packageId);
		map.put("itemId", itemId);
		map.put("readerId", readerId);
		map.put("cameraId", cameraId);
		map.put("staffId", staffId);
		map.put("id", id);
		return (List<SqlReaderAtEquipmentDO>) ibatisManager.select(
				"MachineFlowRecordDAO.selectHistoryInfo", map);
	}
	
	public List<SqlReaderAtEquipmentDO> selectTodaysHistoryInfo(Date startTime,
			String equipmentCondition) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("equipmentCondition", equipmentCondition);
		return (List<SqlReaderAtEquipmentDO>) ibatisManager.select(
				"MachineFlowRecordDAO.selectTodaysHistoryInfo", map);
	}
	


	public Integer insertObject(final Integer itemId, final Integer packageId,
			final Integer cameraId, final Integer machineId, final Integer readerId,
			final Date date, final Integer staffId) throws SQLException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("itemId", itemId);
					map.put("packageId", packageId);
					map.put("cameraId", cameraId);
					map.put("equipmentId", machineId);
					map.put("readerId", readerId);
					map.put("date", date);
					map.put("staffId", staffId);
					return (Integer) ibatisManager.insert("MachineFlowRecordDAO.insertObject", map);
				} catch (Exception e) {
					// TODO 打印日志
					status.setRollbackOnly();
					throw e;
				}
			}
		});

	}

	public SqlEquipCamReaderMappingDO selectMapping(Integer readerId, Integer cameraId,
			Integer equipmentId) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("readerId", readerId);
		map.put("cameraId", cameraId);
		map.put("equipmentId", equipmentId);
		return (SqlEquipCamReaderMappingDO) ibatisManager.select(
				"MachineFlowRecordDAO.selectMapping", map);
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
