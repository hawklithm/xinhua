package com.multiagent.hawklithm.davinci.eventListener;
//
//import java.lang.reflect.Method;
//
//import org.springframework.aop.AfterReturningAdvice;
//import org.springframework.aop.MethodBeforeAdvice;
//import org.springframework.aop.ThrowsAdvice;
//
//import com.multiagent.hawklithm.leon.manager.IRFIDModuleManager;
//import com.multiagent.hawklithm.leon.manager.impl.RFIDMachineFlowRecordManager;
//import com.multiagent.hawklithm.readerNet.RFIDOriginalInfos;
///**
// * 
// * @author hawklithm
// *
// */
//public class RFIDDataSubmitEventListenerAdvice implements MethodBeforeAdvice,
//		AfterReturningAdvice, ThrowsAdvice {
//	
//	private RFIDMachineFlowRecordManager rfidManager;
//
//	public void afterReturning(Object returnValue, Method method,
//			Object[] args, Object target) throws Throwable {
//		if (args.length <= 0) {
//			throw new Exception(
//					"event listener proxy error, please check whether the listened method has multipul matching");
//		}
//		RFIDOriginalInfos infos=(RFIDOriginalInfos)args[0];
////		rfidManager.messageTrans2Module(infos);
////		rfidManager.RFIDdataManage(infos);
//		
//	}
//
//	public void before(Method method, Object[] args, Object target)
//			throws Throwable {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void afterThrowing(Throwable throwable) {
//		// TODO 处理被代理接口抛出异常后的处理
//	}
//
//	public RFIDMachineFlowRecordManager getRfidManager() {
//		return rfidManager;
//	}
//
//	public void setRfidManager(RFIDMachineFlowRecordManager rfidManager) {
//		this.rfidManager = rfidManager;
//	}
//
//
//}
