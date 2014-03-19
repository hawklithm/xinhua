package com.multiagent.hawklithm.davinci.exceptioin;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -987545529673160717L;
	private static String defaultErrorMessage = "there's no order match the condition you submit";

	public OrderNotFoundException() {
		super(defaultErrorMessage);
	}

	public OrderNotFoundException(String msg) {
		super(msg);
	}

	public OrderNotFoundException(Exception e) {
		super(e.getMessage());
	}

}
