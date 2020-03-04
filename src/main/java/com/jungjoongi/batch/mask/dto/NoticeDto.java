package com.jungjoongi.batch.mask.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDto {
    private int seq;
    private String notiTitle;
    private String notiContent;
    private int notiType;
    private String useYn;
    private String regDate;
    private String modDate;
}