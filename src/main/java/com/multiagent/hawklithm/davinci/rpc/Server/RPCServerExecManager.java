package com.multiagent.hawklithm.davinci.rpc.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ClassUtils;

import com.google.gson.Gson;
import com.multiagent.hawklithm.davinci.rpc.DO.ParameterTypeDatas;

public class RPCServerExecManager {
	private Gson gson = new Gson();

	public Object exec(String className, String methodName, ParameterTypeDatas paramTypes,
			Object instance, String[] paramStrs) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method method;
		Object ret = null;
		int len = paramTypes.getLength();
		Class<?>[] parameterTypes = new Class<?>[len];
		Object[] params = new Object[len];
		try {
			for (int i = 0; i < len; i++) {
				Class<?> classtype = Class.forName(paramTypes.getParamType(i));
				if (paramTypes.isPrimitive(i)) {
					classtype = ClassUtils.wrapperToPrimitive(classtype);
				}
				params[i] = gson.fromJson(paramStrs[i], classtype);
				parameterTypes[i] = classtype;
			}
			method = instance.getClass().getMethod(methodName, parameterTypes);
			ret = method.invoke(instance, params);
		} catch (NoSuchMethodException e) {
			// TODO 打印日志
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 打印日志
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
