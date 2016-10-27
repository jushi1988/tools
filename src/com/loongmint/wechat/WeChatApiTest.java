package com.loongmint.wechat;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 微信接入工具测试包
* @author LoongMint 
* @date 2016年10月27日 下午5:28:27
 */
public class WeChatApiTest {

	/** AppID(应用ID) **/
	private static final String APP_ID = "123456";
	
	/** AppSecret(应用密钥) **/
	private static final String APP_SECRET = "123456";
	
	private static final String TEMPLATE_ID = "123456"; // 微信模板ID
	
	private static String ACCESS_TOKEN = "";
	
	static String openIds = "123456";
	
	public static void main(String[] args) {
		WeChatApiTest test = new WeChatApiTest();
		if (ACCESS_TOKEN.length() == 0) {
			System.out.println("----------------------获取access_token start----------------------");
			System.out.println(test.getAccessToken());
			System.out.println("----------------------获取access_token end----------------------");
		} else {
			System.out.println("access_token: " + ACCESS_TOKEN);
		}
		
		
//		System.out.println();
//		System.out.println();
//		System.out.println("----------------------获取微信服务器IP地址start----------------------");
//		test.getCallbackIp();
//		System.out.println("----------------------获取微信服务器IP地址start----------------------");
		
//		test.getAuthCode();
//		test.addKf();
//		test.getkflist();
//		test.sendCustomMsg();
		
		// 发送模板消息start
//		test.tempSetIndustry();
//		test.tempAddTemplate();
//		test.getAllTemplate();
//		test.sendTemplateMsg();
		// 发送模板消息end
		
//		String token = test.getAccessToken();
//		String[] aRy = openIds.split(",");
//		if (aRy != null && aRy.length > 0) {
//			for (String openId : aRy) {
//				test.getUserInfo(token, openId);
//			}
//		}
	}
	
	/**
	 * 获取access_token
	 */
	public String getkflist() {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 1.1-获取设置的行业信息
	 */
	public String tempGetIndustry() {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 1-设置所属行业
	 */
	public String tempSetIndustry() {
		try {
			String param = "{\"industry_id1\":\"1\",\"industry_id2\":\"2\"}";
			String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST", "application/json", param);
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 2-获得模板ID
	 */
	public String tempAddTemplate() {
		try {
			String param = "{\"industry_id1\":\"1\",\"industry_id2\":\"2\"}";
			String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST", "application/json", param);
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 3-获取模板列表
	 */
	public String getAllTemplate() {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 4-发送模板消息
	 */
	public String sendTemplateMsg() {
		try {
			WeChatTemplate t = new WeChatTemplate();
			t.setUrl("http://zhiyun.95155.com/");
			t.setTouser("obqkrs7otWBVwMdAGYLGjmP42j_c");
			t.setTopcolor("#000000");
			t.setTemplate_id(TEMPLATE_ID);
			Map<String,TemplateData> m = new HashMap<String,TemplateData>();
			TemplateData first = new TemplateData();
			first.setColor("red");
			first.setValue("您收到了一条新的笔记本资源订单");
			m.put("first", first);
			
			TemplateData tradeDateTime = new TemplateData();
			tradeDateTime.setColor("#000000");
			tradeDateTime.setValue("2016-10-01");
			m.put("tradeDateTime", tradeDateTime);
			
			TemplateData orderType = new TemplateData();
			orderType.setColor("#000000");
			orderType.setValue("确认订单");
			m.put("orderType", orderType);
			
			TemplateData customerInfo = new TemplateData();
			customerInfo.setColor("#000000");
			customerInfo.setValue("北京 张三");
			m.put("customerInfo", customerInfo);
			
			TemplateData orderItemName = new TemplateData();
			orderItemName.setColor("#000000");
			orderItemName.setValue("笔记本型号");
			m.put("orderItemName", orderItemName);
			
			TemplateData orderItemData = new TemplateData();
			orderItemData.setColor("#000000");
			orderItemData.setValue("联系yoga4,XY-23129型,280mm*18mm*380mm");
			m.put("orderItemData", orderItemData);
			
			TemplateData remark = new TemplateData();
			remark.setColor("blue");
			remark.setValue("具体事宜请联系对方手机号：15x0000xxxx");
			m.put("remark", remark);
			t.setData(m);
			
			String param = JSON.toJSONString(t);
			
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST", "application/json", param);
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取access_token
	 */
	public String getAccessToken() {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
			AccessToken token = JSON.parseObject(res, AccessToken.class);
			if (token.getErrcode() == ResultCode.CODE_SUCCESS) {
				ACCESS_TOKEN = token.getAccess_token();
				System.out.println("token: " + ACCESS_TOKEN);
				return token.getAccess_token();
			} else {
				System.out.println("errmsg:" + token.getErrmsg());
				return "";
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取微信服务器IP地址
	 */
	public void getCallbackIp() {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
			CallbackIp ip = JSON.parseObject(res, CallbackIp.class);
			if (ip.getErrcode() == ResultCode.CODE_SUCCESS) {
				List<String> ips = ip.getIp_list();
				if (ips != null && ips.size() > 0) {
					for (String i : ips) {
						System.out.println("ip: " + i);
					}
				}
			} else if (ResultCode.CODE_TOKEN_EXPIRED == ip.getErrcode()) {
				System.out.println("access_token time out, begin to acquire again");
				getAccessToken();
				System.out.println("access_token time out, acquire end, access_token:" + ACCESS_TOKEN);
				getCallbackIp();
			} else {
				System.out.println("errmsg: " + ip.getErrmsg());
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * @param accessToken
	 * @param openId
	 */
	public void getUserInfo(String accessToken, String openId) {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
			System.out.println(url);
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
			UserInfo user = JSON.parseObject(res, UserInfo.class);
			if (user.getErrcode() == ResultCode.CODE_SUCCESS) {
				System.out.println("nickname:" + user.getNickname());
			} else if (ResultCode.CODE_TOKEN_EXPIRED == user.getErrcode()) {
				System.out.println("access_token time out, begin to acquire again");
				getAccessToken();
				System.out.println("access_token time out, acquire end, access_token:" + ACCESS_TOKEN);
				getUserInfo(accessToken, openId);
			} else {
				System.out.println("errmsg: " + user.getErrmsg());
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量获取用户基本信息
	 */
	public void getUserOpenIds() {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
			UserList json = JSON.parseObject(res, UserList.class);
			if (json.getErrcode() == ResultCode.CODE_SUCCESS) {
				List<UserInfo> list = json.getUser_info_list();
				for (UserInfo user : list) {
					System.out.println("nickname:" + user.getNickname());
				}
			} else if (ResultCode.CODE_TOKEN_EXPIRED == json.getErrcode()) {
				System.out.println("access_token time out, begin to acquire again");
				getAccessToken();
				System.out.println("access_token time out, acquire end, access_token:" + ACCESS_TOKEN);
				getUserOpenIds();
			} else {
				System.out.println("errmsg:" + json.getErrmsg());
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 微信Auth2.0认证 **/
	
//	String redirect_uri = "https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60";
	String redirect_uri = "http://jianpage.com";
	String scope = "snsapi_base";
	
	/**
	 * 第一步：用户同意授权，获取code
	 * 应用授权作用域，
	 * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
	 * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 */
	public void getAuthCode() {
		try {
			redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=" + scope + "&state=" + System.currentTimeMillis() + "#wechat_redirect";
			System.out.println(url);
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 第二步：通过code换取网页授权access_token
	 * @param code
	 */
	public String getAuthAccessToken(String code) {
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
			System.out.println(url);
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
			AuthAccessToken token = JSON.parseObject(res, AuthAccessToken.class);
			if (token.getErrcode() == ResultCode.CODE_SUCCESS) {
				System.out.println("token:" + token.getAccess_token());
				System.out.println("refresh_token:" + token.getRefresh_token());
				System.out.println("openid:" + token.getOpenid());
				return token.getAccess_token();
//				int errorCode = isAccessTokenValid(token.getAccess_token(), token.getOpenid());
//				if (errorCode == ResultCode.CODE_ACCESS_TOKEN_TIMEOUT) {
//					getAuthRefreshToken(token.getRefresh_token());
//				}
			} else {
				System.out.println("errmsg: " + token.getErrmsg());
				return "";
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 第三步：刷新access_token（如果需要）
	 * @param refresh_token
	 */
	public void getAuthRefreshToken(String refresh_token) {
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APP_ID + "&grant_type=refresh_token&refresh_token=" + refresh_token;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST");
			System.out.println("reponse: " + res);
			AuthRefreshAccessToken token = JSON.parseObject(res, AuthRefreshAccessToken.class);
			if (token.getErrcode() == ResultCode.CODE_SUCCESS) {
				System.out.println("token:" + token.getAccess_token());
				System.out.println("refresh_token:" + token.getRefresh_token());
			} else {
				System.out.println("errmsg: " + token.getErrmsg());
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * @param accessToken
	 */
	public void getAuthUserInfo(String accessToken, String openId) {
		try {
			String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "GET");
			System.out.println("reponse: " + res);
			AuthUserInfo user = JSON.parseObject(res, AuthUserInfo.class);
			if (user.getErrcode() == ResultCode.CODE_SUCCESS) {
				System.out.println("nickname:" + user.getNickname());
				System.out.println("openid:" + user.getOpenid());
			} else {
				System.out.println("errmsg: " + user.getErrmsg());
			}
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 检验授权凭证（access_token）是否有效
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public int isAccessTokenValid(String accessToken, String openId) {
		try {
			String url = "https://api.weixin.qq.com/sns/auth?access_token=" + accessToken + "&openid=" + openId;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "GET");
			System.out.println("reponse: " + res);
			AuthBaseJson json = JSON.parseObject(res, AuthBaseJson.class);
			return json.getErrcode();
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 获取access_token
	 */
	public String addKf() {
		try {
			String param = "{\"kf_account\":\"test1@zhiyun_test\",\"nickname\":\"客服1\",\"password\":\"e5b7a718072857ea181a481b0710cebd\"}";
			String url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST", "application/json", param);
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 客服接口-发消息
	 */
	public String sendCustomMsg() {
		try {
			String param = "{\"touser\":\"obqkrs7otWBVwMdAGYLGjmP42j_c\",\"msgtype\":\"text\",\"text\":{\"content\":\"测试车旺智运\"}}";
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST", "application/json", param);
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 通过openid群发消息
	 */
	public String sendMassMsg() {
		try {
			String param = "{\"touser\":[\"obqkrszNuaoL6GCKUpHtqbF3ccA4\",\"obqkrs7otWBVwMdAGYLGjmP42j_c\"],\"msgtype\":\"text\",\"text\":{\"content\":\"欢迎使用车旺智运\"}}";
			String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=" + ACCESS_TOKEN;
			HttpsUtil des = new HttpsUtil(5000, 5000);
			String res = des.accessHttps(url, "POST", "application/json", param);
			System.out.println("reponse: " + res);
		} catch (ConnectTimeOutException cte) {
			cte.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}