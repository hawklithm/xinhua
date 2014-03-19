package com.multiagent.hawklithm.davinci.init;

import java.util.List;
/**
 * 
 * @author hawklithm
 *
 */
public class SystemInitDO {
	private List<String> configFileList;
	
	public String[] getConfigFileArray(){
		return configFileList.toArray(new String[configFileList.size()]);
//		int len=configFileList.size();
//		String[] ret=new String[len];
//		for (int i=0;i<len;i++){
//			ret[i]=configFileList.get(i);
//		}
//		return ret;
	}

	public List<String> getConfigFileList() {
		return configFileList;
	}

	public void setConfigFileList(List<String> configFileList) {
		this.configFileList = configFileList;
	}
	
}
