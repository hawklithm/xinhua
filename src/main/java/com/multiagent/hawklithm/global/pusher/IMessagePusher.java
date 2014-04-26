package com.multiagent.hawklithm.global.pusher;

import com.multiagent.hawklithm.global.dataobject.Message;

//消息推送接口
public interface IMessagePusher<T extends Message> {
	public void push(T message);
}
