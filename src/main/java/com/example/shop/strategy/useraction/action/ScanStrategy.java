package com.example.shop.strategy.useraction.action;

import com.example.shop.enums.UserActionEnum;
import com.example.shop.exception.DuplicateException;
import com.example.shop.exception.ParamException;
import com.example.shop.service.impl.UserActionServiceImpl;
import com.example.shop.strategy.useraction.ActionStrategy;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;

import static com.example.shop.constant.RedisKeyConstant.*;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 浏览策略
 * @CreateDate: 2023/4/5 20:42
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 20:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class ScanStrategy extends ActionStrategy {
    @Override
    public boolean doAction(UserActionServiceImpl actionService, String userId, String infoId, String status) {
        Object cache = actionService.getRedisTemplate().opsForHash().get(REDIS_SCAN_STATUS + userId, infoId);
        if ("0".equals(status)) {
            if (cache == null) {
                throw new ParamException("参数异常");
            }
            if (status.equals(String.valueOf(cache))) {
                throw new DuplicateException("重复操作!");
            }
        }
        RLock rLock = actionService.getRedissonClient().getLock(REDIS_SCAN_LOCK + infoId);
        if (rLock == null) {
            log.error("Fail to get scan lock! userId {}, infoId {}", userId, infoId);
            return false;
        }
        rLock.lock();
        // 同一时刻只能有一个用户可以对这篇资讯浏览，所以资讯的浏览量是临界资源。换言之，需要对REDIS_SCAN_COUNT + infoId加锁
        actionService.getRedisTemplate().execute(actionService.getSetAction(), List.of(REDIS_SCAN_STATUS + userId, REDIS_SCAN_COUNT + infoId),
                infoId, status);
        log.info("Update scan userId {} infoId {}, status {}, count {}!", userId, infoId, status,
                actionService.getRedisTemplate().opsForValue().get(REDIS_SCAN_COUNT + infoId));
        actionService.getActionTask().run(userId, infoId, status, UserActionEnum.SCAN.getType());
        rLock.unlock();
        return true;
    }
}
