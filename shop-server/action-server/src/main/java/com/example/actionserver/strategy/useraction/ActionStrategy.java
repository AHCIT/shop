package com.example.actionserver.strategy.useraction;

import com.example.actionserver.service.impl.UserActionServiceImpl;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 行为处理策略
 * @CreateDate: 2023/4/5 00:44
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 00:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class ActionStrategy {
    public abstract boolean doAction(UserActionServiceImpl userActionService, String userId, String infoId,
                                     String status);
}
