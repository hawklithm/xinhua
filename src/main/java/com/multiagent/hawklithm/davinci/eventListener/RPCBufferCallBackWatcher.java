package com.multiagent.hawklithm.davinci.eventListener;

import com.multiagent.hawklithm.davinci.exceptioin.BufferEmptyException;
import com.multiagent.hawklithm.davinci.exceptioin.BufferOverflowException;
import com.multiagent.hawklithm.davinci.exceptioin.MessageTransportException;
import com.multiagent.hawklithm.davinci.net.NettyHandler;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCConnectInfo;
/*
 * 
 * 将netty准备发送的消息储存在一个队列里面
 */
public class RPCBufferCallBackWatcher  {

	private class LoopQueue {
		private RPCConnectInfo[] buffer;
		private int insertIndex = 0, getIndex = 0;
		private int loopLen;

		public LoopQueue(int loopLen) {
			buffer = new RPCConnectInfo[loopLen + 1];
			this.loopLen = loopLen + 1;
		}

		synchronized public void insert(RPCConnectInfo object)
				throws BufferOverflowException {
			int tmpIndex = (insertIndex + 1) % loopLen;
			if (tmpIndex == getIndex) {
				throw new BufferOverflowException();
			}
			buffer[insertIndex] = object;
			insertIndex = tmpIndex;
		}

		synchronized public RPCConnectInfo get() throws BufferEmptyException {
			if (getIndex == insertIndex) {
				throw new BufferEmptyException();
			}
			RPCConnectInfo ret = buffer[getIndex];
			getIndex = (getIndex + 1) % loopLen;
			return ret;
		}

	}
	private NettyHandler rpcServerNettyHandler;
	private LoopQueue sendBuffer;
	
	public RPCBufferCallBackWatcher(int bufferLen){
		sendBuffer=new LoopQueue(bufferLen);
	}
	public RPCBufferCallBackWatcher(){
		sendBuffer=new LoopQueue(200);
	}
	
	
	public boolean insert(RPCConnectInfo msg) {
		try {
//			sendBuffer.insert(msg);
			sendOut(msg);
//		} catch (BufferOverflowException e) {
//			// TODO 打印日志
//			e.printStackTrace();
//			return false;
		} catch (MessageTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void sendOut(RPCConnectInfo msg) throws MessageTransportException{
		rpcServerNettyHandler.sendMessage(msg.getMsg(), msg.getChannel());
	}

	public RPCConnectInfo get() {
		try {
			return sendBuffer.get();
		} catch (BufferEmptyException e) {
			// TODO 打印日志
			e.printStackTrace();
			return null;
		}
	}
	public NettyHandler getRpcServerNettyHandler() {
		return rpcServerNettyHandler;
	}
	public void setRpcServerNettyHandler(NettyHandler rpcServerNettyHandler) {
		this.rpcServerNettyHandler = rpcServerNettyHandler;
	}


}