package com.multiagent.hawklithm.davinci.rpc.Server;

import com.multiagent.hawklithm.davinci.rpc.DO.RPCConnectInfo;

public interface IRPCDataExchange {
	public boolean insert(RPCConnectInfo msg);
	public RPCConnectInfo get() ;
}
