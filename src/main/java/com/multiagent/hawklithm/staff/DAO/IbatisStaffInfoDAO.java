package com.multiagent.hawklithm.staff.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.staff.DO.StaffInfoDO;

/**
 * private Integer staffId; private Date gmtCreate; private Date gmtModified;
 * private String staffName; private String staffPhoneNumber; private String
 * staffGender; private Integer staffAge; private String staffDepartmentId;
 * 
 * @author hawklithm 2013-12-25ÏÂÎç6:38:48
 */
public class IbatisStaffInfoDAO {
	private IbatisManagerModule ibatisManager;

	public int submit(String staffName, String staffPhoneNumber, String staffGender,
			Integer staffAge, String staffDepartmentId) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("staffName", staffName);
		paramMap.put("staffPhoneNumber", staffPhoneNumber);
		paramMap.put("staffGender", staffGender);
		paramMap.put("staffAge", staffAge);
		paramMap.put("staffDepartmentId", staffDepartmentId);
		return (int) ibatisManager.insert("IbatisStaffInfoDAO.submit", paramMap);

	}

	public int modify(Integer staffId, String staffName, String staffPhoneNumber,
			String staffGender, Integer staffAge, String staffDepartmentId)
			throws DataAccessException {
		Assert.notNull(staffId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("staffPhoneNumber", staffPhoneNumber);
		paramMap.put("staffGender", staffGender);
		paramMap.put("staffAge", staffAge);
		paramMap.put("staffDepartmentId", staffDepartmentId);
		return ibatisManager.update("IbatisStaffInfoDAO.modify", paramMap);
	}

	public List<StaffInfoDO> query(Integer staffId, String staffName, String staffPhoneNumber,
			String staffGender, Integer staffAgeStart, Integer staffAgeEnd,
			String staffDepartmentId, Integer offset, Integer length) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("staffId", staffId);
		paramMap.put("staffName", staffName);
		paramMap.put("staffPhoneNumber", staffPhoneNumber);
		paramMap.put("staffGender", staffGender);
		paramMap.put("staffAgeStart", staffAgeStart);
		paramMap.put("staffAgeEnd", staffAgeEnd);
		paramMap.put("staffDepartmentId", staffDepartmentId);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		return (List<StaffInfoDO>) ibatisManager.select("IbatisStaffInfoDAO.query", paramMap);
	}

	public int deleteById(Integer staffId) throws DataAccessException {
		Assert.notNull(staffId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("staffId", staffId);
		return ibatisManager.delete("IbatisStaffInfoDAO.deleteById", paramMap);
	}

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}
}
