package com.multiagent.hawklithm.leon.manager;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.multiagent.hawklithm.history.dao.ItemHistoryDAO;
import com.multiagent.hawklithm.history.dao.PackageHistoryDAO;
import com.multiagent.hawklithm.history.dataobject.ItemHistoryDO;
import com.multiagent.hawklithm.history.dataobject.PackageHistoryDO;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.DO.SqlReaderAtEquipmentDO;
import com.multiagent.hawklithm.leon.dao.ItemPackageMappingInfoDAO;
import com.multiagent.hawklithm.leon.dao.MachineFlowRecordDAO;
import com.multiagent.hawklithm.leon.interface4rpc.RPCMachineFlowRecordManagerInterface;

/**
 * RFID设备流水信息记录管理
 * 
 * @author hawklithm
 * 
 */
public class RFIDMachineFlowRecordManager implements RPCMachineFlowRecordManagerInterface {

	private MachineFlowRecordDAO machineDataDao;
	private ItemPackageMappingInfoDAO itemPackageMappingInfoDAO;
	private ItemHistoryDAO itemHistoryDAO;
	private PackageHistoryDAO packageHistoryDAO;
	
	
	public Integer storeItemHistoryToDataBase(Date time, Integer itemId, Integer readerId, Integer cameraId, String itemStatus,Integer equipmentId){
		try {
			System.out.println("store item history to database");
			return itemHistoryDAO.insertItemHisotry(time, itemId, readerId, cameraId, itemStatus, equipmentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public Integer storePackageHistoryToDataBase(Date time, Integer packageId,Integer readerId,Integer cameraId,String packageStatus,Integer equipmentId){
		try {
			return packageHistoryDAO.insertPackageHisotry(time, packageId, readerId, cameraId, packageStatus, equipmentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public ItemHistoryDO[] queryItemHistory(Integer id, Integer itemId, Integer readerId, Integer equipmentId, Date startTime, Date endTime){
		try {
			 List<ItemHistoryDO> ans= itemHistoryDAO.selectItemHistory(id, itemId, readerId, equipmentId, startTime, endTime);
			 return ans.toArray(new ItemHistoryDO[ans.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PackageHistoryDO[] queryPackageHistory(Integer id, Integer packageId,Integer readerId,Integer equipmentId,Date startTime,Date  endTime){
		 try {
			List<PackageHistoryDO>ans=packageHistoryDAO.selectPackageHistory(id, packageId, readerId, equipmentId, startTime, endTime);
			return ans.toArray(new PackageHistoryDO[ans.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return null;
	}
	

	@Override
	public SqlReaderAtEquipmentDO[] getEquipmentHistoryInfoByItemId(Integer itemId, Date startTime,
			Date endTime) {
		try {
			List<SqlReaderAtEquipmentDO> ret = machineDataDao.selectHistoryInfo(null, startTime,
					endTime, null, null, itemId, null, null, null);
			return ret.toArray(new SqlReaderAtEquipmentDO[ret.size()]);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SqlReaderAtEquipmentDO[] getEquipmentHistoryInfoByPackageId(Integer packageId,
			Date startTime, Date endTime) {
		try {
			List<SqlReaderAtEquipmentDO> ret = machineDataDao.selectHistoryInfo(null, startTime,
					endTime, null, packageId, null, null, null, null);
			return ret.toArray(new SqlReaderAtEquipmentDO[ret.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SqlReaderAtEquipmentDO[] getEquipmentHistoryInfo(Integer id, Date startTime,
			Date endTime, Integer equipmentId, Integer packageId, Integer itemId, Integer readerId,
			Integer cameraId, Integer staffId) {
		try {
			List<SqlReaderAtEquipmentDO> ret = machineDataDao.selectHistoryInfo(id, startTime,
					endTime, equipmentId, packageId, itemId, readerId, cameraId, staffId);
			return ret.toArray(new SqlReaderAtEquipmentDO[ret.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SqlEquipCamReaderMappingDO getMappingDataByReaderId(int readerId) {
		try {
			return machineDataDao.selectMapping(Integer.valueOf(readerId), null, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SqlEquipCamReaderMappingDO getMappingDataByCameraId(int cameraId) {
		try {
			return machineDataDao.selectMapping(null, Integer.valueOf(cameraId), null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SqlEquipCamReaderMappingDO getMappingDataByEquipmentId(int equipmentId) {
		try {
			return machineDataDao.selectMapping(null, null, Integer.valueOf(equipmentId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public MachineFlowRecordDAO getMachineDataDao() {
		return machineDataDao;
	}

	public void setMachineDataDao(MachineFlowRecordDAO machineDataDao) {
		this.machineDataDao = machineDataDao;
	}

	public void storeItemPackageMappingIntoDataBase(RFIDDataPac info, int packageId) {
		try {
			machineDataDao.insertObject(info.getOriginalInfo().getRfid(), packageId,
					info.getCameraId(), info.getEquipmentId(),
					info.getOriginalInfo().getReaderId(), info.getOriginalInfo().getDate(),
					info.getStaffId());
			// 将手术包和器械的打包映射信息存入数据库
			itemPackageMappingInfoDAO.mappingInsert(info.getOriginalInfo().getRfid(), packageId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 存储器械在设备上的设备流水信息
	 * @param info
	 */
	public void storeItemIntoDataBase(RFIDDataPac info) {
		try {
			machineDataDao.insertObject(info.getOriginalInfo().getRfid(), null, info.getCameraId(),
					info.getEquipmentId(), info.getOriginalInfo().getReaderId(), info
							.getOriginalInfo().getDate(), info.getStaffId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 存储手术包在设备上的设备流水信息
	 * @param info
	 */
	public void storePackageIntoDataBase(RFIDDataPac info) {
		try {
			machineDataDao.insertObject(null, info.getOriginalInfo().getRfid(), info.getCameraId(),
					info.getEquipmentId(), info.getOriginalInfo().getReaderId(), info
							.getOriginalInfo().getDate(), info.getStaffId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ItemPackageMappingInfoDAO getItemPackageMappingInfoDAO() {
		return itemPackageMappingInfoDAO;
	}

	public void setItemPackageMappingInfoDAO(ItemPackageMappingInfoDAO itemPackageMappingInfoDAO) {
		this.itemPackageMappingInfoDAO = itemPackageMappingInfoDAO;
	}

	public ItemHistoryDAO getItemHistoryDAO() {
		return itemHistoryDAO;
	}

	public void setItemHistoryDAO(ItemHistoryDAO itemHistoryDAO) {
		this.itemHistoryDAO = itemHistoryDAO;
	}

	public PackageHistoryDAO getPackageHistoryDAO() {
		return packageHistoryDAO;
	}

	public void setPackageHistoryDAO(PackageHistoryDAO packageHistoryDAO) {
		this.packageHistoryDAO = packageHistoryDAO;
	}

}
