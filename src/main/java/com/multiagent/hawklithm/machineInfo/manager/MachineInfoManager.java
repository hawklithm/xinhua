package com.multiagent.hawklithm.machineInfo.manager;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.machineInfo.DAO.IbatisEquipmentStaffMappingDAO;
import com.multiagent.hawklithm.machineInfo.DAO.IbatisMachineInfoDAO;
import com.multiagent.hawklithm.machineInfo.DO.EquipmentStaffMappingDO;
import com.multiagent.hawklithm.machineInfo.DO.MachineInfoDO;
import com.multiagent.hawklithm.machineInfo.interface4rpc.RPCMachineInfoManagerInterface;

/**
 * 
 * @author hawklithm 2013-12-25ÏÂÎç7:45:49
 */
public class MachineInfoManager implements RPCMachineInfoManagerInterface {
	private IbatisMachineInfoDAO ibatisMachineInfoDao;
	private IbatisEquipmentStaffMappingDAO ibatisEquipmentStaffMappingDAO;
	
	@Override
	public Integer addEquipmentStaffMapping(Integer staffId,Integer equipmentId){
		Assert.notNull(staffId);
		Assert.notNull(equipmentId);
		return ibatisEquipmentStaffMappingDAO.insertMapping(staffId, equipmentId);
	}
	
	@Override
	public boolean deleteEquipmentStaffMapping(Integer id){
		Assert.notNull(id);
		return ibatisEquipmentStaffMappingDAO.deleteMapping(id)!=0;
	}
	
	@Override
	public boolean updateEquipmentStaffMapping(Integer id,Integer equipmentId,Integer staffId){
		Assert.notNull(id);
		return ibatisEquipmentStaffMappingDAO.modifyMapping(id, equipmentId, staffId)!=0;
	}

	@Override
	public  EquipmentStaffMappingDO[] queryEquipmentStaffMappingByEquipmentId(Integer equipmentId,Integer offset,Integer length){
		Assert.notNull(equipmentId);
		List<EquipmentStaffMappingDO>list= ibatisEquipmentStaffMappingDAO.queryMapping(equipmentId, null, offset, length);
		return list.toArray(new EquipmentStaffMappingDO[list.size()]);
	}
	
	@Override
	public  EquipmentStaffMappingDO[] queryEquipmentStaffMappingByStaffId(Integer staffId,Integer offset,Integer length){
		Assert.notNull(staffId);
		List<EquipmentStaffMappingDO>list=ibatisEquipmentStaffMappingDAO.queryMapping(null, staffId, offset, length);
		return list.toArray(new EquipmentStaffMappingDO[list.size()]);
	}
	
	@Override
	public EquipmentStaffMappingDO[] queryAllEquipmentStaffMapping(Integer offset,Integer length){
		List<EquipmentStaffMappingDO> list=ibatisEquipmentStaffMappingDAO.queryMapping(null,null , offset, length);
		return list.toArray(new EquipmentStaffMappingDO[list.size()]);
	}
	
	
	@Override
	public boolean equipmentDetailDeleteById(int id) throws DataAccessException {
		return ibatisMachineInfoDao.equipmentDetailDeleteById(id)!= 0;
	}

	@Override
	public boolean equipmentDetailModify(Integer id,  Date gmtLastRepair, 
			   String detail) throws DataAccessException {
		return ibatisMachineInfoDao.equipmentDetailModify(id, gmtLastRepair, detail) != 0;
	}

	@Override
	public MachineInfoDO[] queryById(int id) throws DataAccessException {
		List<MachineInfoDO> ret = ibatisMachineInfoDao.equipmentDetailQuery(id, null, null, null, null, null,
				null, null, null, null, null);
		return ret.toArray(new MachineInfoDO[ret.size()]);
	}

	@Override
	public MachineInfoDO[] queryByRepairTime(Date startTime, Date endTime, Integer offset,
			Integer length) throws DataAccessException {
		List<MachineInfoDO> ret = ibatisMachineInfoDao.equipmentDetailQuery(null, null, null, startTime, endTime,
				null, null, null, null, offset, length);
		return ret.toArray(new MachineInfoDO[ret.size()]);
	}

	@Override
	public MachineInfoDO[] queryByBuyTime(Date startTime, Date endTime, Integer offset,
			Integer length) throws DataAccessException {
		List<MachineInfoDO> ret = ibatisMachineInfoDao.equipmentDetailQuery(null, startTime, endTime, null, null,
				null, null, null, null, offset, length);
		return ret.toArray(new MachineInfoDO[ret.size()]);
	}

	@Override
	public MachineInfoDO[] queryByAllInfo(Integer id, Date gmtBuyStart, Date gmtBuyEnd,
			Date gmtLastRepairStart, Date gmtLastRepairEnd, String equipmentType,
			Integer equimpmentId, String manufacturer, String detail, Integer offset, Integer length)
			throws DataAccessException {
		List<MachineInfoDO> ret = ibatisMachineInfoDao.equipmentDetailQuery(id, gmtBuyStart, gmtBuyEnd,
				gmtLastRepairStart, gmtLastRepairEnd, equipmentType, equimpmentId, manufacturer,
				detail, offset, length);
		return ret.toArray(new MachineInfoDO[ret.size()]);
	}

	@Override
	public Integer addMachineInfo(Date gmtBuy, Date gmtLastRepair,  Integer equimpmentId, String manufacturer, String detail) {
		return ibatisMachineInfoDao.equipmentDetailSubmit(gmtBuy, gmtLastRepair, equimpmentId,
				manufacturer, detail);
	}

	public IbatisMachineInfoDAO getIbatisMachineInfoDao() {
		return ibatisMachineInfoDao;
	}

	public void setIbatisMachineInfoDao(IbatisMachineInfoDAO ibatisMachineInfoDao) {
		this.ibatisMachineInfoDao = ibatisMachineInfoDao;
	}

	public IbatisEquipmentStaffMappingDAO getIbatisEquipmentStaffMappingDAO() {
		return ibatisEquipmentStaffMappingDAO;
	}

	public void setIbatisEquipmentStaffMappingDAO(
			IbatisEquipmentStaffMappingDAO ibatisEquipmentStaffMappingDAO) {
		this.ibatisEquipmentStaffMappingDAO = ibatisEquipmentStaffMappingDAO;
	}

}
