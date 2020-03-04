package com.jungjoongi.batch.mask.dao;

import com.jungjoongi.batch.mask.dto.SiteResDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrawlingDao {
    List<SiteResDto> selectSiteList();

    int updateSite(List<SiteResDto> list);

}
