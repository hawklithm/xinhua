package com.multiagent.hawklithm.leon.module.plugin;

import java.util.ArrayList;
import java.util.List;

import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
/**
 * RFID原始数据分类器
 * @author hawklithm
 * 2013-12-28下午5:42:24
 */
public class OriginalRFIDDataClassifier {
	private RFIDTypeAllocation rfidTypeAllocation;

	public RFIDTypeAllocation getRfidTypeAllocation() {
		return rfidTypeAllocation;
	}

	public void setRfidTypeAllocation(RFIDTypeAllocation rfidTypeAllocation) {
		this.rfidTypeAllocation = rfidTypeAllocation;
	}

	public SqlRFIDOriginalObject[][] divideByRFIDType(SqlRFIDOriginalObject[] rfids) {
		SqlRFIDOriginalObject[][] ans = new SqlRFIDOriginalObject[2][];
		List<SqlRFIDOriginalObject>[] l=new List[RFIDTypeAllocation.RFID_TYPE_AMOUNT];
		for (int i=0;i<RFIDTypeAllocation.RFID_TYPE_AMOUNT;i++){
			l[i]=new ArrayList<SqlRFIDOriginalObject>(); 
		}
		for (int i = 0; i < rfids.length; i++) {
			l[rfidTypeAllocation.getRFIDType(rfids[i].getRfid())].add(rfids[i]);
//			switch (rfidTypeAllocation.getRFIDType(rfids[i].getRfid())) {
//			case RFIDTypeAllocation.RFID_TYPE__ITEM:
//				l[RFIDTypeAllocation.RFID_TYPE__ITEM].add(rfids[i]);
//				break;
//			case RFIDTypeAllocation.RFID_TYPE_PACKAGE:
//				l2.add(rfids[i]);
//				break;
//			}
		}
		ans[0] = l[0].toArray(new SqlRFIDOriginalObject[l[0].size()]);
		ans[1] = l[1].toArray(new SqlRFIDOriginalObject[l[1].size()]);
		return ans;
	}
}
