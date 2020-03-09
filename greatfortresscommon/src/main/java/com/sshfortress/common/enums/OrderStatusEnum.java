package com.sshfortress.common.enums;

import org.apache.commons.lang.StringUtils;

public enum OrderStatusEnum {

	CANCEL("00", "取消"),
	UNPAY("10", "待付款"),
	UNSEND("20", "待发货"), 
	UNRECEIV("30","待收货"),
	REFUND("90","已退款"),
	FINISH("40", "已完成") ;
	private String code;

	private String detail;

	OrderStatusEnum(String code, String detail) {
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

	/**
	 * 获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static OrderStatusEnum getEnumByCode(String code) {

		for (OrderStatusEnum activitie : OrderStatusEnum.values()) {
			if (StringUtils.equals(code, activitie.getCode())) {
				return activitie;
			}
		}
		return null;
	}
}
