package com.multiagent.hawklithm.vehicle.DO;

import java.util.Date;

/**
vehicle_id					int
gmt_create				date				�����ʱ��
gmt_modified			date				�����޸�ʱ��
vehicle_type				char				���乤�����ͣ���Ӧ�����ļ�����
vehicle_level				int					�������ȵȼ���������Դ����
vehicle_capacity			float				��������
 * @author hawklithm
 * 2013-12-25����3:59:38
 */
public class VehicleInfoDO {
	private Integer vehicleId;
	private Date gmtCreate;
	private Date gmtModified;
	private String vehicleType;
	private Integer vehicleLevel;
	private double vehicleCapacity;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getVehicleLevel() {
		return vehicleLevel;
	}

	public void setVehicleLevel(Integer vehicleLevel) {
		this.vehicleLevel = vehicleLevel;
	}

	public double getVehicleCapacity() {
		return vehicleCapacity;
	}

	public void setVehicleCapacity(double vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}
}
