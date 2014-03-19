package com.multiagent.hawklithm.leon.module.plugin;

import java.util.HashSet;
import java.util.Set;

import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;

public class SingleReaderInAndOutDirGetter implements DirGetter{

	Set<Integer> set=new HashSet<Integer>();
	@Override
	public String getDir(Object object) {
		Integer tmp=(Integer) object;
		if (set.contains(tmp)){
			set.remove(tmp);
			return WardenMessage.DIR_EXIT;
		}else 
		{
			return WardenMessage.DIR_ENTER;
		}
	}

}
