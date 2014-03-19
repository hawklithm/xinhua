package com.multiagent.hawklithm.readerNet.manager;

import java.sql.SQLException;

import com.google.gson.Gson;
import com.multiagent.hawklithm.leon.dao.MachineFlowRecordDAO;
import com.multiagent.hawklithm.readerNet.DO.RFIDOriginalInfos;
import com.multiagent.hawklithm.readerNet.DO.SqlRFIDOriginalObject;
import com.multiagent.hawklithm.shadowsong.DO.WardenMessage;
import com.multiagent.hawklithm.shadowsong.manager.WardenOperator;
/**
 * ���������յ������ݴ������ݿ⣬�����Ͷ��������ݵ�����Ϣ����Ϣ���ģ��� KIND_NEW_DATA_COMING+DIR_ENTER
 * @author hawklithm
 *
 */
public class ReaderDataManager {
	private MachineFlowRecordDAO machineDataDao;
	private WardenOperator readerMessageComingPusher;
	private Gson gson =new Gson();

	private int submitData(SqlRFIDOriginalObject[] infoArray) {
		int maxId=0;
//		System.out.println(gson.toJson(infoArray));
		for (SqlRFIDOriginalObject info : infoArray) {
			int tmp = 0;
			try {
				tmp = (int) machineDataDao.insertOriginalRFIDData(info.getDate(), info.getRfid(), info.getReaderId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tmp>maxId){
				maxId=tmp;
			}
		}
		return maxId;
	}
	public void OriginalDataDealing(RFIDOriginalInfos infos){
		submitData(infos.getInfos());//�������ݿ�
		sendOutDataComingMessage(infos);//������Ϣ��������ģ�����״̬����
	}
	/**
	 * ͨ����Ϣ���Ľ����յ��Ķ������������͸�������ģ��
	 * @param infos
	 */
	private void sendOutDataComingMessage(RFIDOriginalInfos infos){
		WardenMessage msg=new WardenMessage();
		msg.setKind(WardenMessage.KIND_NEW_DATA_COMING+WardenMessage.DIR_ENTER);
		String target="";
		for (String s:infos.getTargets()){
			target+=s+WardenMessage.TARGET_TYPE_READER+"|";
		}
		if (target.charAt(target.length()-1)=='|'){
			target=target.substring(0, target.length()-1);
		}
		System.out.println("message target : "+target);
		msg.setTarget(target);
		
		msg.setNote(gson.toJson(infos.getInfos()));
		readerMessageComingPusher.sendOutMessage(msg);
	}

	public MachineFlowRecordDAO getMachineDataDao() {
		return machineDataDao;
	}

	public void setMachineDataDao(MachineFlowRecordDAO machineDataDao) {
		this.machineDataDao = machineDataDao;
	}
	public WardenOperator getReaderMessageComingPusher() {
		return readerMessageComingPusher;
	}
	public void setReaderMessageComingPusher(WardenOperator readerMessageComingPusher) {
		this.readerMessageComingPusher = readerMessageComingPusher;
	}
}
