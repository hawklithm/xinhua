package com.multiagent.hawklithm.staff.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.machineInfo.DAO.IbatisEquipmentStaffMappingDAO;
import com.multiagent.hawklithm.machineInfo.DO.EquipmentStaffMappingDO;
import com.multiagent.hawklithm.staff.DAO.IbatisStaffInfoDAO;
import com.multiagent.hawklithm.staff.DO.StaffInfoDO;
import com.multiagent.hawklithm.staff.interface4rpc.RPCStaffInfoManagerInterface;

/**
 * 
 * @author hawklithm 2013-12-25ÏÂÎç7:45:26
 */
public class StaffInfoManager implements RPCStaffInfoManagerInterface {
	private IbatisStaffInfoDAO ibatisStaffInfoDao;
	private IbatisEquipmentStaffMappingDAO ibatisEquipmentStaffMappingDAO;

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
			String staffDepartmentName, Integer offset, Integer length) throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(staffId, staffName, staffPhoneNumber,
				staffGender, staffAgeStart, staffAgeEnd, staffDepartmentName, offset, length);
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

	@Override
	public StaffInfoDO[] queryByEquipmentId(Integer equipmentId) {
		List<EquipmentStaffMappingDO>mappings=ibatisEquipmentStaffMappingDAO.queryMapping(equipmentId, null, 0, 15);
		List<StaffInfoDO> staffInfos=new ArrayList<StaffInfoDO>(mappings.size());
		for (EquipmentStaffMappingDO mapping:mappings){
			staffInfos.add(ibatisStaffInfoDao.query(mapping.getStaffId(), null, null, null, null, null, null, 0, 1).get(0));
		}
		return staffInfos.toArray(new StaffInfoDO[staffInfos.size()]);
	}

	public IbatisEquipmentStaffMappingDAO getIbatisEquipmentStaffMappingDAO() {
		return ibatisEquipmentStaffMappingDAO;
	}

	public void setIbatisEquipmentStaffMappingDAO(
			IbatisEquipmentStaffMappingDAO ibatisEquipmentStaffMappingDAO) {
		this.ibatisEquipmentStaffMappingDAO = ibatisEquipmentStaffMappingDAO;
	}

}
