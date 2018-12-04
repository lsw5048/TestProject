package com.jd.logback.special;

import com.jd.logback.LogbackTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 控制某条日志单独输入到某个文件
 */
public class SpecialTest {
    private static Logger logger = LoggerFactory.getLogger(SpecialTest.class);
    public static void main(String[] args) {
        logger.debug("{单独输入到一个文件}");
        logger.info("info日志");
    }
}
