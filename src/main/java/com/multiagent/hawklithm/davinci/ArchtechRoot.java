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
			System.out.println("��ʼ��bean");
			ApplicationContext context = new ClassPathXmlApplicationContext("springContext-*.xml");
			SystemInitDO initDO = (SystemInitDO) context.getBean("root");
			RPCServer rpcRoot = (RPCServer) context.getBean("rpcRoot");
			System.out.println("root start");
			ApplicationContext tempContext = new ClassPathXmlApplicationContext(initDO.getConfigFileArray());
			try {
				// AsynTaskRegisterMachine reg
				regManager = tempContext.getBean(AsynTaskRegisterMachine.class);
				System.out.println("�첽����ģ��ע�����");
				// regManager.regist(reg.getAsynTaskLists());
			} catch (NoSuchBeanDefinitionException e) {
				// TODO ��־��ӡ���棬�ڸ�xml����RegisterMachine���壬�þ���ɺ���
			}
			for (RPCSystemServerProxy object : rpcRoot.getRPCregManager().getRegList()) {
				if (StringUtils.hasLength(object.getBeanId())) {
					try {
						Object target = tempContext.getBean(object.getBeanId());
						object.setTarget(target);
						System.out.println(object.getInterfaceName() + "�����ɹ�");
					} catch (NoSuchBeanDefinitionException e) {
						// TODO ��ӡ��־RPC�����ļ���beanȱʧ
						System.out.println("RPC�����ļ���ȱʧbean:[" + object.getBeanId() + "]");
					}
				}
			}
			RootListener.safeCount++;
			// }
			/**
			 * ���������ļ���û�е����򲻽��й�������ֱ�Ӹ���class����ʵ��
			 */
			for (RPCSystemServerProxy object : rpcRoot.getRPCregManager().getRegList()) {
				if (!object.isLinked()) {
					object.setTarget(Class.forName(object.getClassName()).newInstance());
					System.out.println(object.getClassName() + "����ʵ���ɹ�");
				}
			}
			System.out.println("read xml ok");
			regManager.startAll();
			// System.out.println("AsynTask start to run");

		} catch (Exception e) {
			// TODO ��Ӵ�����־�����
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
