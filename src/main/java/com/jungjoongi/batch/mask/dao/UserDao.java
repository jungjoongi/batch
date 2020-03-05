package com.jungjoongi.batch.mask.dao;

import com.jungjoongi.batch.mask.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {

    List<UserDto> selectUserList(UserDto userDto);

}
