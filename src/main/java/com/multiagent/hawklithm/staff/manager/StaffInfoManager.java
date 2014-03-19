package com.multiagent.hawklithm.staff.manager;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.staff.DAO.IbatisStaffInfoDAO;
import com.multiagent.hawklithm.staff.DO.StaffInfoDO;
import com.multiagent.hawklithm.staff.interface4rpc.RPCStaffInfoManagerInterface;

/**
 * 
 * @author hawklithm 2013-12-25ÏÂÎç7:45:26
 */
public class StaffInfoManager implements RPCStaffInfoManagerInterface {
	private IbatisStaffInfoDAO ibatisStaffInfoDao;

	@Override
	public boolean delete(int staffId) throws DataAccessException {
		return ibatisStaffInfoDao.deleteById(staffId) != 0;
	}

	@Override
	public boolean modify(Integer staffId, String staffName, String staffPhoneNumber,
			String staffGender, Integer staffAge, String staffDepartmentId)
			throws DataAccessException {
		return ibatisStaffInfoDao.modify(staffId, staffName, staffPhoneNumber, staffGender,
				staffAge, staffDepartmentId) != 0;
	}

	@Override
	public StaffInfoDO[] queryById(int staffId) throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(staffId, null, null, null, null, null,
				null, null, null);
		return ret.toArray(new StaffInfoDO[ret.size()]);
	}

	@Override
	public StaffInfoDO[] queryByAge(Integer ageStart, Integer ageEnd, Integer offset, Integer length)
			throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(null, null, null, null, ageStart, ageEnd,
				null, offset, length);
		return ret.toArray(new StaffInfoDO[ret.size()]);
	}

	@Override
	public StaffInfoDO[] queryByGender(String gender, Integer offset, Integer length)
			throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(null, null, null, gender, null, null,
				null, offset, length);
		return ret.toArray(new StaffInfoDO[ret.size()]);
	}

	@Override
	public StaffInfoDO[] queryByName(String name, Integer offset, Integer length)
			throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(null, name, null, null, null, null, null,
				offset, length);
		return ret.toArray(new StaffInfoDO[ret.size()]);
	}

	@Override
	public StaffInfoDO[] queryByAllInfo(Integer staffId, String staffName, String staffPhoneNumber,
			String staffGender, Integer staffAgeStart, Integer staffAgeEnd,
			String staffDepartmentId, Integer offset, Integer length) throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(staffId, staffName, staffPhoneNumber,
				staffGender, staffAgeStart, staffAgeEnd, staffDepartmentId, offset, length);
		return ret.toArray(new StaffInfoDO[ret.size()]);
	}

	public IbatisStaffInfoDAO getIbatisStaffInfoDao() {
		return ibatisStaffInfoDao;
	}

	public void setIbatisStaffInfoDao(IbatisStaffInfoDAO ibatisStaffInfoDao) {
		this.ibatisStaffInfoDao = ibatisStaffInfoDao;
	}

	@Override
	public Integer submit(String staffName, String staffPhoneNumber, String staffGender,
			Integer staffAge, String staffDepartmentId) throws DataAccessException {
		return ibatisStaffInfoDao.submit(staffName, staffPhoneNumber, staffGender, staffAge,
				staffDepartmentId);
	}

}
