package com.jungjoongi.batch.mask.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobUserDto {
    private int seq;
    private String alias;
    private int sendType;
    private String email;
    private String smsNo;
    private String kakaoId;
    private String regDate;
    private String modDate;
    private String emailPw;
    private String birth;
    private String kakaoPw;
    private String useYn;

}