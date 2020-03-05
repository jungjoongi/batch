package com.jungjoongi.batch.mask.service.impl;

import com.jungjoongi.batch.mask.dao.JobUserDao;
import com.jungjoongi.batch.mask.dao.NoticeDao;
import com.jungjoongi.batch.mask.dao.UserDao;
import com.jungjoongi.batch.mask.dto.JobUserDto;
import com.jungjoongi.batch.mask.dto.NoticeDto;
import com.jungjoongi.batch.mask.dto.SiteResDto;
import com.jungjoongi.batch.mask.dto.UserDto;
import com.jungjoongi.batch.mask.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    private final static Logger LOGGER = LogManager.getLogger(CrawlingServiceImpl.class);
    private UserDao userDao;
    private NoticeDao noticeDao;
    private JobUserDao jobUserDao;

    MailServiceImpl(UserDao userDao, NoticeDao noticeDao, JobUserDao jobUserDao) {
        this.userDao = userDao;
        this.noticeDao = noticeDao;
        this.jobUserDao = jobUserDao;
    }

    @Override
    public void sendEmail(List<SiteResDto> CrawlingList) {

        try {
            this.send(CrawlingList);

        } catch(Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    public void send(List<SiteResDto> CrawlingList) throws Exception {
        /** DAO SELECT 선언 s */
        JobUserDto jobUserDto = new JobUserDto();
        jobUserDto.setSendType(1);

        List<UserDto> userDtoList = this.getUserList();
        List<JobUserDto> jobUserDtoList = this.selectJobUserList(jobUserDto);
        NoticeDto noticeDto = noticeDao.selectNoticeListWithMail();
        /** DAO SELECT 선언 e */

        /** 컨텐츠 부분 선언 s */
        Date date = new Date();
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String nowTime = simpleDateFormat.format(date);
        String title = noticeDto.getNotiTitle().replace("$date$", nowTime);
        StringBuffer contentSb = new StringBuffer();
        String content = "";
        /** 컨텐츠 부분 선언 e */

        for (SiteResDto list : CrawlingList) {
                contentSb.append("[사이트명]\n").append(list.getSiteNm()).append("\n")
                .append("[주소]\n").append(list.getSiteUrl()).append("\n");
        }
        content = noticeDto.getNotiContent().replace("$content$", contentSb.toString());

        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(prop, new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(jobUserDtoList.get(0).getEmail(), jobUserDtoList.get(0).getEmailPw()); //   발송자 ID 및 possword
            }
        });
        for(UserDto list : userDtoList) {
            MimeMessage message = new MimeMessage(session);
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress("dev.joongi@gmail.com", "dev.정준기"));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(list.getEmail(), "user"));
            message.setSubject(title, "UTF-8");
            message.setText(content, "UTF-8");

            Transport.send(message);
        }

    }

    @Override
    public List<UserDto> getUserList() {
        return userDao.selectUserList();
    }

    @Override
    public List<JobUserDto> selectJobUserList(JobUserDto jobUserDto) {
        return jobUserDao.selectJobUserList(jobUserDto);
    }
}
