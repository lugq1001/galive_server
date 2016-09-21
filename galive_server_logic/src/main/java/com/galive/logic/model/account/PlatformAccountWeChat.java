 package com.galive.logic.model.account;

import org.mongodb.morphia.annotations.Entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.galive.logic.network.platform.wx.WXUserInfoResp;

@Entity(value="platform_account")
public class PlatformAccountWeChat extends PlatformAccount {

	/**
	 * 普通用户的标识，对当前开发者帐号唯一
	 */
	@JSONField(serialize = false)
	private String openid = "";
	
	/**
	 * 普通用户昵称
	 */
	@JSONField(name="nickname")
	private String nickname = "";
	
	/**
	 * 普通用户性别，1为男性，2为女性
	 */
	@JSONField(serialize = false)
	private int sex = 1;
	
	/**
	 * 普通用户个人资料填写的省份
	 */
	@JSONField(serialize = false)
	private String province = "";
	
	/**
	 * 普通用户个人资料填写的城市
	 */
	@JSONField(serialize = false)
	private String city = "";
	
	/**
	 * 国家，如中国为CN
	 */
	private String country = "";
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 */
	@JSONField(name="avatar")
	private String headimgurl = "";
	
	/**
	 * 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
	 */
	//private String privilege = "";
	
	/**
	 * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
	 */
	private String unionid = "";
	
	public static PlatformAccountWeChat convert(WXUserInfoResp resp) {
		PlatformAccountWeChat act = new PlatformAccountWeChat();
		act.setCity(resp.getCity());
		act.setUnionid(resp.getUnionid());
		act.setHeadimgurl(resp.getHeadimgurl());
		act.setCountry(resp.getCountry());
		act.setProvince(resp.getProvince());
		act.setNickname(resp.getNickname());
		act.setSex(resp.getSex());
		act.setOpenid(resp.getOpenid());
		act.setPlatform(Platform.WeChat);
		return act;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
