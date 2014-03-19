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
