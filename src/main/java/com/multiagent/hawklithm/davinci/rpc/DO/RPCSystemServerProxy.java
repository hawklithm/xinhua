package com.multiagent.hawklithm.davinci.rpc.DO;
/**
 * 
 * @author hawklithm
 *
 */
public class RPCSystemServerProxy {
	private String interfaceName;
	private String version;
	private String className;
	private String beanId;
	private Object target;
	private String comment;
	private boolean visible=false;
	private boolean isLinked = false;

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

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
		isLinked = true;
	}

	public boolean isLinked() {
		return isLinked;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
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

}
