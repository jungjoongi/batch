package com.jungjoongi.batch.mask.service;

import com.jungjoongi.batch.mask.dto.JobUserDto;
import com.jungjoongi.batch.mask.dto.SiteResDto;
import com.jungjoongi.batch.mask.dto.UserDto;

import java.util.List;

public interface MailService {
    void sendEmail(List<SiteResDto> CrawlingList);
    List<UserDto> getUserList(UserDto userDto);
    List<JobUserDto> selectJobUserList(JobUserDto jobUserDto);
}
