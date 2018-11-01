package com.netsense.cloudfilemanager.listener;

import com.netsense.cloudfilemanager.mapper.FileUploadHistoryMapper;
import com.netsense.cloudfilemanager.mapper.PowerMapper;
import com.netsense.cloudfilemanager.model.Power;
import com.netsense.cloudfilemanager.schedule.ScheduleTimer;
import com.netsense.cloudfilemanager.xskybasic.Xskythread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Auther: rui
 * @Date: 2018/9/19 10:17
 * @Description:
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private FileUploadHistoryMapper fileUploadHistoryMapper;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            AutowireCapableBeanFactory autowireCapableBeanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getAutowireCapableBeanFactory();
            autowireCapableBeanFactory.autowireBean(this);
            Power power = powerMapper.selectByPrimaryKey(1);
            if (power.getUpdatetype() == 1) {
                ScheduleTimer.startSchedule(power.getDay(), new ScheduleTimer(fileUploadHistoryMapper, 0));
            }
            if (power.getUpdatetype() == 0) {
                ScheduleTimer.shutdownSchedule();
            }
        } catch (Exception e) {
            logger.error(" contextlistener failed", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
