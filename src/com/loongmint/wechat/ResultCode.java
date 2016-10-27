package com.loongmint.wechat;

public class ResultCode {
	
	/** 系统繁忙，此时请开发者稍候再试 **/
	public static final int CODE_WEIXIN_ERROR = -1;
	
	/** 请求成功 **/
	public static final int CODE_SUCCESS = 0;

	/** 获取access_token时AppSecret错误，或者access_token无效 **/
	public static final int CODE_ACCESS_TOKEN_INVALID = 40001;
	
	/** access_token超时，请检查access_token的有效期 **/
	public static final int CODE_TOKEN_EXPIRED = 42001;
	
	/** api功能未授权，请确认公众号已获得该接口 **/
	public static final int CODE_API_UNAUTHORIZED = 48001;
	
	/** 不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID **/
	public static final int CODE_OPENID_ILLEGAL = 40003;
	
	/** access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明 **/
	public static final int CODE_ACCESS_TOKEN_TIMEOUT = 42001;
	
	/** 不合法的oauth_code **/
	public static final int CODE_OAUTH_CODE_ILLEGAL = 40029;
	
}