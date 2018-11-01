package com.netsense.cloudfilemanager.listener;

import com.netsense.cloudfilemanager.mapper.FileUploadHistoryMapper;
import com.netsense.cloudfilemanager.mapper.PowerMapper;
import com.netsense.cloudfilemanager.model.Power;
import com.netsense.cloudfilemanager.schedule.ScheduleTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Auther: rui
 * @Date: 2018/9/28 17:47
 * @Description:
 */
@Component
public class ContextRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private FileUploadHistoryMapper fileUploadHistoryMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
//            AutowireCapableBeanFactory autowireCapableBeanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getAutowireCapableBeanFactory();
//            autowireCapableBeanFactory.autowireBean(this);
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
}
