package com.multiagent.hawklithm.leon.DO;

import java.util.Date;

/**
id							int	
time						date				��Ϣ����ʱ��
gmt_create			date				�����ʱ��
gmt_modified		date				�����޸�ʱ��
item_id					int					��еID						
reader_id				int					������ID
camera_id				int					����ͷID
item_status			char				��е״̬
equipment_id			int					�豸ID
 * @author hawklithm
 * 2014-3-11����4:30:32
 */
public class SqlItemHistoryDO {
	private Integer id;
	private Date time;
	private Date gmt_create;
	private Date gmt_modified	;
	private Integer item_id;
	private Integer reader_id;
	private Integer camera_id;
	private Character item_status;
	private Integer equipment_id;
}
