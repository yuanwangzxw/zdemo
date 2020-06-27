package example;

import java.util.logging.Logger;

public class Logging {

    /**
     * apache的日志接口 commons-logging,默认采用log4j实现
     * SLF4j也是日志接口，logback是实现
     * @param args
     */
    public static void main(String[] args) {
        Logger log = Logger.getGlobal();
        log.info("tmd:{}");
    }
}
