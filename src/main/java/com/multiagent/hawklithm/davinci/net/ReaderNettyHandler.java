package com.multiagent.hawklithm.davinci.net;

import org.jboss.netty.channel.Channel;

import com.google.gson.Gson;
import com.multiagent.hawklithm.davinci.exceptioin.MessageTransportException;
import com.multiagent.hawklithm.readerNet.DO.RFIDOriginalInfos;
import com.multiagent.hawklithm.readerNet.manager.ReaderDataManager;
/*
 * ������ͨ��RFID�ض���Ϣ
 * ����Ϣ����message��channel
 */
public class ReaderNettyHandler extends NettyHandler {

	private Gson gson = new Gson();
	private ReaderDataManager readerDataManager;
/*
 * �����ܵ�����Ϣ�̸�readerDataManager���д���(non-Javadoc)
 * @see com.multiagent.hawklithm.davinci.net.NettyHandler#onMessageReceived(java.lang.String, org.jboss.netty.channel.Channel)
 */
	@Override
	public void onMessageReceived(String message, Channel channel) throws MessageTransportException {
		System.out.println("dog"+message);
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
