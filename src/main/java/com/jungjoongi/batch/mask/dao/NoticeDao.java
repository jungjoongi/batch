package com.jungjoongi.batch.mask.dao;

import com.jungjoongi.batch.mask.dto.NoticeDto;
import org.springframework.stereotype.Component;

@Component
public interface NoticeDao {

    NoticeDto selectNoticeListWithMail();

}
