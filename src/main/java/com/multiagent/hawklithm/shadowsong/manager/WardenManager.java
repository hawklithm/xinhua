package com.multiagent.hawklithm.shadowsong.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.multiagent.hawklithm.global.pusher.IMessagePusher;
import com.multiagent.hawklithm.global.register.IRegisterManager;
import com.multiagent.hawklithm.shadowsong.DO.Warden;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

/**
 * 消息注册管理机
 * 
 * @author hawklithm
 * 主要是读卡器消息的注册
 * 包括注册消息、消息的推送
 * map是所有的读卡器类型
 * String包括gateReader、sortingReader0、sortingReader1、sortingReader2、sortingReaderX、cleanAndDisinfectReader0、cleanAndDisinfectReader1、cleanAndDisinfectReader2
 * cleanAndDisinfectReaderX、packageReader0、packageReader1、packageReader2、packageReaderX等
 * kinds包括message_gate_over
 * message_sorting_enter
 * message_sorting_start
 * message_sorting_end
 * message_sortting_over
 * message_cleananddisinfect_enter
 * message_cleananddisinfect_start
 * message_cleananddisinfect_end等
 * 
 */
public class WardenManager implements IRegisterManager,
		IMessagePusher<WardenMessage> {
	private Map<String, List<Object>> map = new HashMap<String, List<Object>>();
	private ExecutorService exec;
	private Gson gson = new Gson();

	public WardenManager() {
		exec = Executors.newFixedThreadPool(20);
	}

	public WardenManager(int threadNumber) {
		exec = Executors.newFixedThreadPool(threadNumber);
	}

	@Override
	public boolean regist(Object object) {
		Warden warden = (Warden) object;
		boolean ok = false;
		if (object==null){
			System.out.println("注册warden为null");
		}
		if (warden==null){
			System.out.println("转换为warden后为null");
		}
//		System.out.println("注册warden: " + gson.toJson(object));
		for (String kind : warden.getKinds()) {
			if (!map.containsKey(kind)) {
				map.put(kind,new ArrayList<Object>());
			}
			map.get(kind).add(warden);
			ok=true;
		}
		warden.setPusher(this);
		System.out.println("需注册的warden名称: "+warden.getName()+"类型: "+gson.toJson(warden.getKinds())+"消息注册管理机Map: "+gson.toJson(map));
		return ok;
	}

	/**
	 * Warden接收消息的异步线程
	 * 
	 * @author hawklithm
	 * 
	 */
	private class WardenThread implements Runnable {
		private WardenMessage message;
		private Warden object;

		public WardenThread(Warden object, WardenMessage message) {
			this.message = message;
			this.object = object;
		}

		@Override
		public void run() {
			object.receiveMessage(message);
		}

	}

	/**
	 * 推送消息
	 */
	public void push(WardenMessage message) {
//		System.out.println("message to push: "+gson.toJson(message));
		List<Object> list = map.get(message.getKind());
//		System.out.println("push: "+gson.toJson(list));
		if (!CollectionUtils.isEmpty(list)) {
			for (Object object : list) {
				exec.execute(new WardenThread((Warden)object, message));
			}
		}
	}

	public Map<String, List<Object>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Object>> map) {
		this.map = map;
	}

}
