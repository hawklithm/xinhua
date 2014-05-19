package com.multiagent.hawklithm.leon.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.multiagent.hawklithm.history.dao.ItemHistoryDAO;
import com.multiagent.hawklithm.history.dao.PackageHistoryDAO;
import com.multiagent.hawklithm.history.dataobject.ExItemHistoryDO;
import com.multiagent.hawklithm.history.dataobject.ItemHistoryDO;
import com.multiagent.hawklithm.history.dataobject.PackageHistoryDO;
import com.multiagent.hawklithm.item.dao.IbatisItemInfoDAO;
import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;
import com.multiagent.hawklithm.leon.DO.RFIDDataPac;
import com.multiagent.hawklithm.leon.DO.SqlEquipCamReaderMappingDO;
import com.multiagent.hawklithm.leon.DO.SqlReaderAtEquipmentDO;
import com.multiagent.hawklithm.leon.dao.ItemPackageMappingInfoDAO;
import com.multiagent.hawklithm.leon.dao.MachineFlowRecordDAO;
import com.multiagent.hawklithm.leon.interface4rpc.RPCMachineFlowRecordManagerInterface;
import com.multiagent.hawklithm.leon.module.Gate;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerProperty;
import com.multiagent.hawklithm.leon.module.property.DO.ChangerAnnouncerPropertyArrayVersion;
import com.multiagent.hawklithm.machineInfo.DAO.IbatisEquipmentStaffMappingDAO;
import com.multiagent.hawklithm.machineInfo.DAO.IbatisMachineInfoDAO;
import com.multiagent.hawklithm.machineInfo.DO.EquipmentStaffMappingDO;
import com.multiagent.hawklithm.machineInfo.DO.MachineInfoDO;
import com.multiagent.hawklithm.staff.DAO.IbatisStaffInfoDAO;
import com.multiagent.hawklithm.staff.DO.StaffInfoDO;

/**
 * RFID设备流水信息记录管理
 *设备流水线的管理
 手术包的映射管理
 手术器械的历史记录
 设备信息操作
 配置管理
 员工信息管理
 手术器械管理
 *
 * 
 * @author hawklithm
 * 
 */
public class RFIDMachineFlowRecordManager implements RPCMachineFlowRecordManagerInterface {

	private MachineFlowRecordDAO machineDataDao;
	private ItemPackageMappingInfoDAO itemPackageMappingInfoDAO;
	private ItemHistoryDAO itemHistoryDAO;
	private PackageHistoryDAO packageHistoryDAO;
	private IbatisMachineInfoDAO ibatisMachineInfoDao;
	private IbatisEquipmentStaffMappingDAO ibatisEquipmentStaffMappingDAO;
	private IbatisStaffInfoDAO ibatisStaffInfoDao;
	private IbatisItemInfoDAO ibatisItemInfoDao;
	
	/*
	 * 将手术器械的历史记录存入到数据库中
	 */
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
	
	
	private String getEquipmentTypeByEquipmentId(Integer equipmentId){
		List<MachineInfoDO> ret = ibatisMachineInfoDao.equipmentDetailQuery(null, null, null,
				null, null, null, equipmentId,null,
				null, null, 1);
		if (ret.size()<=0){
			return "null";
		}else{
			return  ret.get(0).getEquipmentType();
		}
				
	}
	
	private  String queryStaffInfoByEquipmentId(Integer equipmentId){
		Assert.notNull(equipmentId);
		List<EquipmentStaffMappingDO>list= ibatisEquipmentStaffMappingDAO.queryMapping(equipmentId, null, null, 100);
		StringBuilder builder=new StringBuilder();
		for (EquipmentStaffMappingDO index:list){
			StaffInfoDO staffInfo=queryStaffNameByStaffId(index.getStaffId());
			builder.append(staffInfo.getStaffName()).append('(').append(index.getStaffId()).append(')').append(',');
		}
		if (builder.length()>1){
			return builder.substring(0, builder.length()-1);
		}
		return "null";
	}
	
	private  StaffInfoDO queryStaffNameByStaffId(int staffId) throws DataAccessException {
		List<StaffInfoDO> ret = ibatisStaffInfoDao.query(staffId, null, null, null, null, null,
				null, null, null);
		return ret.get(0);
	}
	private ItemInfoDO queryItemInfoById(int itemId){
		 ItemInfoDO ret=ibatisItemInfoDao.queryById(itemId);
		 return ret;
		 
	}
	@Override
	public ExItemHistoryDO[] queryItemHistory(Integer id, Integer itemId, Integer readerId, Integer equipmentId, Date startTime, Date endTime,Integer offset,Integer length){
		try {
			 List<ItemHistoryDO> ans= itemHistoryDAO.selectItemHistory(id, itemId, readerId, equipmentId, startTime, endTime,offset,length);
			 ExItemHistoryDO[] ret=new ExItemHistoryDO[ans.size()];
			 int tmpLength=ret.length;
			 for (int index=0;index<tmpLength;index++){
				 ret[index]=new ExItemHistoryDO(ans.get(index));
				 if (ret[index].getEquipmentId()!=null){
					 ret[index].setProcessName(getEquipmentTypeByEquipmentId(ret[index].getEquipmentId()));
					 ret[index].setStaffInfo(queryStaffInfoByEquipmentId(ret[index].getEquipmentId()));
	                 ret[index].setType(queryItemInfoById(ret[index].getItemId()).getItemType()+"");
				 }
			 }
			 return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public PackageHistoryDO[] queryPackageHistory(Integer id, Integer packageId,Integer readerId,Integer equipmentId,Date startTime,Date  endTime,Integer offset,Integer length){
		 try {
			List<PackageHistoryDO>ans=packageHistoryDAO.selectPackageHistory(id, packageId, readerId, equipmentId, startTime, endTime,offset,length);
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
	public ChangerAnnouncerPropertyArrayVersion[] getEquipmentsTodayHistoryInfo(Date startTime,
			Integer[] equipmentIds) {
		try {
			StringBuilder condition = new StringBuilder();
//			if (equipmentIds.length == 0) {
//				return new SqlReaderAtEquipmentDO[0];
//			}
			condition.append(" equipment_id=").append(equipmentIds[0]).append(" ");
			for (int i = 1; i < equipmentIds.length; i++) {
				condition.append("or equipment_id=").append(equipmentIds[i]).append(" ");
			}
			//TODO  分组到各个设备
			Map<Integer,ChangerAnnouncerProperty> machineGroup=new HashMap<Integer,ChangerAnnouncerProperty>();
			List<SqlReaderAtEquipmentDO> items = machineDataDao.selectTodaysHistoryInfo(startTime,
					condition.toString());
//			Set<Integer> itemMap=new HashSet<Integer >();
			for (SqlReaderAtEquipmentDO item:items){
				ChangerAnnouncerProperty one;
				if (!machineGroup.containsKey(item.getEquipmentId())){
					one=new ChangerAnnouncerProperty();
					one.setMachineRFID(item.getEquipmentId());
					if (item.getEquipmentId()==equipmentIds[equipmentIds.length-1]){
						one.setSourceType(Gate.GATE_TAG);
					}
					one.setStaffRFID(item.getStaffId());
				}else {
					one=machineGroup.get(item.getEquipmentId());
				}
				ItemInfoDO itemInfo=ibatisItemInfoDao.queryById(item.getItemId());
				if (!one.getItemAdd().contains(itemInfo.getItemId())){
					one.getItemAdd().add(itemInfo);
				}else{
					one.getItemRemove().add(itemInfo);
				}
			}
			List<ChangerAnnouncerPropertyArrayVersion> ans=new ArrayList<ChangerAnnouncerPropertyArrayVersion>();
			for (Map.Entry<Integer, ChangerAnnouncerProperty> entry:machineGroup.entrySet()){
				ChangerAnnouncerPropertyArrayVersion object=new ChangerAnnouncerPropertyArrayVersion(entry.getValue());
				ans.add(object);
			}
			return ans.toArray(new ChangerAnnouncerPropertyArrayVersion[ans.size()]);
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
	public IbatisStaffInfoDAO getIbatisStaffInfoDao() {
		return ibatisStaffInfoDao;
	}
	public void setIbatisStaffInfoDao(IbatisStaffInfoDAO ibatisStaffInfoDao) {
		this.ibatisStaffInfoDao = ibatisStaffInfoDao;
	}
	public IbatisItemInfoDAO getIbatisItemInfoDao() {
		return ibatisItemInfoDao;
	}
	public void setIbatisItemInfoDao(IbatisItemInfoDAO ibatisItemInfoDao) {
		this.ibatisItemInfoDao = ibatisItemInfoDao;
	}

}
