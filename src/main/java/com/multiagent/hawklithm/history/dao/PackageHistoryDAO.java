package com.multiagent.hawklithm.history.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.multiagent.hawklithm.history.dataobject.PackageHistoryDO;
import com.multiagent.hawklithm.ibatis.IbatisManagerModule;

public class PackageHistoryDAO {
	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;

	public Integer insertPackageHisotry(final Date time, final Integer packageId,
			final Integer readerId, final Integer cameraId, final String packageStatus,
			final Integer equipmentId) throws SQLException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("time", time);
					map.put("packageId", packageId);
					map.put("readerId", readerId);
					map.put("cameraId", cameraId);
					map.put("packageStatus", packageStatus);
					map.put("equipmentId", equipmentId);
					return (Integer) ibatisManager.insert("PackageHistoryDAO.insertPackageHisotry",
							map);
				} catch (Exception e) {
					// TODO ¥Ú”°»’÷æ
					status.setRollbackOnly();
					throw e;
				}
			}

		});

	}

	public List<PackageHistoryDO> selectPackageHistory(Integer id, Integer packageId,
			Integer readerId, Integer equipmentId, Date startTime, Date endTime)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("equipmentId", equipmentId);
		map.put("packageId", packageId);
		map.put("readerId", readerId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return (List<PackageHistoryDO>) ibatisManager.select(
				"PackageHistoryDAO.selectPackageHistory", map);
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
