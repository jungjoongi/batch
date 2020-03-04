package com.jungjoongi.batch.mask.service;

import com.jungjoongi.batch.mask.dto.SiteResDto;

import java.util.List;

public interface CrawlingService {

    List<SiteResDto> getWebsiteList();

    List<SiteResDto> executeCrawling();

    int updateSite(List<SiteResDto> list);
}
