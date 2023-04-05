package com.example.shop.strategy.useraction;

import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 行为处理策略
 * @CreateDate: 2023/4/5 00:44
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 00:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class ActionStrategy {
    public abstract boolean doAction(StringRedisTemplate redisTemplate, RedisScript<Void> setUserAction,
                                     RedissonClient redissonClient, String userId, String infoId, String status);
}
