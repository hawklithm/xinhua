package com.multiagent.hawklithm.vehicle.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.vehicle.DO.VehicleInfoDO;
/**
 * private Integer vehicleId;
	private Date gmtCreate;
	private Date gmtModified;
	private String vehicleType;
	private Integer vehicleLevel;
	private double vehicleCapacity;
 * @author hawklithm
 * 2013-12-25ÏÂÎç4:06:03
 */
public class IbatisVehicleInfoDAO {
	private IbatisManagerModule ibatisManager;

	public int submit(String vehicleType, Integer vehicleLevel, Double vehicleCapacity)
			throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vehicleType", vehicleType);
		paramMap.put("vehicleLevel", vehicleLevel);
		paramMap.put("vehicleCapacity", vehicleCapacity);
		return (int) ibatisManager.insert("IbatisVehicleInfoDAO.submit", paramMap);

	}

	public int modify(Integer vehicleId, String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity) throws DataAccessException {
		Assert.notNull(vehicleId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vehicleId", vehicleId);
		paramMap.put("vehicleType", vehicleType);
		paramMap.put("vehicleLevel", vehicleLevel);
		paramMap.put("vehicleCapacity", vehicleCapacity);
		return ibatisManager.update("IbatisVehicleInfoDAO.modify", paramMap);
	}

	public List<VehicleInfoDO> query(Integer vehicleId, String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity, Integer offset, Integer length) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vehicleId", vehicleId);
		paramMap.put("vehicleType", vehicleType);
		paramMap.put("vehicleLevel", vehicleLevel);
		paramMap.put("vehicleCapacity", vehicleCapacity);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<VehicleInfoDO>) ibatisManager.select("IbatisVehicleInfoDAO.query", paramMap);
	}

	public int deleteById(Integer id) throws DataAccessException {
		Assert.notNull(id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return ibatisManager.delete("IbatisVehicleInfoDAO.deleteById", paramMap);
	}

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}
}
