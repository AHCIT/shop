package com.example.actionserver.strategy.useraction.action;

import com.example.actionserver.enums.UserActionEnum;
import com.example.actionserver.exception.DuplicateException;
import com.example.actionserver.exception.ParamException;
import com.example.actionserver.service.impl.UserActionServiceImpl;
import com.example.actionserver.strategy.useraction.ActionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;

import java.util.Arrays;

import static com.example.actionserver.constant.RedisKeyConstant.*;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/4/5 20:42
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 20:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class CollectStrategy extends ActionStrategy {
    @Override
    public boolean doAction(UserActionServiceImpl actionService, String userId, String infoId, String status) {
        Object cache = actionService.getRedisTemplate().opsForHash().get(REDIS_COLLECT_STATUS + userId, infoId);
        if (cache == null && "0".equals(status)) {
            throw new ParamException("参数异常");
        }
        if (cache != null && status.equals(String.valueOf(cache))) {
            throw new DuplicateException("重复操作!");
        }
        RLock rLock = actionService.getRedissonClient().getLock(REDIS_COLLECT_LOCK + infoId);
        if (rLock.isLocked()) {
            log.error("Fail to get collect lock! userId {}, infoId {}", userId, infoId);
            return false;
        }
        rLock.lock();
        // 同一时刻只能有一个用户可以对这篇资讯收藏，所以资讯的收藏量是临界资源。换言之，需要对REDIS_COLLECT_COUNT + infoId加锁
        actionService.getRedisTemplate().execute(actionService.getSetAction(), Arrays.asList(REDIS_COLLECT_STATUS + userId, REDIS_COLLECT_COUNT + infoId),
                infoId, status);
        log.info("Update collect userId {} infoId {}, status {}, count {}!", userId, infoId, status,
                actionService.getRedisTemplate().opsForValue().get(REDIS_COLLECT_COUNT + infoId));
        actionService.getActionTask().run(userId, infoId, status, UserActionEnum.COLLECT.getType());
        rLock.unlock();
        return true;
    }
}
