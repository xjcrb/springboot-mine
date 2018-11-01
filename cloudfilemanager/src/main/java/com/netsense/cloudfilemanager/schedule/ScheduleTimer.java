package com.netsense.cloudfilemanager.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.netsense.cloudfilemanager.mapper.FileUploadHistoryMapper;
import com.netsense.cloudfilemanager.model.FileUploadHistory;
import com.netsense.cloudfilemanager.utils.DateUtil;
import com.netsense.cloudfilemanager.utils.PropertiesUtil;
import com.netsense.cloudfilemanager.xskybasic.Xskythread;
import lombok.Data;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: rui
 * @Date: 2018/9/18 09:46
 * @Description:
 */
@Data
public class ScheduleTimer implements Runnable {

    private static final long period = 24*60*60*1000;

    private static final String time = PropertiesUtil.getPropertieValue("common.properties","time");

    private FileUploadHistoryMapper fileUploadHistoryMapper;

    private int days;

    public ScheduleTimer(FileUploadHistoryMapper fileUploadHistoryMapper, int days) {
        this.fileUploadHistoryMapper = fileUploadHistoryMapper;
        this.days = days;
    }

    private static ScheduledExecutorService executorService;

    public static void init() {
        if (executorService == null) {
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
            executorService = new ScheduledThreadPoolExecutor(1, namedThreadFactory);
        }
    }

    @Override
    public void run() {
        Date date = DateUtil.getlastfewday(new Date(), days);
        List<FileUploadHistory> fileUploadHistoryList = fileUploadHistoryMapper.getlastfewday(date);
        Xskythread.init();
        for (int i = 0; i < fileUploadHistoryList.size(); i++) {
            FileUploadHistory fileUploadHistory = new FileUploadHistory();
            fileUploadHistory.setToken(fileUploadHistoryList.get(i).getToken());
            fileUploadHistory.setFlag(1);
            fileUploadHistory.setUpdatetime(new Date());
            fileUploadHistoryMapper.updateByPrimaryKeySelective(fileUploadHistory);
            Xskythread.deletefile(fileUploadHistoryList.get(i).getToken().toLowerCase(),fileUploadHistoryList.get(i).getToken());
        }
    }

    /**
     * 启用定时任务线程
     * @param day
     * @param scheduleTimer
     * @throws Exception
     */
    public static void startSchedule(int day, ScheduleTimer scheduleTimer) throws Exception {
        if(executorService == null || executorService.isShutdown()){
            init();
            System.out.println(executorService.isShutdown());
            executorService.scheduleAtFixedRate(scheduleTimer, dayOfDelay(time), period, TimeUnit.MILLISECONDS);
            System.out.println(executorService.isShutdown());
        }
    }

    /**
     * 停掉定时任务线程
     */
    public static void shutdownSchedule() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
            executorService=null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, namedThreadFactory);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "========");
            }
        },0, period, TimeUnit.MILLISECONDS);
    }


    /**
     * 每天晚上定时执行一次
     * @param time 20:00:00
     */
    public static long dayOfDelay(String time) throws Exception {
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay  = getTimeMillis(time) - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        return initDelay;
    }


    /**
     * 获取给定时间对应的毫秒数
     *
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) throws Exception {
        Date currentDate = DateUtil.parseDateTime(DateUtil.formatDate(new Date()) + " " + time);
        return currentDate.getTime();
    }

}
