package com.multiagent.hawklithm.global.dataobject;
/**
 * target��ʾ������Ϣ��ʵ��ı��
 * kind��ʾ��Ϣ���ͣ�����ʵ��������ͷ���
 * note��ʾ��Ϣ��containt
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
