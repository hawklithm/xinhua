package com.multiagent.hawklithm.item.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;

public class IbatisItemInfoDAO {
	private IbatisManagerModule ibatisManager;

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public void submit(String itemName , Integer itemType, Integer hospitalId,
			String manufacturer, boolean interconvertible,String remark) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemName", itemName);
		paramMap.put("itemType", itemType);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("manufacturer", manufacturer);
		paramMap.put("interconvertible", interconvertible);
		paramMap.put("remark", remark);
		ibatisManager.insert("IbatisItemInfoDAO.submit", paramMap);

	}
	public int deleteById(Integer itemId) throws DataAccessException {
		Assert.notNull(itemId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		return ibatisManager.delete("IbatisItemInfoDAO.deleteById", paramMap);
	}

	public List<ItemInfoDO> query(Integer itemId, Date startTime,Date endTime,
			 Integer itemType,Integer hospitalId, String manufacturer,
			boolean interconvertible, Integer offset,Integer length) throws DataAccessException {
		Assert.notNull(length);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("itemType", itemType);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("manufacturer", manufacturer);
		paramMap.put("interconvertible", interconvertible);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<ItemInfoDO>) ibatisManager.select("IbatisItemInfoDAO.query", paramMap);
	}
public ItemInfoDO queryById(Integer itemId){
	Assert.notNull(itemId);
	Map<String,Object> paramMap=new HashMap<String,Object>();
	paramMap.put("itemId",itemId);
	return (ItemInfoDO) ibatisManager.select("IbatisItemInfoDAO.queryById", paramMap);
}
public List<ItemInfoDO> queryByType(Integer itemType){
	Assert.notNull(itemType);
	Map<String,Object> paramMap=new HashMap<String,Object>();
	paramMap.put("itemType",itemType);
	return (List<ItemInfoDO>)ibatisManager.select("IbatisItemInfoDAO.queryByType", paramMap);
}
}
