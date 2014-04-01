package com.hawklithm.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class Jsoner {
	private  static Gson normalGson=new Gson();
	public static String toJson(Object src){
		return normalGson.toJson(src);
	}
	public static <T> T fromJson(String json,Class<T>classOfT){
		return normalGson.fromJson(json, classOfT);
	}
	public static <T>T fromJson(String json,Type typeOfT){
		return normalGson.fromJson(json, typeOfT);
	}
}
