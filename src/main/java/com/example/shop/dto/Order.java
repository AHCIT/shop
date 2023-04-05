package com.example.shop.dto;

import java.io.Serializable;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/4/5 22:28
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 22:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Order implements Serializable {
    private String id;
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
