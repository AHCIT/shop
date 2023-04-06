package com.example.shop.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/4/6 23:43
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/6 23:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component
public class UserActionTask {

    @Async("userActionExecutor")
    public void run(String userId, String infoId, String status, Integer type) {
        // todo
    }
}
