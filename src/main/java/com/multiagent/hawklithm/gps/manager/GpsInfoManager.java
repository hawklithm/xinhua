package com.multiagent.hawklithm.gps.manager;

import java.util.Date;
import java.util.List;

import com.multiagent.hawklithm.gps.DAO.GpsInfoDAO;
import com.multiagent.hawklithm.gps.DO.GpsInfoDO;
import com.multiagent.hawklithm.gps.interface4rpc.RPCGpsInfoManagerInterface;

public class GpsInfoManager implements RPCGpsInfoManagerInterface {
	private static String LOCATION_ON_THE_WAY = "on_the_way";
	private String locationOnTheWay = GpsInfoManager.LOCATION_ON_THE_WAY;
	public GpsInfoDAO gpsInfoDAO;

	public GpsInfoDAO getGpsInfoDAO() {
		return gpsInfoDAO;
	}

	public void setGpsInfoDAO(GpsInfoDAO gpsInfoDAO) {
		this.gpsInfoDAO = gpsInfoDAO;
	}

	@Override
	public boolean submit(Integer vehicleId, Double longitude, Double latitude, Date time,Integer subTransId) {
		return submit(vehicleId, longitude, latitude, time, locationOnTheWay,subTransId);
	}

	@Override
	public boolean submit(Integer vehicleId, Double longitude, Double latitude, Date time,
			String stationName,Integer subTransId) {
		return gpsInfoDAO.insert(vehicleId, longitude, latitude, stationName, time,subTransId) != 0;
	}

	@Override
	public boolean delete(Integer gpsId) {
		return gpsInfoDAO.delete(gpsId) != 0;
	}

	@Override
	public GpsInfoDO[] queryBySubTransId( Integer subTransId) {
		List<GpsInfoDO> ret = gpsInfoDAO.selectBySubTransId( subTransId);
		return ret.toArray(new GpsInfoDO[ret.size()]);
	}
}
