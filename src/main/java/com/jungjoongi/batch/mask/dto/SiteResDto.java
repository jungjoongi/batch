package com.jungjoongi.batch.mask.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
public class SiteResDto {
    private int seq;
    private String siteNm;
    private String content;
    private String updateYn;
    private String regDate;
    private String modDate;
    private String siteUrl;
    private String contentElement;
    private String useYn;
    private int parseType;
    private String parseFlag;
}
