package com.multiagent.hawklithm.davinci.net;

import org.jboss.netty.channel.Channel;

import com.google.gson.Gson;
import com.multiagent.hawklithm.davinci.exceptioin.MessageTransportException;
import com.multiagent.hawklithm.readerNet.DO.RFIDOriginalInfos;
import com.multiagent.hawklithm.readerNet.manager.ReaderDataManager;

public class ReaderNettyHandler extends NettyHandler {

	private Gson gson = new Gson();
	private ReaderDataManager readerDataManager;

	@Override
	public void onMessageReceived(String message, Channel channel) throws MessageTransportException {
		RFIDOriginalInfos infos = gson.fromJson(message, RFIDOriginalInfos.class);
		readerDataManager.OriginalDataDealing(infos);
	}

	public ReaderDataManager getReaderDataManager() {
		return readerDataManager;
	}

	public void setReaderDataManager(ReaderDataManager readerDataManager) {
		this.readerDataManager = readerDataManager;
	}

}
