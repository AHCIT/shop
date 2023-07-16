package com.example.actionserver.service;

import com.example.actionserver.vo.ActionDetailVo;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 用户行为数据处理接口
 * @CreateDate: 2023/4/5 19:59
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 19:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserActionService {
    ActionDetailVo doAction(String userId, String infoId, String status, Integer type);
}
