package com.multiagent.hawklithm.davinci.rpc.Server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.multiagent.hawklithm.davinci.net.NettyHandler;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemServerProxy;

/**
 * 
 * @author hawklithm
 * RPC服务端
 */
public class RPCServer implements BeanPostProcessor/* , IRPCDataExchange */{

	private int port = 0;
	private NettyHandler rpcServerNettyHandler;
	private RPCRegManager RPCregManager;

	public RPCServer(int port) {
		this.port = port;
		initNetty();
	}

	private void initNetty() {
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("UP_FRAME_HANDLER", new LengthFieldBasedFrameDecoder(
						Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("DOWN_FRAME_HANDLER", new LengthFieldPrepender(4, false));
				pipeline.addLast("myHandler", rpcServerNettyHandler);
				return pipeline;
			}

		});
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println("RPC server开启成功");
	}

	public RPCRegManager getRPCregManager() {
		return RPCregManager;
	}

	public void setRPCregManager(RPCRegManager rPCregManager) {
		RPCregManager = rPCregManager;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof RPCSystemServerProxy) {
			RPCregManager.regist((RPCSystemServerProxy) bean);
		}
		return bean;
	}

	public NettyHandler getRpcServerNettyHandler() {
		return rpcServerNettyHandler;
	}

	public void setRpcServerNettyHandler(NettyHandler rpcServerNettyHandler) {
		this.rpcServerNettyHandler = rpcServerNettyHandler;
	}

}
