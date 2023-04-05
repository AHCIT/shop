package com.example.shop.service.impl;

import com.example.shop.dto.Order;
import com.example.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/3/25 21:56
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/3/25 21:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public String demo() {
        return null;
    }
}
