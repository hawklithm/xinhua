package com.multiagent.hawklithm.davinci.exceptioin;

import org.springframework.dao.DataAccessException;

public class DataBadFormatException extends DataAccessException{

	private static final long serialVersionUID = 2709469465548601488L;

	public DataBadFormatException(String msg) {
		super(msg);
	}

}
