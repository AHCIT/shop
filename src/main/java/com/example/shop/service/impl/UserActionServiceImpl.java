package com.example.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.shop.exception.GetLockException;
import com.example.shop.service.UserActionService;
import com.example.shop.strategy.useraction.ActionContext;
import com.example.shop.strategy.useraction.ActionStrategyFactory;
import com.example.shop.strategy.useraction.action.CollectStrategy;
import com.example.shop.strategy.useraction.action.LikeStrategy;
import com.example.shop.strategy.useraction.action.ScanStrategy;
import com.example.shop.strategy.useraction.action.TransmitStrategy;
import com.example.shop.vo.ActionDetailVo;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = RuntimeException.class)
public class UserActionServiceImpl implements UserActionService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisScript<Void> setUserLike;
    @Autowired
    private RedisScript<String> getUserLike;

    @Override
    public ActionDetailVo doAction(String userId, String infoId, String status, Integer type) {
        ActionStrategyFactory actionStrategyFactory = new ActionStrategyFactory();
        ActionContext context = null;
        switch (type) {
            case 1:
                LikeStrategy strategy1 = (LikeStrategy) actionStrategyFactory.createStrategy(LikeStrategy.class);
                context = new ActionContext(strategy1);
                break;
            case 2:
                CollectStrategy strategy2 = (CollectStrategy) actionStrategyFactory.createStrategy(CollectStrategy.class);
                context = new ActionContext(strategy2);
                break;
            case 3:
                TransmitStrategy strategy3 = (TransmitStrategy) actionStrategyFactory.createStrategy(TransmitStrategy.class);
                context = new ActionContext(strategy3);
                break;
            case 4:
                ScanStrategy strategy4 = (ScanStrategy) actionStrategyFactory.createStrategy(ScanStrategy.class);
                context = new ActionContext(strategy4);
                break;
            default:
                log.warn("Cannot find match strategy, type {}!", type);
                break;
        }
        assert context != null;
        boolean success = context.doStrategy(redisTemplate, setUserLike, redissonClient, userId, infoId, status);
        if (!success) {
            throw new GetLockException("当前服务正忙，请稍后再试");
        }
        String result = redisTemplate.execute(getUserLike, List.of(userId, infoId));
        return JSON.parseObject(result, ActionDetailVo.class);
    }

}
