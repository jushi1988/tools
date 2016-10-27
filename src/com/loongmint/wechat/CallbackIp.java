package com.loongmint.wechat;

import java.util.List;

public class CallbackIp extends AuthBaseJson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> ip_list;

	public List<String> getIp_list() {
		return ip_list;
	}

	public void setIp_list(List<String> ip_list) {
		this.ip_list = ip_list;
	}
}