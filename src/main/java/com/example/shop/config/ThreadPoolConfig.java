package com.example.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/4/6 22:20
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/6 22:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 核心线程数，默认为1
     */
    @Value("${thread.pool.core.size:10}")
    private int corePoolSize = 40;

    /**
     * 最大线程数，默认为Integer.MAX_VALUE
     */
    @Value("${thread.pool.max.size:15}")
    private int maxPoolSize = 100;

    /**
     * <队列最大长度，一般需要设置值>=notifyScheduledMainExecutor.maxNum；默认为Integer.MAX_VALUE
     */
    @Value("${thread.pool.queue.capacity:10000}")
    private int queueCapacity = 10000;

    /**
     * 线程池维护线程所允许的空闲时间，单位秒，默认为60s
     */
    @Value("${thread.pool.alive.seconds:60}")
    private int keepAliveSeconds = 60;

    /**
     * 线程池定义
     */
    private TaskExecutor getExecutor(String prefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(prefix);
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 线程池
     */
    @Bean("userActionExecutor")
    public TaskExecutor userActionExecutor() {
        return getExecutor("user-action-");
    }

    /**
     * 线程池
     */
    @Bean("infoActionExecutor")
    public TaskExecutor infoActionExecutor() {
        return getExecutor("info-action-");
    }

}
