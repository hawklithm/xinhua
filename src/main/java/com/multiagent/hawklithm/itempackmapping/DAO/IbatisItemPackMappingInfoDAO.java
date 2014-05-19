package com.multiagent.hawklithm.itempackmapping.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.itempackmapping.dataobject.ItemPackMappingInfoDO;

/**
 * 
 * @author hawklithm
 * 2014-5-19ÏÂÎç4:16:32
 */
public class IbatisItemPackMappingInfoDAO {
	private IbatisManagerModule ibatisManager;

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public void submit(Integer itemId, Integer packageId) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		paramMap.put("packageId", packageId);
		ibatisManager.insert("IbatisItemPackMappingInfoDAO.submit", paramMap);

	}

	public int delete(Integer id,Integer packageId,Integer itemId) throws DataAccessException {
		Assert.notNull(packageId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		paramMap.put("id", id);
		paramMap.put("itemId", itemId);
		return ibatisManager.delete("IbatisItemPackMappingInfoDAO.delete", paramMap);
	}
	
	public List<ItemPackMappingInfoDO> query(Integer id, Integer itemId, Integer packageId, Date startTime,
			Date endTime, Integer offset, Integer length) throws DataAccessException {
		Assert.notNull(length);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("itemId", itemId);
		paramMap.put("packageId", packageId);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<ItemPackMappingInfoDO>) ibatisManager.select("IbatisItemPackMappingInfoDAO.query",
				paramMap);
	}
}
