package com.jungjoongi.batch.mask.dao;

import com.jungjoongi.batch.mask.dto.JobUserDto;
import com.jungjoongi.batch.mask.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobUserDao {

    List<JobUserDto> selectJobUserList(JobUserDto jobUserDto);

}
