package com.multiagent.hawklithm.itempackmapping.manager;

import java.util.Date;
import java.util.List;

import com.multiagent.hawklithm.itempackmapping.DAO.IbatisItemPackMappingInfoDAO;
import com.multiagent.hawklithm.itempackmapping.dataobject.ItemPackMappingInfoDO;
import com.multiagent.hawklithm.itempackmapping.interface4rpc.RPCItemPackMappingInfoManagerInterface;

public class ItemPackMappingInfoManager implements RPCItemPackMappingInfoManagerInterface{
private IbatisItemPackMappingInfoDAO ibatisItemPackMappingInfoDAO;
	@Override
	public boolean deleteById(Integer id) {
		try{
			ibatisItemPackMappingInfoDAO.delete(id, null, null);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public boolean deleteByPackageId(Integer packageId){
		try{
			ibatisItemPackMappingInfoDAO.delete(null, packageId, null);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deleteByItemId(Integer itemId){
		try{
			ibatisItemPackMappingInfoDAO.delete(null, null, itemId);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean submit(Integer itemId,Integer packageId) {
		try{
			ibatisItemPackMappingInfoDAO.submit(itemId, packageId);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	@Override
	public ItemPackMappingInfoDO queryMappingByPackageId(Integer packageId,Integer offset,Integer length) {
		List<ItemPackMappingInfoDO> items=ibatisItemPackMappingInfoDAO.query(null, null, packageId, null, null, offset, length);
		if (items.size()>0){
			return items.get(0);
		}
		return null;
	}

	@Override
	public ItemPackMappingInfoDO[] queryMappingByItemId(Integer itemId,Integer offset,Integer length) {
		List<ItemPackMappingInfoDO> ans=ibatisItemPackMappingInfoDAO.query(null, itemId, null, null, null, offset, length);
		return (ItemPackMappingInfoDO[]) ans.toArray();
	}
	
	@Override
	public ItemPackMappingInfoDO[] queryPackageByPackTime(Date startTime,Date endTime,Integer length,Integer offset){
		List<ItemPackMappingInfoDO> items=ibatisItemPackMappingInfoDAO.query(null, null, null, startTime, endTime, offset, length);
		return items.toArray(new ItemPackMappingInfoDO[items.size()]);
	}
	
	@Override
	public ItemPackMappingInfoDO[] queryByAllInfo(Integer id,Integer itemId, Integer packageId, Date startTime,
			Date endTime, Integer offset, Integer length) {
		List<ItemPackMappingInfoDO> items = ibatisItemPackMappingInfoDAO.query(id, itemId,
				packageId, startTime, endTime, offset, length);
		return items.toArray(new ItemPackMappingInfoDO[items.size()]);
	}

}
