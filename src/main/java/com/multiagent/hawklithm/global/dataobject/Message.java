package com.multiagent.hawklithm.global.dataobject;
/**
 * target表示接收信息的实体的标记
 * kind表示消息类型，所有实体更具类型分类
 * note表示消息的containt
 * @author hawklithm
 *
 */
public class Message {
	private String target;
	private String kind;
	private String note;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
