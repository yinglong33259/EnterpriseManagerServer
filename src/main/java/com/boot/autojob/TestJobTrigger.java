package com.boot.autojob;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestJobTrigger {

    @Value("${customer.quartz.cron.TestJob}")
    private String cornExpression;

    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(TestJob.class).withIdentity("testJob").storeDurably().build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cornExpression);
        return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
                .withIdentity("testJob")
                .withSchedule(scheduleBuilder)
                .build();
    }
}