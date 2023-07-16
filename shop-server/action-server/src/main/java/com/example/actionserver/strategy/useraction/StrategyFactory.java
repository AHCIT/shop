package com.example.actionserver.strategy.useraction;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 抽象策略工厂
 * @CreateDate: 2023/4/5 00:51
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 00:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class StrategyFactory<T> {
    public abstract ActionStrategy createStrategy(Class<T> c);
}
