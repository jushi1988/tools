package com.loongmint.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class HttpsUtil {

	/** 业务名称 **/
	private static final String SERVICE_NAME = "WECHAT";

	/** 请求传输方式：GET **/
	private static final String ACCESS_METHOD_GET = "GET";

	/** 请求传输方式：POST **/
	private static final String ACCESS_METHOD_POST = "POST";

	/** 字符集编码 **/
	private static final String CHARSET = "UTF-8";
	
	/** 内容类型 **/
	private static final String CONTENT_TYPE = "text/html";

	/** 请求访问超时时间，单位：毫秒，默认2分钟 **/
	private int CONNECT_TIMEOUT = 120000;

	/** 读取数据超时时间，单位：毫秒，默认2分钟 **/
	private int READ_TIMEOUT = 120000;

	/** 返回信息：访问接口超时 **/
	private static final String MSG_CONNECTION_TIMEOUT = "access interface time out";

	protected static final Logger log = Logger.getLogger("com.ctfo.dexs.service.DataExchangeService");

	/**
	 * 构造
	 * 
	 * @param connectTimeout
	 *            请求访问超时时间
	 * @param readTimeout
	 *            读取数据超时时间
	 */
	public HttpsUtil(int connectTimeout, int readTimeout) throws ConnectTimeOutException, IllegalArgumentException, Exception {
		this.CONNECT_TIMEOUT = (connectTimeout > 0) ? connectTimeout : 120000;
		this.READ_TIMEOUT = (readTimeout > 0) ? readTimeout : 120000;
	}

	/**
	 * 发起HTTPS请求（客户端导入证书后）
	 * 
	 * @param url
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public String accessHttps(String url, String method) throws ConnectTimeOutException, IllegalArgumentException, Exception {
		if (null == url || url.length() == 0 || null == method || method.length() == 0) {
			throw new IllegalArgumentException("url or method is required");
		}
		if (method.length() > 0) {
			if (!ACCESS_METHOD_GET.equals(method.toUpperCase()) && !ACCESS_METHOD_POST.equals(method.toUpperCase())) {
				throw new IllegalArgumentException("method is only be POST or GET");
			}
		}
		HttpsURLConnection conn = null;
		String res = null;
		try {
			// 获取HttpsURLConnection对象
			conn = this.wrapHttpsURLConnection(url, method, null);
			if (null == conn) {
				log.log(Level.SEVERE, SERVICE_NAME + " HttpsURLConnection is null");
				return null;
			}
			// 建立连接
			conn.connect();
			// 传递参数
			String param = "";
			if (url.indexOf("?") >= 0) {
				param = url.substring(url.indexOf("?"), url.length());
			}
			// 发送请求参数
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 输出返回数据
			res = getResponseAsString(conn);
		} catch (SocketTimeoutException ste) {
			throw new ConnectTimeOutException(MSG_CONNECTION_TIMEOUT);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, SERVICE_NAME + " close HttpsURLConnection error", e);
				throw new Exception(SERVICE_NAME + " close https connection error");
			}
		}

		return res;
	}
	
	/**
	 * 发起HTTPS请求（客户端导入证书后）
	 * 
	 * @param url
	 * @param method
	 * @param contentType
	 * @return
	 * @throws Exception
	 */
	public String accessHttps(String url, String method, String contentType, String param) throws ConnectTimeOutException, IllegalArgumentException, Exception {
		if (null == url || url.length() == 0 || null == method || method.length() == 0) {
			throw new IllegalArgumentException("url or method is required");
		}
		if (method.length() > 0) {
			if (!ACCESS_METHOD_GET.equals(method.toUpperCase()) && !ACCESS_METHOD_POST.equals(method.toUpperCase())) {
				throw new IllegalArgumentException("method is only be POST or GET");
			}
		}
		HttpsURLConnection conn = null;
		String res = null;
		try {
			// 获取HttpsURLConnection对象
			conn = this.wrapHttpsURLConnection(url, method, contentType);
			if (null == conn) {
				log.log(Level.SEVERE, SERVICE_NAME + " HttpsURLConnection is null");
				return null;
			}
			// 建立连接
			conn.connect();
			// 传递参数
			if (param != null && param.length() > 0) {
				OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				out.append(param);
				// flush输出流的缓冲
				out.flush();
				out.close();
			}
			// 输出返回数据
			res = getResponseAsString(conn);
		} catch (SocketTimeoutException ste) {
			throw new ConnectTimeOutException(MSG_CONNECTION_TIMEOUT);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, SERVICE_NAME + " close HttpsURLConnection error", e);
				throw new Exception(SERVICE_NAME + " close https connection error");
			}
		}

		return res;
	}

	/**
	 * 封装HttpURLConnection对象
	 * @param url
	 * @param method
	 * @return
	 * @throws Exception
	 */
	private HttpsURLConnection wrapHttpsURLConnection(String url, String method, String contentType) throws Exception {
		try {
			// 获取HttpsURLConnection对象
			URL u = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
			if (null == conn) {
				log.log(Level.SEVERE, SERVICE_NAME + " HttpsURLConnection is null");
				throw new Exception("can not access:" + url + ", please check it");
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method.toUpperCase());
			if (contentType != null) {
				conn.setRequestProperty("Accept", contentType); // 设置接收数据的格式  
				conn.setRequestProperty("Content-Type", contentType);
			} else {
				conn.setRequestProperty("Content-Type", CONTENT_TYPE);
			}
			conn.setRequestProperty("connection", "Keep-Alive"); 
			// 设置连接超时时间
			conn.setConnectTimeout(this.CONNECT_TIMEOUT);
			// 设置读取数据超时时间
			conn.setReadTimeout(this.READ_TIMEOUT);
			return conn;
		} catch (Exception e) {
			throw new Exception("can not access:" + url + ", please check it");
		}
	}

	/**
	 * 读取响应数据流
	 * 
	 * @param conn
	 * @return
	 */
	private static String getResponseAsString(HttpsURLConnection conn) throws ConnectTimeOutException, IllegalArgumentException, Exception {
		return getStreamAsString(conn.getInputStream(), CHARSET);
	}

	/**
	 * 读取数据流
	 * 
	 * @param stream
	 * @param charset
	 * @return
	 */
	private static String getStreamAsString(InputStream stream, String charset) throws Exception {
		// 输出返回数据
		StringWriter writer = new StringWriter();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, CHARSET));
			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}
			return writer.toString();
		} catch (IOException ie) {
			log.log(Level.SEVERE, SERVICE_NAME + " getStreamAsString error", ie);
			throw new Exception("parse response error");
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, SERVICE_NAME + " getStreamAsString close InputStream error", e);
				throw e;
			}
		}
	}
}