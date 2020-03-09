package com.jungjoongi.batch.mask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class MaskApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void crawling() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://smartstore.naver.com/mfbshop/category/ALL?cp=1").get();

            System.out.println("asd");
            System.out.println(doc.select(".area_status .soldout span").text());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            doc = null;
        }
    }
}
