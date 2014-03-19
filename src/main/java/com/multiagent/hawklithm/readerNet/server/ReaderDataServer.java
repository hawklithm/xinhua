package com.multiagent.hawklithm.readerNet.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

import com.multiagent.hawklithm.davinci.net.ReaderNettyHandler;
/**
 * 读卡器数据接收服务器
 * @author hawklithm
 *
 */
public class ReaderDataServer {
	private int port = 3273 + 1;
	private ReaderNettyHandler readerNettyHandler;

	public ReaderDataServer() {

	}

	public ReaderDataServer(int port) {
		this.port = port;
	}

	public void initReaderServer() {
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("UP_FRAME_HANDLER", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
				pipeline.addLast("DOWN_FRAME_HANDLER", new LengthFieldPrepender(2, false));
				pipeline.addLast("myHandler", readerNettyHandler);
				return pipeline;
			}

		});
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println("reader data server开启成功");
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ReaderNettyHandler getReaderNettyHandler() {
		return readerNettyHandler;
	}

	public void setReaderNettyHandler(ReaderNettyHandler readerNettyHandler) {
		this.readerNettyHandler = readerNettyHandler;
	}
}
