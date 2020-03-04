package com.jungjoongi.batch.mask.job;

import com.jungjoongi.batch.mask.dto.SiteResDto;
import com.jungjoongi.batch.mask.service.CrawlingService;
import com.jungjoongi.batch.mask.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j // log 사용을 위한 lombok 어노테이션
@Component
public class MaskJob extends QuartzJobBean implements InterruptableJob {

    private CrawlingService crawlingService;
    private MailService mailService;

    MaskJob(CrawlingService crawlingService, MailService mailService) {
        this.crawlingService = crawlingService;
        this.mailService = mailService;
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<SiteResDto> CrawlingList = crawlingService.executeCrawling();
        if (CrawlingList != null) {
            mailService.sendEmail(CrawlingList);
        }
    }
}
