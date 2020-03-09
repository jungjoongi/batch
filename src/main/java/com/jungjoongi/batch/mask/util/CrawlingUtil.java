package com.jungjoongi.batch.mask.util;

import org.jsoup.select.Elements;

public class CrawlingUtil {

    public static String parseGateway(int type, Elements elements, String flag) {
        String parseData = "";
        if (type == 0) {
            parseData = elements.text();
        } else if (type == 1) {
            parseData = parseWithSplit(elements, flag);
        }
        return parseData;
    }

    static private String parseWithSplit(Elements elements, String flag) {
        String parseData = elements.text().split(flag)[0];
        return parseData;
    }



}
