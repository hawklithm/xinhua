package com.multiagent.hawklithm.pack.interface4rpc;

import java.util.Date;

import com.multiagent.hawklithm.pack.dataobject.PackageInfoDO;

public interface RPCPackageInfoManagerInterface {

	boolean delete(Integer packageId);

	boolean submit(String packageName, Integer packageType, Integer hospitalId, Date packTime,
			Date expiryDate, Integer staffId, String remark);

	PackageInfoDO queryPackageById(Integer packageId);

	PackageInfoDO[] queryPackageByPackTime(Date startTime, Date endTime, Integer length,
			Integer offset);

	PackageInfoDO[] queryPackageByStaffId(Integer staffId, Integer offset, Integer length);

	PackageInfoDO[] queryByAllInfo(Integer packageId, Date startTime, Date endTime,
			Integer packageType, Integer hospitalId, Integer staffId, Integer offset, Integer length);

}
