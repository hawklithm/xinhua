package com.multiagent.hawklithm.user.interface4rpc;

import java.util.List;

import com.multiagent.hawklithm.user.DO.SqlUserInfoDO;

public interface RPCUserInfoManagerInterface {
	/**
	 * ����û�
	 * 
	 * @param userName
	 * @param password
	 * @param level
	 * @param isEmployee
	 * @param hospitalId
	 * @return
	 */
	public boolean addUser(String userName, String password, String level, boolean isEmployee,
			int hospitalId);

	/**
	 * ɾ���û�
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(int userId);

	/**
	 * �����û�id��ѯ�û���Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public SqlUserInfoDO selectUserById(int userId);

	/**
	 * �����û�����ѯ�û���Ϣ
	 * 
	 * @param userName
	 * @return
	 */
	public SqlUserInfoDO selectUserByUserName(String userName);

	/**
	 * ������Ϣ��ѯ�û���Ϣ
	 * 
	 * @param userId
	 * @param userName
	 * @param level
	 * @param isEmployee
	 * @param hospitalId
	 * @return
	 */
	public SqlUserInfoDO[] selectUser(Integer userId, String userName, String level,
			Boolean isEmployee, Integer hospitalId, Integer offset, Integer length);

	/**
	 * �޸��û�����
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	boolean modifyPassword(int userId, String oldPassword, String newPassword);

	/**
	 * �޸��û���Ϣ����������
	 * 
	 * @param userId
	 * @param userName
	 * @param level
	 * @param isEmployee
	 * @param hospitalId
	 * @return
	 */
	boolean modifyUserInfo(int userId, String userName, String level, boolean isEmployee,
			int hospitalId, boolean enable);

}
