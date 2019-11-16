package com.application.entity;

public class CommonReq<T> {
	
	private String sign; //签名

	private String timestamp; //时间戳
	
	private String appId; //APPID
	
	private Integer pageNum; //页数
	
	private Integer pageSize; //每页容量
	
	private T reqData; //请求体

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppid(String appId) {
		this.appId = appId;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public T getReqData() {
		return reqData;
	}

	public void setReqData(T reqData) {
		this.reqData = reqData;
	}
	
}
