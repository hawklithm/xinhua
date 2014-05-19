package com.multiagent.hawklithm.shadowsong.DO;

import java.util.HashSet;
import java.util.Set;

import com.multiagent.hawklithm.global.pusher.IMessagePusher;

/**
 * ��Ϣ����ģ��
 * 
 * @author hawklithm
 * 
 */
public abstract class Warden {
	private String name;//��Ϣ�����߱�Ż�������
	/*
	 * ������������
	 * �ּ�ʼ�������ȵ�
	 */
	private Set<String> kinds = new HashSet<String>();
	private IMessagePusher<WardenMessage> pusher;
	
	public  Warden(String name,String... kinds){
		this.name=name;
		int len=kinds.length;
		for (int i=0;i<len;i++){
			this.kinds.add(kinds[i]);
		}
	}

	public void receiveMessage(WardenMessage message) {
		if (kinds.contains(message.getKind())) {
//			System.out.println("receveMessage,target: "+message.getTarget());
			if (name.matches(message.getTarget())) {
				asynchronizedProcess(message.getNote());
			}
		}
	}

	/**
	 * ��Ӧ��Ϣ��ִ�й���
	 * @param message ��Ϣ���ݣ���note���֣����޳�target��type
	 */
	abstract public void asynchronizedProcess(String message);

//	public void sendMessage(WardenMessage message) {
//		pusher.push(message);
//	}

	public String getName() {
		return name;
	}

	public Set<String> getKinds() {
		return kinds;
	}

	public void addKind(String kind) {
		this.kinds.add(kind);
	}

	public IMessagePusher<WardenMessage> getPusher() {
		return pusher;
	}

	public void setPusher(IMessagePusher<WardenMessage> pusher) {
		this.pusher = pusher;
	}
}
