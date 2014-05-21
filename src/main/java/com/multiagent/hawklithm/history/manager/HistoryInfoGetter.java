package com.multiagent.hawklithm.history.manager;

import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Assert;

import com.hawklithm.utils.Pair;
import com.multiagent.hawklithm.history.dataobject.ExItemHistoryDO;
import com.multiagent.hawklithm.history.dataobject.ExPackageHistoryDO;
import com.multiagent.hawklithm.history.dataobject.ItemAllHistoryInfoDO;
import com.multiagent.hawklithm.history.dataobject.PackageAllHistoryInfoDO;
import com.multiagent.hawklithm.history.interface4rpc.RPCHistoryInfoGetterInterface;
import com.multiagent.hawklithm.leon.manager.RFIDMachineFlowRecordManager;

public class HistoryInfoGetter implements RPCHistoryInfoGetterInterface{
	private RFIDMachineFlowRecordManager rfidManager;
	/**
	 * 根据器械id和设备id获取指定机器上的历史信息
	 * @param itemId
	 * @param machineId
	 * @param length 信息可能较多，表示需要返回的信息条数，传入null 表示使用默认值，默认为100
	 * @param offset 信息可能较多，表示需要返回的信息的偏移量，传入null 表示使用默认值，默认为0
	 * @return
	 */
	@Override
	public ItemAllHistoryInfoDO getItemAllHistoryOnSpecifiedMachine(Integer itemId,
			Integer machineId,Integer length,Integer offset) {
		if (length==null){
			length=100;
		}
		if (offset==null){
			offset=0;
		}
		ExItemHistoryDO[] items=rfidManager.queryItemHistory(null, itemId, null, machineId, null, null, offset, length);
		Pair<Date, Date> pair=getItemStartTimeAndEndTime(items);
		ItemAllHistoryInfoDO itemAllHistory=new ItemAllHistoryInfoDO();
		itemAllHistory.setItems(items);
		itemAllHistory.setStartTime(pair.getFirst());
		itemAllHistory.setEndTime(pair.getLast());
		return itemAllHistory;
	}
	/**
	 * 获取传入的items历史信息数组中最早的时间点和最晚的时间点作为处理一个器械的起止时间
	 * @param items 传入的同一个设备处理的同一个器械的所有历史信息
	 * @return 返回一个包含起止时间点的Pair
	 */
	private Pair<Date, Date> getItemStartTimeAndEndTime(ExItemHistoryDO[] items){
		Assert.notEmpty(items);
		Date starter,ender;
		starter=ender=items[0].getTime();
		for (int i=0;i<items.length;i++){
			if (items[0].getTime().before(starter)){
				starter=items[0].getTime();
			}
			if (items[0].getTime().after(ender)){
				ender=items[0].getTime();
			}
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(ender);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)+5);
		ender=calendar.getTime();
		calendar.setTime(starter);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)-5);
		starter=calendar.getTime();
		return new Pair<Date, Date>(starter, ender);
	}
	/**
	 * 获取传入的packages历史信息数组中最早的时间点和最晚的时间点作为处理一个器械的起止时间
	 * @param packages 传入的同一个设备处理的同一个器械的所有历史信息
	 * @return 返回一个包含起止时间点的Pair
	 */
	private Pair<Date, Date> getPackagesStartTimeAndEndTime(ExPackageHistoryDO[] packages){
		//TODO 与getItemStartTimeAndEndTime相同
		return new Pair<Date, Date>(null, null);
	}
	
	
	@Override
	public PackageAllHistoryInfoDO getPackageAllHistoryOnSpecifiedMachine(Integer packId,
			Integer machineId, Integer length, Integer offset) {
		if (length==null){
			length=100;
		}
		if (offset==null){
			offset=0;
		}
		ExPackageHistoryDO[] packs=rfidManager.queryPackageHistory(null, packId, null, machineId, null, null, offset, length);
		Pair<Date, Date> pair=getPackagesStartTimeAndEndTime(packs);
		PackageAllHistoryInfoDO packAllHistory=new PackageAllHistoryInfoDO();
		packAllHistory.setPacks(packs);
		packAllHistory.setStartTime(pair.getFirst());
		packAllHistory.setEndTime(pair.getLast());
		return packAllHistory;
	}
	public RFIDMachineFlowRecordManager getRfidManager() {
		return rfidManager;
	}
	public void setRfidManager(RFIDMachineFlowRecordManager rfidManager) {
		this.rfidManager = rfidManager;
	}
}
