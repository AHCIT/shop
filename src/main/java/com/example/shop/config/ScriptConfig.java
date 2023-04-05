package com.example.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: lua脚本
 * @CreateDate: 2023/4/3 23:19
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/3 23:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
public class ScriptConfig {
    @Bean
    public RedisScript<Void> setUserLike() {
        Resource script = new ClassPathResource("script/setUserAction.lua");
        return RedisScript.of(script, Void.class);
    }

    @Bean
    public RedisScript<String> getUserLike() {
        Resource script = new ClassPathResource("script/getUserAction.lua");
        return RedisScript.of(script, String.class);
    }
}
