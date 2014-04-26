package com.multiagent.hawklithm.user.manager;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.multiagent.hawklithm.user.DAO.UserInfoIbatisDAO;
import com.multiagent.hawklithm.user.DO.SqlUserInfoDO;
import com.multiagent.hawklithm.user.interface4rpc.RPCUserInfoManagerInterface;

public class UserInfoManager implements RPCUserInfoManagerInterface {
	private UserInfoIbatisDAO userInfoDao;

	@Override
	public boolean addUser(String userName, String password, String level, boolean isEmployee,
			int hospitalId,int staffId) {
		try {
			userInfoDao.submit(userName, password, level, isEmployee, hospitalId, true,staffId);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			return userInfoDao.delete(userId) != 0;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean modifyUserInfo(int userId, String userName, String level, boolean isEmployee,
			int hospitalId, boolean enable,int staffId) {
		try {
			return userInfoDao
					.modify(userId, userName, null, level, isEmployee, hospitalId, enable,staffId) != 0;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean modifyPassword(int userId, String oldPassword, String newPassword) {
		if (userInfoDao.queryForCheckingPassword(userId, oldPassword)) {
			userInfoDao.modify(userId, null, newPassword, null, null, null, null,(Integer) null);
			return true;
		}
		return false;
	}

	@Override
	public SqlUserInfoDO selectUserById(int userId) {
		List<SqlUserInfoDO> result = userInfoDao.query(userId, null, null, null, null,null);
		if (!CollectionUtils.isEmpty(result)) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public SqlUserInfoDO selectUserByUserName(String userName) {
		List<SqlUserInfoDO> result = userInfoDao.query(null, userName, null, null, null,null);
		if (!CollectionUtils.isEmpty(result)) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public SqlUserInfoDO[] selectUser(Integer userId, String userName, String level,
			Boolean isEmployee, Integer hospitalId,int staffId,Integer offset, Integer length) {
		List<SqlUserInfoDO> ret = userInfoDao.query(userId, userName, level, isEmployee,
				hospitalId, staffId,offset, length);
		return ret.toArray(new SqlUserInfoDO[ret.size()]);
	}

	public UserInfoIbatisDAO getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoIbatisDAO userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
}
