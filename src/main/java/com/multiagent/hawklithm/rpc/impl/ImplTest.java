package com.multiagent.hawklithm.rpc.impl;

import com.multiagent.hawklithm.rpc.Interfacetest;

public class ImplTest implements Interfacetest{

	@Override
	public boolean go() {
		// TODO Auto-generated method stub
		System.out.println("gogogogogogogogogogogo");
		return true;
	}

	@Override
	public int getNumber(int z) throws Exception{
		// TODO Auto-generated method stub
		throw new Exception("etssd");
//		return z/2;
	}

}
