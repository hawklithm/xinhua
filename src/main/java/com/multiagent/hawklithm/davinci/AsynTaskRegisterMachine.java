package com.multiagent.hawklithm.davinci;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.multiagent.hawklithm.davinci.init.AsynTaskRegister;
import com.multiagent.hawklithm.global.register.IRegisterManager;
/**
 * 
 * @author hawklithm
 *
 */
public class AsynTaskRegisterMachine implements IRegisterManager{
	private List<AsynTaskRegister> asynTaskLists=new ArrayList<AsynTaskRegister>();
	private ExecutorService asynTaskThreadPool;
	public static Class<?> sign=AsynTaskRegister.class;
	
	public AsynTaskRegisterMachine(){
		System.out.println("×¢²á»úÆô¶¯");
		asynTaskThreadPool=Executors.newFixedThreadPool(40);
	}
	
	public void startAll(){
		for (AsynTaskRegister object:asynTaskLists){
			asynTaskThreadPool.execute(object);
		}
//		asynTaskThreadPool.shutdown();
	}
	
	public boolean regist(List<AsynTaskRegister> list){
		boolean ret=true;
		for (AsynTaskRegister object:list){
			if (!regist(object)){
				ret=false;
			}
		}
		return ret;
	}
	
	public boolean regist(Object object){
		AsynTaskRegister task=(AsynTaskRegister) object;
		if (asynTaskLists.contains(task)){
			return false;
		}
		System.out.println("×¢²á: "+task.getClass());
		asynTaskLists.add(task);
//		asynTaskThreadPool.execute(task);
		return true;
	}

	public List<AsynTaskRegister> getAsynTaskLists() {
		return asynTaskLists;
	}
}
