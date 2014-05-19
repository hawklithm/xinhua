package com.multiagent.hawklithm.itempackmapping.interface4rpc;

import java.util.Date;

import com.multiagent.hawklithm.itempackmapping.dataobject.ItemPackMappingInfoDO;

public interface RPCItemPackMappingInfoManagerInterface {

	boolean deleteByPackageId(Integer packageId);

	boolean deleteById(Integer id);

	boolean deleteByItemId(Integer itemId);

	boolean submit(Integer itemId, Integer packageId);

	ItemPackMappingInfoDO queryMappingByPackageId(Integer packageId, Integer offset, Integer length);

	ItemPackMappingInfoDO[] queryMappingByItemId(Integer itemId, Integer offset, Integer length);

	ItemPackMappingInfoDO[] queryPackageByPackTime(Date startTime, Date endTime, Integer length,
			Integer offset);

	ItemPackMappingInfoDO[] queryByAllInfo(Integer id, Integer itemId, Integer packageId,
			Date startTime, Date endTime, Integer offset, Integer length);


}
