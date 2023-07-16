package com.example.orderserver.mq.producer;

import com.example.orderserver.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/3/26 14:17
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/3/26 14:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component
public class OrderProducer {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    void produce() {
        String topic = "order";
        Order test = new Order();
        test.setId("test");
        test.setCreateTime(System.currentTimeMillis());
        ListenableFuture<SendResult<String, Order>> future = kafkaTemplate.send(topic, test);
        future.addCallback(result -> {
                    assert result != null;
                    log.info("生产者成功发送消息到topic:{} partition:{}的消息",
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition());
                },
                ex -> log.error("生产者发送消失败，原因：{}", ex.getMessage()));
    }
}
