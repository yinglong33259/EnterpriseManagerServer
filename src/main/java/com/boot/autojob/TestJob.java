package com.boot.autojob;

import com.boot.kafka.Producer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class TestJob extends QuartzJobBean {
    @Autowired
    public Producer producer;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务执行成功啦");
        producer.send("Spring Kafka and Spring Boot Send Message:"+"ssssss");
    }
}
