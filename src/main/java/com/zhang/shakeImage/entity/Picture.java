package com.zhang.shakeImage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhangsl on 2017/2/10.
 */
@Entity
public class Picture {

    @Id
    private int id;
    private String url;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
