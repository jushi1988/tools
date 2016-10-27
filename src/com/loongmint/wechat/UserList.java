package com.loongmint.wechat;

import java.util.List;

public class UserList extends AuthBaseJson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<UserInfo> user_info_list;
	
	public List<UserInfo> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<UserInfo> user_info_list) {
		this.user_info_list = user_info_list;
	}
}