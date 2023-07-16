package com.example.actionserver.strategy.useraction;

import com.example.actionserver.service.impl.UserActionServiceImpl;

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

    public boolean doStrategy(UserActionServiceImpl userActionService, String userId, String infoId, String status) {
        return actionStrategy.doAction(userActionService, userId, infoId, status);
    }
}
