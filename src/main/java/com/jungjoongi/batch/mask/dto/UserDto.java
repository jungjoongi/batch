package com.jungjoongi.batch.mask.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private int seq;
    private String email;
    private String agreeYn;
    private String smsNo;
    private String kakaoId;
    private String regDate;
    private String modDate;
    private String sendType;

}