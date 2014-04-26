package com.multiagent.hawklithm.davinci.rpc.DO;

import org.jboss.netty.channel.Channel;

public class RPCConnectInfo {
	//RPC连接信息包含信息、管道
	private String msg;
	private Channel channel;

	public RPCConnectInfo(String msg, Channel channel) {
		this.msg = msg;
		this.channel = channel;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
