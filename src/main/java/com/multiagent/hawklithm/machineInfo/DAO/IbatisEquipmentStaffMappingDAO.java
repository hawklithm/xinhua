package com.multiagent.hawklithm.machineInfo.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.machineInfo.DO.EquipmentStaffMappingDO;

public class IbatisEquipmentStaffMappingDAO {
	
	private IbatisManagerModule ibatisManager;

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}
	
	public List<EquipmentStaffMappingDO> queryMapping(Integer equipmentId,Integer staffId,Integer offset,Integer length){
		Assert.notNull(length);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("equipmentId", equipmentId);
		paramMap.put("staffId", staffId);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<EquipmentStaffMappingDO>) ibatisManager.select("IbatisEquipmentStaffMappingDAO.queryMapping", paramMap);
	}
	public int modifyMapping(Integer id,Integer equipmentId,Integer staffId) throws DataAccessException{
		Assert.notNull(id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("equipmentId", equipmentId);
		paramMap.put("staffId", staffId);
		return ibatisManager.update("IbatisEquipmentStaffMappingDAO.modifyMapping", paramMap);
	}
	public int deleteMapping(Integer id) throws DataAccessException{
		Assert.notNull(id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return ibatisManager.delete("IbatisEquipmentStaffMappingDAO.deleteMapping", paramMap);
	}
	public int insertMapping(Integer staffId,Integer equipmentId )throws DataAccessException{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("staffId", staffId);
		paramMap.put("equipmentId", equipmentId);
		return (int) ibatisManager.insert("IbatisEquipmentStaffMappingDAO.insertMapping", paramMap);
	}

}
