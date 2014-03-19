package com.multiagent.hawklithm.davinci.net;

import java.lang.reflect.InvocationTargetException;

import org.jboss.netty.channel.Channel;

import com.google.gson.Gson;
import com.multiagent.hawklithm.davinci.eventListener.RPCBufferCallBackWatcher;
import com.multiagent.hawklithm.davinci.exceptioin.MessageTransportException;
import com.multiagent.hawklithm.davinci.exceptioin.RPCInterfaceNotFoundException;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCConnectInfo;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemProtocol;
import com.multiagent.hawklithm.davinci.rpc.Server.RPCRegManager;
import com.multiagent.hawklithm.davinci.rpc.Server.RPCServerExecManager;
import com.multiagent.hawklithm.davinci.rpc.acl.RPCACLManager;

public class RPCServerNettyHandler extends NettyHandler {
	private Gson gson = new Gson();
	private RPCRegManager RPCregManager;
	private RPCServerExecManager rpcExec;
	private RPCBufferCallBackWatcher watcher;
	private RPCACLManager aclManager;

	@Override
	public void onMessageReceived(String msg, Channel cha) throws MessageTransportException {
		System.out.println(msg);
		RPCSystemProtocol rpcMessage = gson.fromJson(msg, RPCSystemProtocol.class);
		try {
			if (!aclManager.verifyRPCInterfaceCall(rpcMessage)) {
				rpcMessage.setReturnObject("have no permission");
				return;
			}
			if (!rpcMessage.selfCheck()) {
				throw new MessageTransportException("RPC包错误");
			}
			Object instance = RPCregManager.getTarget(rpcMessage);
			Object ret = rpcExec.exec(rpcMessage.getClassName(), rpcMessage.getMethodName(),
					rpcMessage.getParamsType(), instance, rpcMessage.getParameters());
			System.out.println("RPC函数执行完毕");
			rpcMessage.setReturnObject(gson.toJson(ret));
		} catch (RPCInterfaceNotFoundException e) {
			// TODO 打印日志
			e.printStackTrace();
			rpcMessage.setException(e.getMessage());
			return;
		} catch (InvocationTargetException e) {
			Throwable exception = e.getTargetException();
			System.out.println("RPC函数执行异常: " + exception.getMessage() + "\nname: "
					+ exception.getClass().getName());
			e.printStackTrace();
			rpcMessage.setException(exception.getMessage());
			rpcMessage.setExceptionType(exception.getClass().getName());
		}
		catch (Exception e) {
			System.out
					.println("系统异常: " + e.getMessage() + "\nname: " + e.getClass().getName());
			e.printStackTrace();
			rpcMessage.setException(e.getMessage());
			rpcMessage.setExceptionType(e.getClass().getName());
		}

		if (!watcher.insert(new RPCConnectInfo(gson.toJson(rpcMessage), cha))) {
			// TODO 打印日志
		}
	}

	public RPCRegManager getRPCregManager() {
		return RPCregManager;
	}

	public void setRPCregManager(RPCRegManager rPCregManager) {
		RPCregManager = rPCregManager;
	}

	public RPCBufferCallBackWatcher getWatcher() {
		return watcher;
	}

	public void setWatcher(RPCBufferCallBackWatcher watcher) {
		this.watcher = watcher;
	}

	public RPCServerExecManager getRpcExec() {
		return rpcExec;
	}

	public void setRpcExec(RPCServerExecManager rpcExec) {
		this.rpcExec = rpcExec;
	}

	public RPCACLManager getAclManager() {
		return aclManager;
	}

	public void setAclManager(RPCACLManager aclManager) {
		this.aclManager = aclManager;
	}
}
