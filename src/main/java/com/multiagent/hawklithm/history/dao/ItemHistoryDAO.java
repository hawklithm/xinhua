package com.multiagent.hawklithm.history.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.multiagent.hawklithm.history.dataobject.ItemHistoryDO;
import com.multiagent.hawklithm.ibatis.IbatisManagerModule;

public class ItemHistoryDAO {
	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;

	public Integer insertItemHisotry(final Date time, final Integer itemId, final Integer readerId,
			final Integer cameraId, final String itemStatus, final Integer equipmentId)
			throws SQLException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("time", time);
					map.put("itemId", itemId);
					map.put("readerId", readerId);
					map.put("cameraId", cameraId);
					map.put("itemStatus", itemStatus);
					map.put("equipmentId", equipmentId);
					return (Integer) ibatisManager.insert("ItemHistoryDAO.insertItemHisotry", map);
				} catch (Exception e) {
					// TODO ¥Ú”°»’÷æ
					status.setRollbackOnly();
					throw e;
				}
			}

		});

	}

	public List<ItemHistoryDO> selectItemHistory(Integer id, Integer itemId,
			Integer readerId, Integer equipmentId,Date startTime,Date endTime) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("equipmentId", equipmentId);
		map.put("itemId", itemId);
		map.put("readerId", readerId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return (List<ItemHistoryDO>) ibatisManager.select(
				"ItemHistoryDAO.selectItemHistory", map);
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
