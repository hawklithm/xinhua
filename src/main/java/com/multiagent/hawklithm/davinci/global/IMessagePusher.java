package com.multiagent.hawklithm.davinci.global;


public interface IMessagePusher<T extends Message> {
	//ÍÆËÍÊı¾İ
	public void push(T message);
}
