package com.multiagent.hawklithm.global.pusher;

import com.multiagent.hawklithm.global.dataobject.Message;

//��Ϣ���ͽӿ�
public interface IMessagePusher<T extends Message> {
	public void push(T message);
}
