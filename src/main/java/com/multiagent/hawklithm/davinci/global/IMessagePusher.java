package com.multiagent.hawklithm.davinci.global;


public interface IMessagePusher<T extends Message> {
	public void push(T message);
}
