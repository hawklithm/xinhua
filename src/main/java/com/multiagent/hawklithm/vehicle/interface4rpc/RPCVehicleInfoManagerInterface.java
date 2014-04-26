package com.multiagent.hawklithm.vehicle.interface4rpc;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.vehicle.DO.VehicleInfoDO;

public interface RPCVehicleInfoManagerInterface {
 
	//���ӳ�����Ϣ
	boolean addVehicleInformation(String vehicleType, Integer vehicleLevel, Double vehicleCapacity)
			throws DataAccessException;
//ͨ��Idɾ��������Ϣ
	boolean deleteVehicleInformation(int vehicleId) throws DataAccessException;
//���ĳ�����Ϣ
	boolean modifyVehicleInfomation(Integer vehicleId, String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity) throws DataAccessException;
//ͨ��id��ѯ����
	VehicleInfoDO[] queryById(int vehicleId) throws DataAccessException;
//ͨ������id�����ͣ������ȼ���������ƫ��������ҳ��ѯ������Ϣ
	VehicleInfoDO[] queryByAllInfo(Integer vehicleId, String vehicleType, Integer vehicleLevel,
			Double vehicleCapacity, Integer offset, Integer length) throws DataAccessException;

}
