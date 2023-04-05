package com.example.shop.constant;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: redis key 常量
 * @CreateDate: 2023/4/5 15:23
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 15:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RedisKeyConstant {
    public static final String REDIS_LIKE_COUNT = "info:like:count:";
    public static final String REDIS_LIKE_STATUS = "info:like:status:";
    public static final String REDIS_LIKE_LOCK = "info:like:lock:";
    public static final String REDIS_COLLECT_COUNT = "info:collect:count:";
    public static final String REDIS_COLLECT_STATUS = "info:collect:status:";
    public static final String REDIS_COLLECT_LOCK = "info:collect:lock:";
    public static final String REDIS_TRANSMIT_COUNT = "info:transmit:count:";
    public static final String REDIS_TRANSMIT_STATUS = "info:transmit:status:";
    public static final String REDIS_TRANSMIT_LOCK = "info:transmit:lock:";
    public static final String REDIS_SCAN_COUNT = "info:scan:count:";
    public static final String REDIS_SCAN_STATUS = "info:scan:status:";
    public static final String REDIS_SCAN_LOCK = "info:scan:lock:";
}
