package com.multiagent.hawklithm.pack.manager;

import java.util.Date;
import java.util.List;

import com.multiagent.hawklithm.pack.DAO.IbatisPackageInfoDAO;
import com.multiagent.hawklithm.pack.dataobject.PackageInfoDO;
import com.multiagent.hawklithm.pack.interface4rpc.RPCPackageInfoManagerInterface;

public class PackageInfoManager implements RPCPackageInfoManagerInterface{
private IbatisPackageInfoDAO ibatisPackageInfoDao;
	@Override
	public boolean delete(Integer packageId) {
		try{
			ibatisPackageInfoDao.deleteById(packageId);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean submit(String packageName, Integer packageType,
			Integer hospitalId,Date packTime,Date expiryDate,Integer staffId,
			String remark) {
		// TODO Auto-generated method stub
		try{
			ibatisPackageInfoDao.submit(packageName, packageType, hospitalId, packTime, expiryDate, remark, staffId);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	@Override
	public PackageInfoDO queryPackageById(Integer packageId) {
		List<PackageInfoDO> items=ibatisPackageInfoDao.query(packageId, null, null, null, null, null, 0, 1);
		if (items.size()>0){
			return items.get(0);
		}
		return null;
	}

	@Override
	public PackageInfoDO[] queryPackageByStaffId(Integer staffId,Integer offset,Integer length) {
		List<PackageInfoDO> ans=ibatisPackageInfoDao.query(null, null, null, null, staffId, staffId, offset, length);
		return (PackageInfoDO[]) ans.toArray();
	}
	
	@Override
	public PackageInfoDO[] queryPackageByPackTime(Date startTime,Date endTime,Integer length,Integer offset){
		List<PackageInfoDO> items=ibatisPackageInfoDao.query(null, startTime, endTime, null, null, null, offset, length);
		return items.toArray(new PackageInfoDO[items.size()]);
	}
	
	@Override
	public PackageInfoDO[] queryByAllInfo(Integer packageId, Date startTime, Date endTime, Integer packageType,
			Integer hospitalId, Integer staffId, Integer offset,
			Integer length){
		List<PackageInfoDO>items=ibatisPackageInfoDao.query(packageId, startTime, endTime, packageType, hospitalId, staffId, offset, length);
		return items.toArray(new PackageInfoDO[items.size()]);
	}

}
