package com.netsense.cloudfilemanager.service;

import com.netsense.cloudfilemanager.model.PageBean;
import com.netsense.cloudfilemanager.model.Power;
import com.netsense.cloudfilemanager.model.Result;

import java.util.Map;

/**
 * @Auther: rui
 * @Date: 2018/9/17 15:46
 * @Description:
 */
public interface WebService {
    /**
     * 获取分页
     * @param pageBean
     * @return
     * @throws Exception
     */
    Result getlistbypage(PageBean pageBean) throws Exception;

    /**
     * 删除条目
     * @param param
     * @return
     * @throws Exception
     */
    Result delete(Map<String,Object> param) throws Exception;

    /**
     * 搜索
     * @param param
     * @return
     * @throws Exception
     */
    Result search(Map<String,Object> param) throws Exception;

    /**
     * 获取定时任务开关
     * @param param
     * @return
     * @throws Exception
     */
    Result getcheduletimer(Map<String,Object> param) throws Exception;

    /**
     * 是否启用定时任务
     * @param power
     * @return
     * @throws Exception
     */
    Result updatescheduletime(Power power) throws Exception;
}
