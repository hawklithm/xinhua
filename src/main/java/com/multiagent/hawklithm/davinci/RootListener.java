package com.multiagent.hawklithm.davinci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 *  Application Lifecycle Listener implementation class RootListener
 * @author hawklithm
 *
 */
public class RootListener implements ServletContextListener {
//	public static Logger log=Logger.getLogger(RootListener.class);
	public static int safeCount=0;
	private ExecutorService threadPool=null;
	private ArchtechRoot systemRoot=null;
    /**
     * Default constructor. 
     */
    public RootListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
    	threadPool=Executors.newFixedThreadPool(10);
    	systemRoot=new ArchtechRoot();
    	Future<Boolean> future=threadPool.submit(systemRoot);
		try {
			Boolean bool = future.get();
			if (!bool){
				throw new Exception("系统初始化异常");
			}
		} catch (Exception e) {
			// TODO 打印错误日志
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    }
	
}
