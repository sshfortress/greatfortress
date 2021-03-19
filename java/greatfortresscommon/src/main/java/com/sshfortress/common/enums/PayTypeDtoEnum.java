package com.sshfortress.common.enums;


/**
 * <p class="detail">
 * 功能：支付方式枚举类型
 * ALI_PAY(10,"支付宝"),WEIXIN_PAY(20,"微信");
 * BANKCARD_PAY(30,"银联"),CREDIT_PAY(40,"积分");
 * @ClassName: AppClientKindEnum
 * @version V1.0
 */
public enum PayTypeDtoEnum {
	
	/**
	 * 10:支付宝
	 */
	ALI_PAY(10,"支付宝"),
	/**
	 * 20:微信
	 */
	WEIXIN_PAY(20,"微信"),
	
	
	BANKCARD_PAY(30,"银联"),
	
	CREDIT_PAY(40,"积分");
	
	private Integer value;
	private String name;

	private PayTypeDtoEnum(Integer value) {
		this.value = value;
	}
	
	private PayTypeDtoEnum(Integer value,String name) {
		this.value = value;
		this.name = name;
	}
	/**
	 * <p class="detail">
	 * 功能：返回枚举值
	 * </p>
	 * 
	 * @return 枚举每个变量的实际值
	 */
	public Integer getValue() {
		return this.value;
	}
	
	/**
	 * <p class="detail">
	 * 功能：获得枚举值的名称
	 * </p>
	 * @return 枚举值的名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * <p class="detail">
	 * 功能：覆写toString方法
	 * </p>
	 * 
	 * @return
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
	/**
	 * <p class="detail">
	 * 功能：判断一个值是否与当前枚举值相同
	 * </p>
	 * @param value
	 * @return true：相等，false：不相等
	 */
	public boolean isEquals(Integer value){
		return (this.value==null || value==null)?false:(this.value.intValue()==value.intValue());
	}
	/**
	 * <p class="detail">
	 * 功能：根据枚举值获得枚举类型
	 * </p>
	 * @param value 枚举值
	 * @return 枚举值对应的枚举类型
	 */
	public static PayTypeDtoEnum getEnumByValue(int value){
		PayTypeDtoEnum[] enums = PayTypeDtoEnum.values();
		for(int i=0; enums!=null && enums.length>0; i++){
			if(enums[i].getValue().intValue()==value){
				return enums[i];
			}
		}
		return null;
	}
	/**
	 * <p class="detail">
	 * 功能：获得枚举json字符串
	 * </p>
	 * @return
	 */
	public String  getEnumJson(){
		StringBuilder json = new StringBuilder("[");
		PayTypeDtoEnum[] enums = PayTypeDtoEnum.values();
		for(int i=0; enums!=null && enums.length>0; i++){
			if(i>0){
				json.append(",");
			}
			json.append("{\"value\":").append(enums[i].getValue()).append(",\"name\":\"");
			json.append(enums[i].getEnumJson()).append("\"}");
		}
		json.append("]");
		return json.toString();
	}
}
