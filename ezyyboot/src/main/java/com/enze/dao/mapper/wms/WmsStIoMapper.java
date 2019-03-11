package com.enze.dao.mapper.wms;

import org.apache.ibatis.annotations.Param;

import com.enze.entity.TaskEntity;

/**
 * 
* @ClassName: WmsStIoMapper
* @Description: 出入库
* @author wuxuecheng
* @date 2019年3月5日
*
 */
public interface WmsStIoMapper {
	/**
	 * 
	* @Title: getTask
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param rfflag  1 =不发送 即未拣货
	* @param @param areaflag 1=整件
	* @param @return    参数
	* @author wuxuecheng
	* @return TaskEntity    返回类型
	* @throws
	 */
	TaskEntity getTask(@Param("operationtype")int operationtype ,@Param("rfflag")int rfflag,@Param("areaflag")int areaflag);
}
