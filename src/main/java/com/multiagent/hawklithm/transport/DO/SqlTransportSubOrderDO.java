package com.multiagent.hawklithm.transport.DO;

import java.util.Date;
/**
id						int	
gmt_create		date				���������
gmt_modified	date				�����޸�����
parent_id			int					�����䵥ID
Vehicle_id			int					���乤��ID
Sub_order_id		int					��Ӧ�����Ӷ������򷵼��Ӷ���
 * @author hawklithm
 * 2013-12-18����4:51:47
 */
public class SqlTransportSubOrderDO {
	private Integer id;
	private Date gmtCreate;
	private 	Date gmtModified;
	private Integer parentId;
	private Integer vehicleId;
	private Integer subOrderId;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getSubOrderId() {
		return subOrderId;
	}
	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}
}
