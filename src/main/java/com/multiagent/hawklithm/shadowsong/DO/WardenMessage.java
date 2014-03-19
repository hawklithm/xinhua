package com.multiagent.hawklithm.shadowsong.DO;

import com.multiagent.hawklithm.global.dataobject.Message;

public class WardenMessage extends Message {
	// 消息类型
	/**
	 * 从读卡器模块发送来的RFID数据
	 */
	public static String KIND_RFID_FROM_READER = "wm_rfidfromreader";
	/**
	 * 接收到从RFID实体来的数据
	 */
	public static String KIND_NEW_DATA_COMING = "new_data_coming";
	/**
	 * 订单已提交，准备生成运单
	 */
	public static String KIND_ORDER_READY_FOR_TRANSPORT = "order_ready_for_transport";
	/**
	 * 器械打包成手术包
	 */
	public static String KIND_ITEM_TO_PACKAGE="kind_item_to_package";
	// 目标类型
	/**
	 * 数据接收目标类型为读卡器
	 */
	public static String TARGET_TYPE_READER = "target_type_reader";
	/**
	 * 数据接收目标类型为设备模块
	 */
	public static String TARGET_TYPE_MACHINE = "target_type_machine";
	/**
	 * 数据接收目标类型为系统模块
	 */
	public static String TARGET_TYPE_SYSTEM_MODULE = "target_type_system_module";
	// 数据流方向
	/**
	 * 数据流向：进入
	 */
	public static String DIR_ENTER = "wm_enter";
	/**
	 * 数据流向：出去
	 */
	public static String DIR_EXIT = "wm_exit";

	// // RFID类型
	// /**
	// * rfid类型：器械
	// */
	// public static String RFID_TYPE_ITEM="rfid_type_item";
	// /**
	// * rfid类型：手术包
	// */
	// public static String RFID_TYPE_PACKAGE="rfid_type_package";
}
