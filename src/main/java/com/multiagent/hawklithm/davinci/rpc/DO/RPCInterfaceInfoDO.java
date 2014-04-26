package com.multiagent.hawklithm.davinci.rpc.DO;

import java.util.Date;
/*
 * RPC接口信息
 * 接口ID
 * 接口创建的时间
 * 家口修改的时间
 * 接口的名称
 * 类的名称
 * 目标对象
 * 评论
 * 是不是可见，既可以马上使用
 */
public class RPCInterfaceInfoDO {
	private int interfaceId;           
	private Date gmtCreate;
	private Date gmtModified;
	private String interfaceName;
	private String version;
	private String className;
	private String beanId;
	private Object target;
	private String comment;
	private boolean visible=false;
	public int getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(int interfaceId) {
		this.interfaceId = interfaceId;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
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
}
