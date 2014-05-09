package com.multiagent.hawklithm.user.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.multiagent.hawklithm.ibatis.IbatisManagerModule;
import com.multiagent.hawklithm.user.DO.SqlUserInfoDO;

/**
 * 对tb_user用户信息表进行操作
 * 
 * @author hawklithm 2013-12-22下午1:21:54
 */
public class UserInfoIbatisDAO {
	private static String DEFAULT_ROLE_PREFIX = "ROLE_";
	private IbatisManagerModule ibatisManager;
	private TransactionTemplate transactionTemplate;
	private String RolePrefix = DEFAULT_ROLE_PREFIX;

	/**
	 * 添加用户信息
	 * 
	 * @param userName
	 * @param password
	 * @param level
	 *            权限用逗号隔开
	 * @param isEmployee
	 * @param hospitalId
	 * @param enable
	 *            账户是否启用
	 * @return
	 * @throws DataAccessException
	 */
	public int submit(final String userName, final String password, final String level,
			final Boolean isEmployee, final Integer hospitalId, final Boolean enable,final int staffId)
			throws DataAccessException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					Integer userId = insert(userName, password, isEmployee, hospitalId, enable);
					if (StringUtils.hasLength(level)) {
						for (String p : level.split(",")) {
							insertUserRole(userId, p);
						}
					}
					return userId;
				} catch (Exception e) {
					status.setRollbackOnly();
					return 0;
				}
			}
		});
	}

	public int modify(final int userId, final String userName, final String password,
			final String level, final Boolean isEmployee, final Integer hospitalId,
			final Boolean enable,final int staffId) throws DataAccessException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					// if (!level.startsWith(RolePrefix)) {
					// throw new
					// DataBadFormatException("the user level must start with "
					// + RolePrefix);
					// }
					for (String role : level.split(",")) {
						updateUserRole(userId, role);
					}
					return updateByUserId(userId, userName, password, isEmployee, hospitalId,
							enable);
				} catch (Exception e) {
					status.setRollbackOnly();
					return 0;
				}
			}

		});

	}

	public int delete(final int userId) throws DataAccessException {
		return transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					deleteUserRole(userId);
					return deleteById(userId);
				} catch (Exception e) {
					status.setRollbackOnly();
					return 0;
				}

			}
		});
	}

	public List<SqlUserInfoDO> query(Integer userId, String userName, String level,
			Boolean isEmployee, Integer hospitalId,Integer staffId) {
		return query(userId, userName, level, isEmployee, hospitalId, null, null,staffId);
	}

	public List<SqlUserInfoDO> query(Integer userId, String userName, String level,
			Boolean isEmployee, Integer hospitalId,Integer staffId, Integer offset, Integer length) {
		List<SqlUserInfoDO> infos = select(userId, userName, level, isEmployee, hospitalId,staffId,offset,
				length);
		for (SqlUserInfoDO info : infos) {
			List<String> roles = selectUserRole(info.getUserId());
			StringBuilder levels = new StringBuilder();
		/*	for (String role : roles) {
				levels.append(role).append(",");
			}
			levels.deleteCharAt(levels.length());
			info.setLevel(levels.toString());*/
		}
		return infos;
	}

	public boolean queryForCheckingPassword(Integer userId, String password) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("password", password);
		return ibatisManager.select("UserInfoIbatisDAO.queryForCheckingPassword", paramMap).size() != 0;
	}

	private void insertUserRole(Integer userId, String role) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("role", role);
		ibatisManager.insert("UserInfoIbatisDAO.insertUserRole", paramMap);
	}

	private void updateUserRole(Integer userId, String role) {
		Assert.notNull(userId);
		Assert.notNull(role);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("role", role);
		ibatisManager.update("UserInfoIbatisDAO.updateUserRole", paramMap);
	}

	private void deleteUserRole(Integer userId) {
		Assert.notNull(userId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		ibatisManager.delete("UserInfoIbatisDAO.deleteUserRole", paramMap);
	}

	private List<String> selectUserRole(Integer userId) {
		Assert.notNull(userId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return (List<String>) ibatisManager.select("UserInfoIbatisDAO.selectUserRole", paramMap);
	}

	private List<SqlUserInfoDO> select(Integer userId, String userName, String level,
			Boolean isEmployee, Integer hospitalId,Integer staffId, Integer offset, Integer length)
			throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		paramMap.put("isEmployee", isEmployee);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("staffId",staffId);
		paramMap.put("offset", offset);
		paramMap.put("length", length);
		// paramMap.put("level", level);
		return (List<SqlUserInfoDO>) ibatisManager.select("UserInfoIbatisDAO.select", paramMap);
	}

	private int deleteById(Integer userId) throws DataAccessException {
		Assert.notNull(userId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return ibatisManager.delete("UserInfoIbatisDAO.deleteById", paramMap);
	}

	private int updateByUserId(Integer userId, String userName, String password,
			Boolean isEmployee, Integer hospitalId, Boolean enable) throws DataAccessException {
		Assert.notNull(userId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		paramMap.put("isEmployee", isEmployee);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("enable", enable);
		return ibatisManager.update("UserInfoIbatisDAO.updateByUserId", paramMap);
	}

	private int insert(String userName, String password, Boolean isEmployee, Integer hospitalId,
			Boolean enable) throws DataAccessException {
		Assert.notNull(userName, "must specify the userName when insert a new user");
		Assert.notNull(password, "must specify the password when insert a new user");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		paramMap.put("isEmployee", isEmployee);
		paramMap.put("hospitalId", hospitalId);
		paramMap.put("enable", enable);
		return (int) ibatisManager.insert("UserInfoIbatisDAO.insert", paramMap);
	}

	public IbatisManagerModule getIbatisManager() {
		return ibatisManager;
	}

	public void setIbatisManager(IbatisManagerModule ibatisManager) {
		this.ibatisManager = ibatisManager;
	}

	public String getRolePrefix() {
		return RolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		RolePrefix = rolePrefix;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
}
