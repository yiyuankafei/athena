package com.application.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户信息，从支付宝端返回
 */
public class User implements Serializable {
    private static final long serialVersionUID = 69933799614690L;

    private Long id;

    private String userId; //支付宝ID

    private String avatar; //头像地址

    private String province; //省份

    private String city; //城市

    private String nickName; //昵称

    private String isStudentCertified; //是否为学生

    private String userType; //用户类型  1-公司账户  2-个人账户

    private String userStatus; //用户状态  Q-快速注册用户  T-已认证用户  B-被冻结账户  W-已注册未激活

    private String isCertified; //是否通过实名认证  T-通过  F-未通过

    private String gender; //性别  F-女性  M-男性

    private Date lastUpdateTime; //最后更新时间
    
    private Double lng; //本次登陆地点经度
    
    private Double lat; //本次登陆地点纬度

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getIsStudentCertified() {
        return isStudentCertified;
    }

    public void setIsStudentCertified(String isStudentCertified) {
        this.isStudentCertified = isStudentCertified == null ? null : isStudentCertified.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified == null ? null : isCertified.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
    
}