package com.jd.logback.dao;

import com.jd.logback.LogbackTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoTest {
    private static Logger logger = LoggerFactory.getLogger(DaoTest.class);
    public static void main(String[] args) {
        logger.trace("DaoTest trace");
        logger.debug("DaoTest debug");
        logger.info("DaoTest info");
        logger.warn("DaoTest warn");
        /*LogbackTest.main(new String[]{});*/
    }
}
