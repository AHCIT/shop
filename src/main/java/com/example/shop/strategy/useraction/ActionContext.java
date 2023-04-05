package com.example.shop.strategy.useraction;

import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 点赞数据处理策略
 * @CreateDate: 2023/4/5 01:10
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 01:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActionContext {
    private final ActionStrategy actionStrategy;

    public ActionContext(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public boolean doStrategy(StringRedisTemplate redisTemplate, RedisScript<Void> setUserAction,
                              RedissonClient redissonClient, String userId, String infoId, String status) {
        return actionStrategy.doAction(redisTemplate, setUserAction, redissonClient, userId, infoId, status);
    }
}
