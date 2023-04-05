package com.example.shop.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: redission锁配置
 * @CreateDate: 2023/4/5 14:18
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
public class RedissionConfig {

    @Value("${redission.address}")
    private String redissionAddress;

    @Bean
    public RedissonClient redissonClient() {
        // 配置
        Config config = new Config();
        // 地址
        config.useSingleServer().setAddress(redissionAddress);
        // 创建 RedissonClient 对象
        return Redisson.create(config);
    }
}