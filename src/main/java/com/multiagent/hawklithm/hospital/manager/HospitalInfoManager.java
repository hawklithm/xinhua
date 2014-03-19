package com.multiagent.hawklithm.hospital.manager;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.hospital.DAO.IbatisHospitalInfoDAO;
import com.multiagent.hawklithm.hospital.DO.HospitalInfoDO;
import com.multiagent.hawklithm.hospital.interface4rpc.RPCHospitalInfoManagerInterface;

/**
 * 
 * @author hawklithm 2013-12-25ÏÂÎç7:45:54
 */
public class HospitalInfoManager implements RPCHospitalInfoManagerInterface {
	private IbatisHospitalInfoDAO hospitalInfoDao;

	@Override
	public boolean addHospitalInformation(String hospitalName, Integer hospitalLevel,
			String hospitalAddress, String hospitalAgent, String agentPhone)
			throws DataAccessException {
		try {
			hospitalInfoDao.submit(hospitalName, hospitalLevel, hospitalAddress, hospitalAgent,
					agentPhone);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteHopitalInformation(Integer hospitalId) throws DataAccessException {
		try {
			return hospitalInfoDao.deleteById(hospitalId) != 0;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean modifyHospitalInfomation(Integer hospitalId, String hospitalName,
			Integer hospitalLevel, String hospitalAddress, String hospitalAgent, String agentPhone)
			throws DataAccessException {
		try {
			return hospitalInfoDao.modify(hospitalId, hospitalName, hospitalLevel, hospitalAddress,
					hospitalAgent, agentPhone) != 0;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public HospitalInfoDO[] query(Integer hospitalId, String hospitalName, Integer hospitalLevel,
			String hospitalAddress, String hospitalAgent, String agentPhone, Integer offset,
			Integer length) throws DataAccessException {
		List<HospitalInfoDO> ret = hospitalInfoDao.query(hospitalId, hospitalName, hospitalLevel,
				hospitalAddress, hospitalAgent, agentPhone, offset, length);
		return ret.toArray(new HospitalInfoDO[ret.size()]);
	}

	public IbatisHospitalInfoDAO getHospitalInfoDao() {
		return hospitalInfoDao;
	}

	public void setHospitalInfoDao(IbatisHospitalInfoDAO hospitalInfoDao) {
		this.hospitalInfoDao = hospitalInfoDao;
	}
}
