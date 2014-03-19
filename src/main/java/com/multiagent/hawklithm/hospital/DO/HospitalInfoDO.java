package com.multiagent.hawklithm.hospital.DO;

import java.util.Date;

/**
hospital_id				int						���
gmt_create			date					�����ʱ��
gmt_modified		date					�����޸�ʱ��
hospital_name		varchar				ҽԺ��
hospital_level			int						���ȼ�
hospital_address	varchar				ҽԺ��ַ
hospital_agent		varchar				ҽԺ����
agent_phone			varchar				�����˵绰
 * @author hawklithm
 * 2013-12-24����1:39:06
 */
public class HospitalInfoDO {
	private Integer hospitalId;
	private Date gmtCreate;
	private Date gmtModified;
	private String hospitalName;
	private Integer hospitalLevel;
	private String hospitalAddress;
	private String hospitalAgent;
	private String agentPhone;
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
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
	public Integer getHospitalLevel() {
		return hospitalLevel;
	}
	public void setHospitalLevel(Integer hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	public String getHospitalAddress() {
		return hospitalAddress;
	}
	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	public String getHospitalAgent() {
		return hospitalAgent;
	}
	public void setHospitalAgent(String hospitalAgent) {
		this.hospitalAgent = hospitalAgent;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
}
