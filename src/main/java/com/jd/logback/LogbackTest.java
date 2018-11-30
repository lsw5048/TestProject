package com.jd.logback;

import com.jd.logback.dao.DaoTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
    private static Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    public static void main(String[] args) {
        logger.trace("LogbackTest trace");
        logger.debug("LogbackTest debug");
        logger.info("LogbackTest info");
        logger.warn("LogbackTest warn");
        DaoTest.main(new String[]{});
    }
}
