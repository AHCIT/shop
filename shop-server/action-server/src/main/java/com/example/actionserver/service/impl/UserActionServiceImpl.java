package com.example.actionserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.actionserver.exception.GetLockException;
import com.example.actionserver.service.UserActionService;
import com.example.actionserver.strategy.useraction.ActionContext;
import com.example.actionserver.strategy.useraction.ActionStrategyFactory;
import com.example.actionserver.strategy.useraction.action.CollectStrategy;
import com.example.actionserver.strategy.useraction.action.LikeStrategy;
import com.example.actionserver.strategy.useraction.action.ScanStrategy;
import com.example.actionserver.strategy.useraction.action.TransmitStrategy;
import com.example.actionserver.task.UserActionTask;
import com.example.actionserver.vo.ActionDetailVo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 用户行为数据处理业务处理实现
 * @CreateDate: 2023/4/5 20:00
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 20:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Service
@Getter
@Transactional(rollbackFor = RuntimeException.class)
public class UserActionServiceImpl implements UserActionService {
    private final StringRedisTemplate redisTemplate;
    private final RedissonClient redissonClient;
    private final RedisScript<Void> setAction;
    private final RedisScript<String> getAction;
    private final UserActionTask actionTask;
    private final TaskExecutor executor;

    @Autowired
    public UserActionServiceImpl(StringRedisTemplate redisTemplate, RedissonClient redissonClient,
                                 RedisScript<Void> setAction, RedisScript<String> getAction, UserActionTask actionTask,
                                 @Qualifier("userActionExecutor") TaskExecutor executor) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
        this.setAction = setAction;
        this.getAction = getAction;
        this.actionTask = actionTask;
        this.executor = executor;
    }

    @Override
    public ActionDetailVo doAction(String userId, String infoId, String status, Integer type) {
        ActionStrategyFactory actionStrategyFactory = new ActionStrategyFactory();
        ActionContext context = null;
        switch (type) {
            case 0:
                LikeStrategy strategy1 = (LikeStrategy) actionStrategyFactory.createStrategy(LikeStrategy.class);
                context = new ActionContext(strategy1);
                break;
            case 1:
                CollectStrategy strategy2 = (CollectStrategy) actionStrategyFactory.createStrategy(CollectStrategy.class);
                context = new ActionContext(strategy2);
                break;
            case 2:
                TransmitStrategy strategy3 = (TransmitStrategy) actionStrategyFactory.createStrategy(TransmitStrategy.class);
                context = new ActionContext(strategy3);
                break;
            case 3:
                ScanStrategy strategy4 = (ScanStrategy) actionStrategyFactory.createStrategy(ScanStrategy.class);
                context = new ActionContext(strategy4);
                break;
            default:
                log.warn("Cannot find match strategy, type {}!", type);
                break;
        }
        assert context != null;
        boolean success = context.doStrategy(this, userId, infoId, status);
        if (!success) {
            throw new GetLockException("当前服务正忙，请稍后再试");
        }
        String result = redisTemplate.execute(getAction, Arrays.asList(userId, infoId));
        return JSON.parseObject(result, ActionDetailVo.class);
    }
}
