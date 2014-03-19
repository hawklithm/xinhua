package com.multiagent.hawklithm.davinci;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.multiagent.hawklithm.davinci.init.SystemInitDO;
import com.multiagent.hawklithm.davinci.rpc.DO.RPCSystemServerProxy;
import com.multiagent.hawklithm.davinci.rpc.Server.RPCServer;

/**
 * 
 * @author hawklithm
 * 
 */
public class ArchtechRoot implements Callable<Boolean> {

	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private AsynTaskRegisterMachine regManager;// = new
												// AsynTaskRegisterMachine();

	public Boolean call() throws Exception {
		try {
			System.out.println("初始化bean");
			ApplicationContext context = new ClassPathXmlApplicationContext("springContext-*.xml");
			SystemInitDO initDO = (SystemInitDO) context.getBean("root");
			RPCServer rpcRoot = (RPCServer) context.getBean("rpcRoot");
			System.out.println("root start");
			ApplicationContext tempContext = new ClassPathXmlApplicationContext(initDO.getConfigFileArray());
			try {
				// AsynTaskRegisterMachine reg
				regManager = tempContext.getBean(AsynTaskRegisterMachine.class);
				System.out.println("异步任务模块注册完成");
				// regManager.regist(reg.getAsynTaskLists());
			} catch (NoSuchBeanDefinitionException e) {
				// TODO 日志打印警告，在该xml中无RegisterMachine定义，该警告可忽略
			}
			for (RPCSystemServerProxy object : rpcRoot.getRPCregManager().getRegList()) {
				if (StringUtils.hasLength(object.getBeanId())) {
					try {
						Object target = tempContext.getBean(object.getBeanId());
						object.setTarget(target);
						System.out.println(object.getInterfaceName() + "关联成功");
					} catch (NoSuchBeanDefinitionException e) {
						// TODO 打印日志RPC配置文件中bean缺失
						System.out.println("RPC配置文件中缺失bean:[" + object.getBeanId() + "]");
					}
				}
			}
			RootListener.safeCount++;
			// }
			/**
			 * 对于配置文件中没有的项则不进行关联，而直接根据class生成实例
			 */
			for (RPCSystemServerProxy object : rpcRoot.getRPCregManager().getRegList()) {
				if (!object.isLinked()) {
					object.setTarget(Class.forName(object.getClassName()).newInstance());
					System.out.println(object.getClassName() + "生成实例成功");
				}
			}
			System.out.println("read xml ok");
			regManager.startAll();
			// System.out.println("AsynTask start to run");

		} catch (Exception e) {
			// TODO 添加错误日志的输出
			// System.out.println(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public AsynTaskRegisterMachine getRegManager() {
		return regManager;
	}

	public void setRegManager(AsynTaskRegisterMachine regManager) {
		this.regManager = regManager;
	}

}
