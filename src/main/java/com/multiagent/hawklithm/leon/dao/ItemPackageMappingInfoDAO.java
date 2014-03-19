package com.multiagent.hawklithm.leon.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.support.TransactionTemplate;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.leon.DO.ItemPackageMappingDO;

/**
 * 手术包器械对应表tb_item_package_map
 * 
 * @author hawklithm 2013-12-27下午8:07:55
 */
public class ItemPackageMappingInfoDAO {
	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;

	public int mappingInsert(Integer itemId, Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		paramMap.put("packageId", packageId);
		return (int) ibatisManager.insert("ItemPackageMappingInfoDAO.mappingInsert", paramMap);
	}

	public int mappingDelete(Integer id, Integer itemId, Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("itemId", itemId);
		paramMap.put("packageId", packageId);
		return ibatisManager.delete("ItemPackageMappingInfoDAO.mappingDelete", paramMap);
	}

	public List<ItemPackageMappingDO> mappingSelect(Integer id, Integer itemId, Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("itemId", itemId);
		paramMap.put("packageId", packageId);
		return (List<ItemPackageMappingDO>) ibatisManager.select("ItemPackageMappingInfoDAO.mappingSelect", paramMap);
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
