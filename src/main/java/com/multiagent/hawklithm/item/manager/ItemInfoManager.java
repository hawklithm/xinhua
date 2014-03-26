package com.multiagent.hawklithm.item.manager;

import java.util.List;

import com.multiagent.hawklithm.item.dao.IbatisItemInfoDAO;
import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;
import com.multiagent.hawklithm.item.interface4rpc.RPCItemInfoManagerInterface;

public class ItemInfoManager implements RPCItemInfoManagerInterface{
private IbatisItemInfoDAO itemInfoDao;
	@Override
	public boolean delete(Integer itemId) {
		// TODO Auto-generated method stub
		try{
			itemInfoDao.deleteById(itemId);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean submit(String itemName, Integer itemType,
			Integer hospitalId, String manufacturer, boolean interconvertible,
			String remark) {
		// TODO Auto-generated method stub
		try{
			itemInfoDao.submit(itemName, itemType, hospitalId, manufacturer, interconvertible, remark);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	@Override
	public ItemInfoDO queryItemById(Integer itemId) {
		// TODO Auto-generated method stub
		ItemInfoDO item=itemInfoDao.queryById(itemId);
		return null;
	}

	@Override
	public ItemInfoDO[] queryItemByType(Integer itemType) {
		// TODO Auto-generated method stub
	
			List<ItemInfoDO> ans=itemInfoDao.queryByType(itemType);
	
		return (ItemInfoDO[]) ans.toArray();
	}

}
