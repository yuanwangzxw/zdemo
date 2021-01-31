package example;

import lombok.SneakyThrows;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zxw
 * 雪花算法64bit的数字，第一位始终为0，表示正数
 * 41bit作为毫秒数 2^41相当于69年
 * 10bit作为机器id 2^10有1024台机器
 * 12bit序列号     2^12每秒能生成4096个id
 */
public class SnowflakeIdUtil {
    // ==============================Fields===========================================
    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1596037883039L;
    /** 机器id所占的位数 */
    private final long workerIdBits = 5L;
    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 5L;
    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    //-1就是0xFF，与任何数异或相当于取反      ~(-1<<workerIdBits)
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /** 支持的最大数据标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /** 序列在id中占的位数 */
    private final long sequenceBits = 10L;
    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;
    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /** 时间截向左移22位(5+5+12) */
//    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long timestampLeftShift = sequenceBits;
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /** 工作机器ID(0~31) */
    private long workerId;
    /** 数据中心ID(0~31) */
    private long datacenterId;
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;
    private static SnowflakeIdUtil idWorker;
    /**
     * 以SnowFlake算法，获取唯一有序id
     * @return
     */
    public static long getSnowflakeId() {
        if(idWorker == null) {
            synchronized (SnowflakeIdUtil.class) {
                if(idWorker == null) {
                    idWorker = new SnowflakeIdUtil(0, 0);
                }
            }
        }
        return idWorker.nextId();
    }
    // ==============================Methods==========================================
    private SnowflakeIdUtil() {
    }
    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    private SnowflakeIdUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    protected synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
//        return ((timestamp - twepoch) << timestampLeftShift) //
//                | (datacenterId << datacenterIdShift) //
//                | (workerId << workerIdShift) //
//                | sequence;
        return ((timestamp - twepoch) << timestampLeftShift) | sequence;
    }


    static final char[] DIGITS =
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                    'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                    'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private static String to62RadixString(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            int remainder = (int) (seq % 62);
            sBuilder.append(DIGITS[remainder]);
            seq = seq / 62;
            if (seq == 0) {
                break;
            }
        }
        return sBuilder.reverse().toString();
    }


    @SneakyThrows
    public static void main(String[] args) {
        long snowflakeId = getSnowflakeId();
        String uuid = to62RadixString(snowflakeId);
        System.out.println("uuid = " + uuid);

//        Set<String> set = new HashSet<>();
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start("测试");
//        for (int i = 0; i < 1000; i++) {
//            for (int j = 0; j < 1000; j++) {
////                set.add(to62RadixString(getSnowflakeId()));
//                getSnowflakeId();
////                to62RadixString(getSnowflakeId());
//            }
//        }
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());
//        System.out.println("set.size() = " + set.size());
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2030-10-01 00:00:00").getTime();
        long start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-10-01 00:00:00").getTime();
        System.out.println("time = " + time);
        System.out.println("start = " + start);
        long end = 1917014400000L - 1601481600000L;
        System.out.println("end = " + end);
    }
}

