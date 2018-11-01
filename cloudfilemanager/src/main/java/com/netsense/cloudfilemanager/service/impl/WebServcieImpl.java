package com.netsense.cloudfilemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netsense.cloudfilemanager.enums.ResultEnum;
import com.netsense.cloudfilemanager.mapper.FileUploadHistoryMapper;
import com.netsense.cloudfilemanager.mapper.PowerMapper;
import com.netsense.cloudfilemanager.model.FileUploadHistory;
import com.netsense.cloudfilemanager.model.PageBean;
import com.netsense.cloudfilemanager.model.Power;
import com.netsense.cloudfilemanager.model.Result;
import com.netsense.cloudfilemanager.schedule.ScheduleTimer;
import com.netsense.cloudfilemanager.service.WebService;
import com.netsense.cloudfilemanager.utils.DateUtil;
import com.netsense.cloudfilemanager.utils.ResultUtil;
import com.netsense.cloudfilemanager.xskybasic.Xskythread;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: rui
 * @Date: 2018/9/17 15:49
 * @Description:
 */
@Service("webService")
public class WebServcieImpl implements WebService {

    private final Logger logger = LoggerFactory.getLogger(WebServcieImpl.class);

    @Autowired
    private FileUploadHistoryMapper fileUploadHistoryMapper;

    @Autowired
    private PowerMapper powerMapper;

    @Override
    public Result getlistbypage(PageBean pageBean) throws Exception {
        if (pageBean != null) {
            PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
            List<FileUploadHistory> fileUploadHistoryList = fileUploadHistoryMapper.selectAll();
            PageInfo pageInfo = new PageInfo(fileUploadHistoryList);
            return ResultUtil.success(pageInfo);
        } else {
            return ResultUtil.error(ResultEnum.FAILD);
        }
    }

    @Override
    public Result delete(Map<String,Object> params) throws Exception {

            String ids = (String) params.get("token");
            String[] array = ids.split(",");

            //初始化云
            Xskythread.init();
            for (int i=0;i<array.length;i++){
                FileUploadHistory fileUploadHistory = new FileUploadHistory();
                fileUploadHistory.setToken(array[i]);
                fileUploadHistory.setFlag(1);
                fileUploadHistory.setUpdatetime(new Date());
                fileUploadHistoryMapper.updateByPrimaryKeySelective(fileUploadHistory);
                //删除云文件
                Xskythread.deletefile(array[i].toLowerCase(), array[i]);
            }
            return ResultUtil.success(ResultEnum.SUCCESS);

    }

    @Override
    public Result search(Map<String, Object> params) throws Exception {
            String start = (String) params.get("starttime");
            String end = (String) params.get("endtime");
            String usercode = StringUtils.isNotBlank((String) params.get("usercode"))?(String) params.get("usercode"):null;
            String currentPage = (String) params.get("currentPage");
            String pageSize = (String) params.get("pageSize");
            Date starttime = DateUtil.parseDate(start);
            Date endtime = DateUtil.parseDate(end);
            int currenpage = Integer.valueOf(currentPage);
            int pagesize = Integer.valueOf(pageSize);

            PageHelper.startPage(currenpage, pagesize);
            List<FileUploadHistory> fileUploadHistoryList = fileUploadHistoryMapper.search(starttime, endtime, usercode);
            PageInfo<FileUploadHistory> pageInfo = new PageInfo<FileUploadHistory>(fileUploadHistoryList);
            return ResultUtil.success(pageInfo);
    }

    @Override
    public Result getcheduletimer(Map<String, Object> param) throws Exception {
//        String day = (String) param.get("day");
        Power power = powerMapper.selectByPrimaryKey(1);
        return ResultUtil.success(power);
    }

    @Override
    public Result updatescheduletime(Power power) throws Exception {
        powerMapper.updateByPrimaryKeySelective(power);
        if (power.getUpdatetype() == 1){
            ScheduleTimer.startSchedule(power.getDay(),new ScheduleTimer(fileUploadHistoryMapper,power.getDay()));
        }
        if (power.getUpdatetype() == 0){
            ScheduleTimer.shutdownSchedule();
        }
        return ResultUtil.success(ResultEnum.SUCCESS);
    }
}
