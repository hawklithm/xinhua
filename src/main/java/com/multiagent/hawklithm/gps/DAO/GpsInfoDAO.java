package com.multiagent.hawklithm.gps.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.multiagent.hawklithm.gps.DO.GpsInfoDO;
import com.multiagent.hawklithm.ibatis.IbatisManagerModule;

/**
 * tb_gps±íDAO
 * 
 * @author hawklithm 2013-12-30ÏÂÎç2:21:04
 */
public class GpsInfoDAO {
	private IbatisManagerModule ibatisManager;

	public int insert(Integer vehicleId, Double longitude, Double latitude, String stationName, Date time,Integer subTransId) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vehicleId", vehicleId);
		map.put("longitude", longitude);
		map.put("latitude", latitude);
		map.put("stationName", stationName);
		map.put("time", time);
		map.put("subTransId",subTransId);
		return (int) ibatisManager.insert("GpsInfoDAO.insert", map);
	}

	public List<GpsInfoDO> selectBySubTransId(Integer subTransId) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subTransId", subTransId);
		return (List<GpsInfoDO>) ibatisManager.select("GpsInfoDAO.select", map);
	}

	public int delete(Integer gpsId) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gpsId", gpsId);
		return ibatisManager.delete("GpsInfoDAO.delete", map);
	}

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

}
