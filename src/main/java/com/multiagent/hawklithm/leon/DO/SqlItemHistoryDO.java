package com.multiagent.hawklithm.leon.DO;

import java.util.Date;

/**
id							int	
time						date				信息产生时间
gmt_create			date				表项创建时间
gmt_modified		date				表项修改时间
item_id					int					器械ID						
reader_id				int					读卡器ID
camera_id				int					摄像头ID
item_status			char				器械状态
equipment_id			int					设备ID
 * @author hawklithm
 * 2014-3-11下午4:30:32
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
