package com.zhang.shakeImage.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zhangsl on 2017/2/10.
 */
@Entity
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String url;// 图片绝对路径
	private String md5;// url 加密
	private String type;// 图片类型
	private String statusCode;// 状态码

	private String contentType;// URL类型
	private Date createTime;// 创建时间
	private Date updateTime;// 上次使用时间

	private long loadTime;// 加载时间,ms

	public Picture() {
		this.createTime = new Date();
	}

	public Picture(Long id, String url, String md5, String type, String statusCode, String contentType, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.url = url;
		this.md5 = md5;
		this.type = type;
		this.statusCode = statusCode;
		this.contentType = contentType;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}
}
