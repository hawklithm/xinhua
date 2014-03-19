package com.multiagent.hawklithm.davinci.exceptioin;

/**
 * 
 * @author hawklithm
 * 
 */
public class EquipmentNotFoundException extends ModuleNotFoundException {
	private static final long serialVersionUID = -7618273651025531897L;
	private static String defaultErrorMessage = "the equipment you request is not found,please check your RFID";

	public EquipmentNotFoundException() {
		super(defaultErrorMessage);
	}

	public EquipmentNotFoundException(String msg) {
		super(msg);
	}

	public EquipmentNotFoundException(String msg, String module, Integer id) {
		super("there\'s no id[" + id + "] in module [" + module + "] " + msg);
	}

	public EquipmentNotFoundException(Exception e) {
		super(e.getMessage());
	}
}
