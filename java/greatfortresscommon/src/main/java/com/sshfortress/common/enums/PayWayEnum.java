package com.sshfortress.common.enums;

public enum PayWayEnum {

	ALIPAY("10", "支付宝"),
	WEIXINPAY("20", "微信");
	
	private String code;

	private String detail;

	PayWayEnum(String code, String detail) {
	        this.code = code;
	        this.detail = detail;
	    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
