package com.system.core.quartz;

import com.boot.autojob.TestJob;
import com.system.core.controller.ServiceController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;

@Configuration
//@PropertySource("classpath:quartz.properties")
public class QuartzConfig {
    private static final Log logger = LogFactory.getLog(QuartzConfig.class);

    @Autowired(required = true)
    private ApplicationContext context;
    @Autowired
    private Environment env;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(
                new AutowireCapableBeanJobFactory( this.context.getAutowireCapableBeanFactory() )
        );
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        //bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动10秒后
        schedulerFactoryBean.setStartupDelay(10);
        return schedulerFactoryBean;
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     * 并注入任务
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws SchedulerException {
        logger.info("Quartz Job config start >>> ");
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        //获取所有配置的Trigger，即要触发的Trigger
        String[] available_job = this.env.getProperty("customer.quartz.available_job").split(",");

        for (int i=0; i<available_job.length; i++){
            JobDetail jobdetail = (JobDetail) this.context.getBean( available_job[i]+"Detail" );
            Trigger trigger = (Trigger) this.context.getBean( available_job[i]+"Trigger" );
            scheduler.scheduleJob(jobdetail,trigger);
            logger.info("Quartz Job -"+available_job[i]+"- load success !!! ");
        }
        logger.info("Quartz Job config end <<< ");
        return scheduler;
    }

}