package com.multiagent.hawklithm.shadowsong.manager;


public interface WardenOperator<T> {
	/**
	 * 发送推送消息
	 * @param message
	 */
	void sendOutMessage(T message);
}
