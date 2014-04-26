package com.multiagent.hawklithm.davinci.init;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.multiagent.hawklithm.davinci.AsynTaskRegisterMachine;
import com.multiagent.hawklithm.leon.manager.ProcessModuleRegisterManager;
import com.multiagent.hawklithm.leon.process.IProcessModule;

/**
 * 
 * @author hawklithm
 * AutoRegister主要是执行代理
 * 
 */
public class AutoRegister implements BeanPostProcessor {
	private AsynTaskRegisterMachine asynTaskRegManager;
	private ProxyFactoryBean accountService;
	private ProcessModuleRegisterManager pmRegManager;
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("bean name: " + beanName + " "
				+ bean.getClass().getName() + "初始化开始");
		return bean;
	}
/*
 * 在初始化后进行处理，如果注册的是AsynTaskRegister则加入到asynTaskRegManager,否则ProcessModuleRegisterManager进行注册(non-Javadoc)
 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
 */
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof AsynTaskRegister) {
			System.out.println("bean name: "+beanName+" regist AsynTaskRegister");
			asynTaskRegManager.regist(bean);
		}else if (bean instanceof IProcessModule){
			System.out.println("bean name: "+beanName+" regist EquipmentObject");
			pmRegManager.regist(bean);
		}
		return bean;
	}

	public ProxyFactoryBean getAccountService() {
		return accountService;
	}

	public void setAccountService(ProxyFactoryBean accountService) {
		this.accountService = accountService;
	}

	public AsynTaskRegisterMachine getAsynTaskRegManager() {
		return asynTaskRegManager;
	}

	public void setAsynTaskRegManager(AsynTaskRegisterMachine asynTaskRegManager) {
		this.asynTaskRegManager = asynTaskRegManager;
	}

	public ProcessModuleRegisterManager getPmRegManager() {
		return pmRegManager;
	}

	public void setPmRegManager(ProcessModuleRegisterManager pmRegManager) {
		this.pmRegManager = pmRegManager;
	}


}
