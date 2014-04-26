package com.multiagent.hawklithm.vehicle.interface4rpc;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.vehicle.DO.VehicleInfoDO;

public interface RPCVehicleInfoManagerInterface {
 
	//增加车辆信息
	boolean addVehicleInformation(String vehicleType, Integer vehicleLevel, Double vehicleCapacity)
			throws DataAccessException;
//通过Id删除车辆信息
	boolean deleteVehicleInformation(int vehicleId) throws DataAccessException;
//更改车辆信息
	boolean modifyVehicleInfomation(Integer vehicleId, String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity) throws DataAccessException;
//通过id查询车辆
	VehicleInfoDO[] queryById(int vehicleId) throws DataAccessException;
//通过车辆id，类型，车辆等级、容量、偏移量来分页查询车辆信息
	VehicleInfoDO[] queryByAllInfo(Integer vehicleId, String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity, Integer offset, Integer length) throws DataAccessException;

}
