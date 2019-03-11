package com.enze.service;

import java.util.concurrent.TimeUnit;

import com.enze.entity.TaskEntity;

public interface TaskService<T> {
    T getTask();
    T getTaskActual();
    void updateCaches(TaskEntity taskEntity,int timeout,TimeUnit timeUnit);
    /**
     * 
    * @Title: checkTask
    * @Description: 检查任务信息是否有变更
    * @param @return    参数
    * @author wuxuecheng
    * @return boolean    true=有变更
    * @throws
     */
    boolean checkTask();
}
