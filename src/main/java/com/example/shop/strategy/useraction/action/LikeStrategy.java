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
    public boolean doAction(UserActionServiceImpl actionService, String userId, String infoId, String status) {
        Object cache = actionService.getRedisTemplate().opsForHash().get(REDIS_LIKE_STATUS + userId, infoId);
        if (cache == null && "0".equals(status)) {
            throw new ParamException("参数异常");
        }
        if (cache != null && status.equals(String.valueOf(cache))) {
            throw new DuplicateException("重复操作!");
        }
        RLock rLock = actionService.getRedissonClient().getLock(REDIS_LIKE_LOCK + infoId);
        if (rLock == null) {
            log.error("Fail to get like lock! userId {}, infoId {}", userId, infoId);
            return false;
        }
        rLock.lock();
        // 同一时刻只能有一个用户可以对这篇资讯点赞，所以资讯的点赞量是临界资源。换言之，需要对REDIS_LIKE_COUNT + infoId加锁
        actionService.getRedisTemplate().execute(actionService.getSetAction(), List.of(REDIS_LIKE_STATUS + userId,
                REDIS_LIKE_COUNT + infoId), infoId, status);
        log.info("Update like userId {} infoId {}, status {}, count {}!", userId, infoId, status,
                actionService.getRedisTemplate().opsForValue().get(REDIS_LIKE_COUNT + infoId));
        actionService.getActionTask().run(userId, infoId, status, UserActionEnum.LIKE.getType());
        rLock.unlock();
        return true;
    }
}
