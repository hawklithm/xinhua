package com.multiagent.hawklithm.vehicle.manager;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.vehicle.DAO.IbatisVehicleInfoDAO;
import com.multiagent.hawklithm.vehicle.DO.VehicleInfoDO;
import com.multiagent.hawklithm.vehicle.interface4rpc.RPCVehicleInfoManagerInterface;

/**
 * 
 * @author hawklithm 2013-12-25ÏÂÎç7:45:39
 */
public class VehicleInfoManager implements RPCVehicleInfoManagerInterface {
	private IbatisVehicleInfoDAO vehicleInfoDao;

	@Override
	public boolean addVehicleInformation(String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity) throws DataAccessException {
		try {
			vehicleInfoDao.submit(vehicleType, vehicleLevel, vehicleCapacity);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteVehicleInformation(int vehicleId) throws DataAccessException {
		try {
			return vehicleInfoDao.deleteById(vehicleId) != 0;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean modifyVehicleInfomation(Integer vehicleId, String vehicleType,
			Integer vehicleLevel, Double vehicleCapacity) throws DataAccessException {
		try {
			return vehicleInfoDao.modify(vehicleId, vehicleType, vehicleLevel, vehicleCapacity) != 0;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public VehicleInfoDO[] queryById(int vehicleId) throws DataAccessException {
		List<VehicleInfoDO> ans = vehicleInfoDao.query(vehicleId, null, null, null, null, null);
		return ans.toArray(new VehicleInfoDO[ans.size()]);
	}

	@Override
	public VehicleInfoDO[] queryByAllInfo(Integer vehicleId, String vehicleType,
			Integer vehicleLevel, Double vehicleCapacity, Integer offset, Integer length)
			throws DataAccessException {
		List<VehicleInfoDO> ans = vehicleInfoDao.query(vehicleId, vehicleType, vehicleLevel,
				vehicleCapacity, offset, length);
		return ans.toArray(new VehicleInfoDO[ans.size()]);
	}

	public IbatisVehicleInfoDAO getVehicleInfoDao() {
		return vehicleInfoDao;
	}

	public void setVehicleInfoDao(IbatisVehicleInfoDAO vehicleInfoDao) {
		this.vehicleInfoDao = vehicleInfoDao;
	}

}
