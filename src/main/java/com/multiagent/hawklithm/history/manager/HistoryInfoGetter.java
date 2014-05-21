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
	 * ������еid���豸id��ȡָ�������ϵ���ʷ��Ϣ
	 * @param itemId
	 * @param machineId
	 * @param length ��Ϣ���ܽ϶࣬��ʾ��Ҫ���ص���Ϣ����������null ��ʾʹ��Ĭ��ֵ��Ĭ��Ϊ100
	 * @param offset ��Ϣ���ܽ϶࣬��ʾ��Ҫ���ص���Ϣ��ƫ����������null ��ʾʹ��Ĭ��ֵ��Ĭ��Ϊ0
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
	 * ��ȡ�����items��ʷ��Ϣ�����������ʱ���������ʱ�����Ϊ����һ����е����ֹʱ��
	 * @param items �����ͬһ���豸�����ͬһ����е��������ʷ��Ϣ
	 * @return ����һ��������ֹʱ����Pair
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
	 * ��ȡ�����packages��ʷ��Ϣ�����������ʱ���������ʱ�����Ϊ����һ����е����ֹʱ��
	 * @param packages �����ͬһ���豸�����ͬһ����е��������ʷ��Ϣ
	 * @return ����һ��������ֹʱ����Pair
	 */
	private Pair<Date, Date> getPackagesStartTimeAndEndTime(ExPackageHistoryDO[] packages){
		//TODO ��getItemStartTimeAndEndTime��ͬ
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
