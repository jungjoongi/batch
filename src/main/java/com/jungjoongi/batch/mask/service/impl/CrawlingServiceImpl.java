package com.jungjoongi.batch.mask.service.impl;

import com.jungjoongi.batch.mask.dao.CrawlingDao;
import com.jungjoongi.batch.mask.dto.SiteResDto;
import com.jungjoongi.batch.mask.service.CrawlingService;
import com.jungjoongi.batch.mask.util.CrawlingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CrawlingServiceImpl implements CrawlingService {
    private final static Logger LOGGER = LogManager.getLogger(CrawlingServiceImpl.class);

    private CrawlingDao crawlingDao;

    CrawlingServiceImpl(CrawlingDao crawlingDao) {
        this.crawlingDao = crawlingDao;
    }


    @Override
    public List<SiteResDto> getWebsiteList() {
        return crawlingDao.selectSiteList();
    }

    @Override
    public int updateSite(List<SiteResDto> list) {
        return crawlingDao.updateSite(list);
    }

    @Override
    @Transactional
    public List<SiteResDto> executeCrawling() {
        LOGGER.info("executeCrawling START");
        List<SiteResDto> siteResDtoList = this.getWebsiteList();
        List<SiteResDto> updateList = new ArrayList<SiteResDto>();

        Document doc = null;
        for (SiteResDto list :  siteResDtoList) {
            if(list.getUseYn().equals("Y")) {
                try {
                    doc = Jsoup.connect(list.getSiteUrl()).get();

                    String content = CrawlingUtil.parseGateway(
                            list.getParseType(),
                            doc.select(list.getContentElement()),
                            list.getParseFlag()
                    );
                    if(!list.getContent().equals(content)) {
                        list.setContent(content);
                        list.setUpdateYn("Y");
                        updateList.add(list);
                    }
                } catch (IOException e) {
                    log.info(e.getMessage());
                } finally {
                    doc = null;
                }
            }
        }

        if(updateList.size() > 0) {
            this.updateSite(updateList);
            return updateList;
        } else {
            return null;
        }
    }
}
