package com.example.actionserver.vo;

import java.io.Serializable;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 行为数据封装
 * @CreateDate: 2023/4/5 19:10
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 19:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActionDetailVo implements Serializable {

    private static final long serialVersionUID = -7731966211482191724L;
    private String likeStatus;
    private Integer likeCount;
    private String collectStatus;
    private Integer collectCount;
    private String transmitStatus;
    private Integer transmitCount;
    private String scanStatus;
    private Integer scanCount;

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getTransmitStatus() {
        return transmitStatus;
    }

    public void setTransmitStatus(String transmitStatus) {
        this.transmitStatus = transmitStatus;
    }

    public Integer getTransmitCount() {
        return transmitCount;
    }

    public void setTransmitCount(Integer transmitCount) {
        this.transmitCount = transmitCount;
    }

    public String getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
    }

    public Integer getScanCount() {
        return scanCount;
    }

    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }
}
