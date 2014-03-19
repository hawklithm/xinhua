package com.multiagent.hawklithm.leon.module.plugin;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 由RFID数值获取器类型值
 * 
 * @author hawklithm 2013-12-27下午2:58:50
 */
public class RFIDTypeAllocation {

	public final static int RFID_TYPE_ITEM = 0, RFID_TYPE_PACKAGE = 1, RFID_TYPE_TRANSPORT = 2, RFID_TYPE_STAFF = 3;
	
	public final static int RFID_TYPE_AMOUNT=4;

	private class NumPair {
		private int top;
		private int bottom;

		public NumPair(int top, int bottom) {
			this.top = top;
			this.bottom = bottom;
		}

		public int getTop() {
			return top;
		}

		public void setTop(int top) {
			this.top = top;
		}

		public int getBottom() {
			return bottom;
		}

		public void setBottom(int bottom) {
			this.bottom = bottom;
		}

	}

	private NumPair itemValue, pacValue, staffValue, transValue;

	private NumPair checkRfidType(String str) {
		NumPair pair = new NumPair(Integer.MIN_VALUE, Integer.MAX_VALUE);
		StringUtils.deleteWhitespace(str);
		int left = 0, right = str.length() - 1;
		if (!Character.isDigit(str.charAt(0))) {
			left++;
		}
		if (!Character.isDigit(str.charAt(right))) {
			right--;
		}
		if (left > right) {
			Assert.notNull(null, "specify rfid bounds error");
		}
		String[] numStrs = str.substring(left, right + 1).split(",");
		if (numStrs.length > 2) {
			Assert.notNull(null, "we only need top bounds and bottom bounds");
		}
		if (numStrs.length <= 0) {
			Assert.notNull(null, "we only need top bounds and bottom bounds");
		}
		if (numStrs.length == 1) {
			pair.setBottom(Integer.valueOf(numStrs[0]));
		}
		if (numStrs.length == 2) {
			pair.setBottom(Integer.valueOf(numStrs[0]));
			pair.setTop(Integer.valueOf(numStrs[1]));
		}
		return pair;
	}

	public void setTransValue(String transportValue) {
		this.transValue = checkRfidType(transportValue);
	}

	private int getItemTop() {
		return itemValue.getTop();
	}

	private int getItemBottom() {
		return itemValue.getBottom();
	}

	public void setItemValue(String itemValue) {
		this.itemValue = checkRfidType(itemValue);
	}

	private int getPacTop() {
		return pacValue.getTop();
	}

	private int getPacBottom() {
		return pacValue.getBottom();
	}

	public void setPacValue(String packageValue) {
		this.pacValue = checkRfidType(packageValue);
	}

	private int getStaffTop() {
		return staffValue.getTop();
	}

	private int getStaffBottom() {
		return staffValue.getBottom();
	}

	public void setStaffValue(String staffValue) {
		this.staffValue = checkRfidType(staffValue);
	}

	private int getTransTop() {
		return transValue.getTop();
	}

	private int getTransBottom() {
		return transValue.getBottom();
	}

	public boolean isTransportRFID(int rfid) {
		return (getTransTop() > rfid && getTransBottom() <= rfid);
	}

	public boolean isStaffRFID(int rfid) {
		return (getStaffTop() > rfid && getStaffBottom() <= rfid);
	}

	public boolean isItemRFID(int rfid) {
		return (getItemTop() > rfid && getItemBottom() <= rfid);
	}

	public boolean isPacRFID(int rfid) {
		return (getPacTop() > rfid && getPacBottom() <= rfid);
	}

	public int getRFIDType(int rfid) {
		if (isTransportRFID(rfid)) {
			return RFIDTypeAllocation.RFID_TYPE_TRANSPORT;
		} else if (isItemRFID(rfid)) {
			return RFIDTypeAllocation.RFID_TYPE_ITEM;
		} else if (isPacRFID(rfid)) {
			return RFIDTypeAllocation.RFID_TYPE_PACKAGE;
		} else if (isStaffRFID(rfid)) {
			return RFIDTypeAllocation.RFID_TYPE_STAFF;
		}
		return -1;
	}
}
