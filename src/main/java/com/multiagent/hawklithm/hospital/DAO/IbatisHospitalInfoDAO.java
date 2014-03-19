package com.multiagent.hawklithm.hospital.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.hospital.DO.HospitalInfoDO;
import com.multiagent.hawklithm.ibatis.IbatisManagerModule;

public class IbatisHospitalInfoDAO {
	private IbatisManagerModule ibatisManager;

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public void submit(String hospitalName, Integer hospitalLevel, String hospitalAddress,
			String hospitalAgent, String agentPhone) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hospitalName", hospitalName);
		paramMap.put("hospitalLevel", hospitalLevel);
		paramMap.put("hospitalAddress", hospitalAddress);
		paramMap.put("hospitalAgent", hospitalAgent);
		paramMap.put("agentPhone", agentPhone);
		ibatisManager.insert("IbatisHospitalInfoDAO.submit", paramMap);

	}

	public int modify(Integer hospitalId, String hospitalName, Integer hospitalLevel,
			String hospitalAddress, String hospitalAgent, String agentPhone)
			throws DataAccessException {
		Assert.notNull(hospitalId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("hospitalName", hospitalName);
		paramMap.put("hospitalLevel", hospitalLevel);
		paramMap.put("hospitalAddress", hospitalAddress);
		paramMap.put("hospitalAgent", hospitalAgent);
		paramMap.put("agentPhone", agentPhone);
		return ibatisManager.update("IbatisHospitalInfoDAO.modify", paramMap);
	}

	public List<HospitalInfoDO> query(Integer hospitalId, String hospitalName,
			Integer hospitalLevel, String hospitalAddress, String hospitalAgent, String agentPhone,
			Integer offset, Integer length) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("hospitalName", hospitalName);
		paramMap.put("hospitalLevel", hospitalLevel);
		paramMap.put("hospitalAddress", hospitalAddress);
		paramMap.put("hospitalAgent", hospitalAgent);
		paramMap.put("agentPhone", agentPhone);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<HospitalInfoDO>) ibatisManager.select("IbatisHospitalInfoDAO.query", paramMap);
	}

	public int deleteById(Integer userId) throws DataAccessException {
		Assert.notNull(userId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return ibatisManager.delete("IbatisHospitalInfoDAO.deleteById", paramMap);
	}
}
