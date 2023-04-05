package com.example.shop.strategy.useraction.action;

import com.example.shop.constant.RedisKeyConstant;
import com.example.shop.enums.ReturnCode;
import com.example.shop.exception.DuplicateException;
import com.example.shop.strategy.useraction.ActionStrategy;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import static com.example.shop.constant.RedisKeyConstant.*;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 点赞数据处理策略
 * @CreateDate: 2023/4/5 01:10
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 01:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class LikeStrategy extends ActionStrategy {

    @Override
    public boolean doAction(StringRedisTemplate redisTemplate, RedisScript<Void> setUserAction,
                            RedissonClient redissonClient, String userId, String infoId, String status) {
        Object cache = redisTemplate.opsForHash().get(RedisKeyConstant.REDIS_LIKE_STATUS + userId, infoId);
        if (cache != null && status.equals(String.valueOf(cache))) {
            throw new DuplicateException(ReturnCode.PARAMETER.getCode(), "重复操作!");
        }
        RLock rLock = redissonClient.getLock(REDIS_LIKE_LOCK + infoId);
        if (rLock == null) {
            log.error("Fail to get like lock! userId {}, infoId {}", userId, infoId);
            return false;
        }
        rLock.lock();
        // 同一时刻只能有一个用户可以对这篇资讯点赞，所以资讯的点赞量是临界资源。换言之，需要对REDIS_LIKE_COUNT + infoId加锁
        redisTemplate.execute(setUserAction, List.of(REDIS_LIKE_STATUS + userId, REDIS_LIKE_COUNT + infoId),
                infoId, status);
        log.info("Update like userId {} infoId {}, status {}, count {}!", userId, infoId, status,
                redisTemplate.opsForValue().get(RedisKeyConstant.REDIS_LIKE_COUNT + infoId));
        rLock.unlock();
        return true;
    }
}
