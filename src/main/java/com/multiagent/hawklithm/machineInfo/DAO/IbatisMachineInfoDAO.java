package com.multiagent.hawklithm.machineInfo.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.machineInfo.DO.MachineInfoDO;

public class IbatisMachineInfoDAO {
	private IbatisManagerModule ibatisManager;

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public int equipmentDetailSubmit(Date gmtBuy, Date gmtLastRepair,  Integer equimpmentId, String manufacturer, String detail) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gmtBuy", gmtBuy);
		paramMap.put("gmtLastRepair", gmtLastRepair);
		paramMap.put("equimpmentId", equimpmentId);
		paramMap.put("manufacturer", manufacturer);
		paramMap.put("detail", detail);
		return (int) ibatisManager.insert("IbatisMachineInfoDAO.equipmentDetailSubmit", paramMap);

	}

	public int equipmentDetailModify(Integer id, Date gmtLastRepair,  String detail) throws DataAccessException {
		Assert.notNull(id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("gmtLastRepair", gmtLastRepair);
		paramMap.put("detail", detail);
		return ibatisManager.update("IbatisMachineInfoDAO.equipmentDetailModify", paramMap);
	}

	public List<MachineInfoDO> equipmentDetailQuery(Integer id, Date gmtBuyStart, Date gmtBuyEnd, Date gmtLastRepairStart, Date gmtLastRepairEnd, String equipmentType, Integer equimpmentId, String manufacturer,
			String detail,Integer offset,Integer length) throws DataAccessException {
		Assert.notNull(length);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("gmtBuyStart", gmtBuyStart);
		paramMap.put("gmtBuyEnd", gmtBuyEnd);
		paramMap.put("gmtLastRepairStart", gmtLastRepairStart);
		paramMap.put("gmtLastRepairEnd", gmtLastRepairEnd);
		paramMap.put("equimpmentId", equimpmentId);
		paramMap.put("manufacturer", manufacturer);
		paramMap.put("equipmentType", equipmentType);
		paramMap.put("detail", detail);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<MachineInfoDO>) ibatisManager.select("IbatisMachineInfoDAO.equipmentDetailQuery", paramMap);
	}

	public int equipmentDetailDeleteById(Integer id) throws DataAccessException {
		Assert.notNull(id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return ibatisManager.delete("IbatisMachineInfoDAO.equipmentDetailDeleteById", paramMap);
	}
}
