package com.jd.logback.special;

import com.jd.logback.LogbackTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 控制某条日志单独输入到某个文件
 */
public class SpecialTest {
    private static Logger logger = LoggerFactory.getLogger(SpecialTest.class);
    private static Logger logger2 = LoggerFactory.getLogger("designatedFile");
    public static void main(String[] args) {
        logger2.info("{logger2单独输入到一个指定文件}");
        logger.info("info日志");
    }
}
