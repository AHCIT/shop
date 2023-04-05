package com.example.shop.strategy.useraction;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 行为策略工厂
 * @CreateDate: 2023/4/5 00:58
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 00:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActionStrategyFactory extends StrategyFactory {

    @Override
    public ActionStrategy createStrategy(Class c) {
        ActionStrategy product;
        try {
            product = (ActionStrategy) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
