package com.godzilla.cn.godzilla.component;

import com.godzilla.cn.godzilla.bean.JiraTask;
import com.godzilla.cn.godzilla.service.JiraTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by myz on 2017/7/10.
 *
 * 定时器
 */
@Component
public class Timegrabbing {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static List<String> lists = new ArrayList<>();
    @Autowired
    public JiraTaskService jiraTaskService;

    @Scheduled(fixedRate = 10000)
    public void timerRate() {
        System.out.println(dateFormat.format(new Date()));
    }

    /**
     *  第一次延迟1秒执行，当执行完后1分钟再执行
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000*60*1)
    public void timerInit() {
        lists.clear();
        List<JiraTask> jiraTaskList =  jiraTaskService.findAllList();
        jiraTaskList.stream().forEach(item->{
            lists.add(item.getIssuekey().trim());
        });
        System.out.println("init : "+dateFormat.format(new Date())+"，lists大小："+lists.size());
    }


    /**
     *  每天20点16分50秒时执行
     */
    @Scheduled(cron = "50 16 20 * * ?")
    public void timerCron() {
        System.out.println("current time : "+ dateFormat.format(new Date()));
    }
}
