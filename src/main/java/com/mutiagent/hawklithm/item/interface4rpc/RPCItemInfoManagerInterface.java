package com.mutiagent.hawklithm.item.interface4rpc;

import com.multiagent.hawklithm.item.dataobject.ItemInfoDO;

public interface RPCItemInfoManagerInterface {
boolean delete(Integer itemId);
boolean submit(String itemName , Integer itemType, Integer hospitalId,
		String manufacturer, boolean interconvertible,String remark);
public ItemInfoDO queryItemById(Integer itemId);
public ItemInfoDO[] queryItemByType(Integer itemType);
}
