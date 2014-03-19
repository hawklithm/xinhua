package com.multiagent.hawklithm.machineInfo.DO;

import java.util.Date;

/**
 * tb_machine_manage�豸��Ϣ�����
id							int	
gmt_create			date				���������
gmt_modified		date				�����޸�����
gmt_buy				date				��������
gmt_last_repair		date				�ϴ�ά��ʱ��
equipment_id			int					����RFID
manufacturer			varchar			��������
detail					varchar 			�豸��ϸ����
equipmentType		varchar			�豸����
 * @author hawklithm
 * 2013-12-25����1:07:17
 */
public class MachineInfoDO {
	private Integer id;
	private Date gmtCreate;
	private Date gmtModified;
	private Date gmtBuy;
	private Date gmtLastRepair;
	private Integer equipmentId;
	private String manufacturer;
	private String detail;
	private String equipmentType;
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Date getGmtBuy() {
		return gmtBuy;
	}
	public void setGmtBuy(Date gmtBuy) {
		this.gmtBuy = gmtBuy;
	}
	public Date getGmtLastRepair() {
		return gmtLastRepair;
	}
	public void setGmtLastRepair(Date gmtLastRepair) {
		this.gmtLastRepair = gmtLastRepair;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
}
