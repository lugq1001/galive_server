package com.galive.logic.network.platform.wx;

/**
 * 通过code获取access_token
 * @author luguangqing
 *
 */
public class WXAccessTokenResp extends WXResp {

	/**
	 * 接口调用凭证
	 */
	private String access_token = "";
	
	/**
	 * access_token接口调用凭证超时时间，单位（秒） 例:7200
	 */
	private long expires_in = 0;
	
	/**
	 * 用户刷新access_token
	 */
	private String refresh_token = "";
	
	/**
	 * 授权用户唯一标识
	 */
	private String openid = "";
	
	/**
	 * 用户授权的作用域，使用逗号（,）分隔
	 */
	private String scope = "";
	
	/**
	 * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
	 */
	private String unionid = "";

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
