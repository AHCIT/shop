package com.example.actionserver.controller;

import com.alibaba.fastjson.JSON;
import com.example.actionserver.dto.BaseReturnDto;
import com.example.actionserver.exception.DuplicateException;
import com.example.actionserver.exception.GetLockException;
import com.example.actionserver.exception.ParamException;
import com.example.actionserver.service.UserActionService;
import com.example.actionserver.vo.ActionDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/4/3 22:54
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/3 22:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/action")
public class UserActionController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisScript<String> getUserAction;
    @Autowired
    private UserActionService userActionService;

    @GetMapping("/doAction")
    Object doAction(@RequestParam(name = "infoId") String infoId,
                    @RequestParam(name = "userId") String userId,
                    @RequestParam(name = "status") String status,
                    @RequestParam(name = "type", defaultValue = "1") Integer type) {
        try {
            if (!"1".equals(status) && !"0".equals(status)) {
                return BaseReturnDto.ofFail("参数异常");
            }
            if (type != 0 && type != 1 && type != 2 && type != 3) {
                return BaseReturnDto.ofFail("参数异常");
            }
            ActionDetailVo actionDetailVo = userActionService.doAction(userId, infoId, status, type);
            return BaseReturnDto.ofSuccess(actionDetailVo);
        } catch (DuplicateException e) {
            log.error("Duplicate action with userId {}, infoId {}, status {}", userId, infoId, status, e);
            return BaseReturnDto.ofFail(e.getMsg());
        } catch (GetLockException e) {
            log.error("Fail to get lock with userId {}, infoId {}, status {}", userId, infoId, status, e);
            return BaseReturnDto.ofFail(e.getMsg());
        } catch (ParamException e) {
            log.error("Fail to do action with wrong params userId {}, infoId {}, status {}", userId, infoId, status, e);
            return BaseReturnDto.ofFail(e.getMsg());
        } catch (Exception e) {
            log.error("Fail to do action userId {}, infoId {}, status {}", userId, infoId, status);
            return BaseReturnDto.ofFail();
        }
    }

    @GetMapping("/getActionDetail")
    Object getStatus(@RequestParam(name = "userId") String userId,
                     @RequestParam(name = "infoId") String infoId) {
        try {
            String result = redisTemplate.execute(getUserAction, Arrays.asList(userId, infoId));
            return BaseReturnDto.ofSuccess(JSON.parseObject(result, ActionDetailVo.class));
        } catch (Exception e) {
            log.error("Fail to get action detail with userId {}, infoId {}!", userId, infoId);
            return BaseReturnDto.ofFail();
        }
    }
}
