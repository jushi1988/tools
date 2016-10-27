package com.loongmint.wechat;


public class ConnectTimeOutException extends java.lang.RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ConnectTimeOutException() {
		super();
	}
	
	public ConnectTimeOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectTimeOutException(Throwable cause) {
		super(cause);
	}

	public ConnectTimeOutException(String message) {
		super(message);
	}
}
