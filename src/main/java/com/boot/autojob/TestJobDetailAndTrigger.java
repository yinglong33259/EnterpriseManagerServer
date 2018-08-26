package com.boot.autojob;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

@Configuration
public class TestJobDetailAndTrigger {

    @Value("${customer.quartz.cron.testJob}")
    private String cornExpression;

    @Bean("testJobDetail")
    public JobDetail testJobDetail() {
        return JobBuilder.newJob(TestJob.class).withIdentity("TestJob").storeDurably().build();
    }

//    @Bean("testJobTrigger")
//    public CronTriggerFactoryBean uploadTaskTrigger() {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setJobDetail( uploadTaskDetail() );
//        trigger.setCronExpression( cornExpression );
//        trigger.setName("testJobTrigger");
//        trigger.setBeanName("testJobTrigger");
//        return trigger;
//    }

    @Bean("testJobTrigger")
    public Trigger testJobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cornExpression);
        return TriggerBuilder.newTrigger().forJob(testJobDetail())
                .withIdentity("testJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}