package com.enze.dao.mapper.wms;

import com.enze.entity.TaskEntity;

/**
 * 
* @ClassName: WmsTurnboxMapper
* @Description: 散件
* @author wuxuecheng
* @date 2019年3月5日
*
 */
public interface WmsTurnboxMapper {
	/**
	 * 
	* @Title: getTask
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param usestatus  状态 1=正在拣货  2=待复核 3=待装箱
	* @param @return    参数
	* @author wuxuecheng
	* @return TaskEntity    返回类型
	* @throws
	 */
	TaskEntity getTask(int usestatus);
}
