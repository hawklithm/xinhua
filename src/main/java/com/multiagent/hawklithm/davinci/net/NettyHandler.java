package com.multiagent.hawklithm.davinci.net;

import java.io.IOException;
import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.multiagent.hawklithm.davinci.exceptioin.MessageTransportException;

/**
 * 
 * @author hawklithm
 * 
 */
public abstract class NettyHandler extends SimpleChannelHandler {

	@Override
	final public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	  System.out.println(e.getMessage());
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
		String recvMsg = buffer.toString(Charset.defaultCharset());
		onMessageReceived(recvMsg, e.getChannel());
	}
	@Override
	  public void channelConnected(ChannelHandlerContext ctx,
              final ChannelStateEvent e) {
    System.out.println("有来自客户端的请求链接");
       }
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (e.getCause() instanceof IOException) {
			System.out.println("连接错误,断开");
			ctx.getChannel().close();
		} else {
			e.getCause().printStackTrace();
		}
	}

	//
	// @Override
	// public void channelDisconnected(ChannelHandlerContext ctx,
	// ChannelStateEvent e) throws Exception {
	// System.out.println("channelDisconnect");
	// super.channelDisconnected(ctx, e);
	// }

	// @Override
	// public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
	// throws Exception {
	// System.out.println("channalClosed");
	// super.channelClosed(ctx, e);
	// }

	public void sendMessage(String message, Channel channel) throws MessageTransportException {
		if (channel == null) {
			throw new MessageTransportException(new NullPointerException());
		}
		byte[] messageBytes=message.getBytes(Charset.defaultCharset());
		ChannelBuffer buffer = ChannelBuffers.buffer(messageBytes.length);
		buffer.writeBytes(messageBytes);
		channel.write(buffer);
	}

	/**
	 * 重写该函数可获取传输数据
	 */
	public abstract void onMessageReceived(String message, Channel channel) throws MessageTransportException;
//	{
//		if (channel == null) {
//			throw new MessageTransportException(new NullPointerException());
//		}
//		System.out.println("[recv message] " + message + " from " + channel.getRemoteAddress());
//	}
}
