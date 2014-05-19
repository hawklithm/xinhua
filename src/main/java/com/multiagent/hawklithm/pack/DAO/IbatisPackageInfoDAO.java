package com.multiagent.hawklithm.pack.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.pack.dataobject.PackageInfoDO;

/**
 * 
 * @author hawklithm
 * 2014-5-19ÏÂÎç4:16:32
 */
public class IbatisPackageInfoDAO {
	private IbatisManagerModule ibatisManager;

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public void submit(String packageName, Integer packageType, Integer hospitalId, Date packTime,
			Date expiryDate, String remark,Integer staffId) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageName", packageName);
		paramMap.put("packageType", packageType);
		paramMap.put("packTime", packTime);
		paramMap.put("expiryDate", expiryDate);
		paramMap.put("staffId", staffId);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("remark", remark);
		ibatisManager.insert("IbatisPackageInfoDAO.submit", paramMap);

	}

	public int deleteById(Integer packageId) throws DataAccessException {
		Assert.notNull(packageId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		return ibatisManager.delete("IbatisPackageInfoDAO.deleteById", paramMap);
	}

	public List<PackageInfoDO> query(Integer packageId, Date startTime, Date endTime, Integer packageType,
			Integer hospitalId, Integer staffId, Integer offset,
			Integer length) throws DataAccessException {
		Assert.notNull(length);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("packageType", packageType);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("staffId", staffId);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<PackageInfoDO>) ibatisManager.select("IbatisPackageInfoDAO.query", paramMap);
	}
}
