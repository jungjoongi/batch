package com.jungjoongi.batch.mask.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface CodeDao {

    Map<String,String> getCode();

}
