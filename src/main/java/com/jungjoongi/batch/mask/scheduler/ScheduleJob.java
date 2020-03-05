package com.jungjoongi.batch.mask.scheduler;

import com.jungjoongi.batch.mask.job.MaskJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Component
@Slf4j
public class ScheduleJob {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void start() {
        JobDetail maskJobDetail = buildJobDetail(MaskJob.class, "maskJob", "job", new HashMap());
        try {
            scheduler.scheduleJob(maskJobDetail, buildJobTrigger("0 0/1 * * * ?")); // 매 1분마다 스케줄 등록
        } catch (SchedulerException e) {
            log.info(e.getMessage());
        }
    }

    public Trigger buildJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, String name, String group, Map params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).withIdentity(name,group)
                .usingJobData(jobDataMap)
                .build();
    }

}
