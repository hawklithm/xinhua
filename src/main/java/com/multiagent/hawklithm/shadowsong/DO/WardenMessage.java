package com.multiagent.hawklithm.shadowsong.DO;

import com.multiagent.hawklithm.global.dataobject.Message;

public class WardenMessage extends Message {
	// ��Ϣ����
	/**
	 * �Ӷ�����ģ�鷢������RFID����
	 */
	public static String KIND_RFID_FROM_READER = "wm_rfidfromreader";
	/**
	 * ���յ���RFIDʵ����������
	 */
	public static String KIND_NEW_DATA_COMING = "new_data_coming";
	/**
	 * �������ύ��׼�������˵�
	 */
	public static String KIND_ORDER_READY_FOR_TRANSPORT = "order_ready_for_transport";
	/**
	 * ��е�����������
	 */
	public static String KIND_ITEM_TO_PACKAGE="kind_item_to_package";
	// Ŀ������
	/**
	 * ���ݽ���Ŀ������Ϊ������
	 */
	public static String TARGET_TYPE_READER = "target_type_reader";
	/**
	 * ���ݽ���Ŀ������Ϊ�豸ģ��
	 */
	public static String TARGET_TYPE_MACHINE = "target_type_machine";
	/**
	 * ���ݽ���Ŀ������Ϊϵͳģ��
	 */
	public static String TARGET_TYPE_SYSTEM_MODULE = "target_type_system_module";
	// ����������
	/**
	 * �������򣺽���
	 */
	public static String DIR_ENTER = "wm_enter";
	/**
	 * �������򣺳�ȥ
	 */
	public static String DIR_EXIT = "wm_exit";

	// // RFID����
	// /**
	// * rfid���ͣ���е
	// */
	// public static String RFID_TYPE_ITEM="rfid_type_item";
	// /**
	// * rfid���ͣ�������
	// */
	// public static String RFID_TYPE_PACKAGE="rfid_type_package";
}
