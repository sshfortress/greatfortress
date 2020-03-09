package com.sshfortress.common.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p class="detail">
 * 功能：数字运算类，使用BigDecimal对常用数字计算进行封装
 * </p>
 * @ClassName: DigitUtil 
 * @version V1.0  
 */
public class DigitUtil {

	/**
	 * 第一个计算数
	 */
	BigDecimal digitFirst =null;
	/**
	 * 第二个计算数
	 */
	BigDecimal digitSec =null;
	/**
	 * 小数位数
	 */
	int scale=2;
	/**
	 * 舍入规则
	 */
	int roundingMode=BigDecimal.ROUND_HALF_UP;
	
	public DigitUtil(){
		
	}
	public DigitUtil(String digitFirst,String digitSec){
		this.digitFirst = new BigDecimal(digitFirst);
		this.digitFirst.setScale(this.scale, this.roundingMode);
		this.digitSec=new BigDecimal(digitSec);
		this.digitSec.setScale(this.scale, this.roundingMode);
	}
	public DigitUtil(Double digitFirst,Double digitSec){
		this.digitFirst = new BigDecimal(digitFirst);
		this.digitFirst.setScale(this.scale, this.roundingMode);
		this.digitSec=new BigDecimal(digitSec);
		this.digitSec.setScale(this.scale, this.roundingMode);
	}
	
	public BigDecimal getDigitFirst() {
		return digitFirst;
	}
	public void setDigitFirst(BigDecimal digitFirst) {
		this.digitFirst = digitFirst;
	}
	public BigDecimal getDigitSec() {
		return digitSec;
	}
	public void setDigitSec(BigDecimal digitSec) {
		this.digitSec = digitSec;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getRoundingMode() {
		return roundingMode;
	}
	public void setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
	}
	/**
	 * <p class="detail">
	 * 功能：加法计算
	 * </p>
	 * @return
	 */
	public BigDecimal add(){
		if(digitFirst==null || digitSec==null){
			return null;
		}
		return digitFirst.add(digitSec);
	}
	/**
	 * <p class="detail">
	 * 功能：减法计算
	 * </p>
	 * @return
	 */
	public BigDecimal subtract(){
		if(digitFirst==null || digitSec==null){
			return null;
		}
		return digitFirst.subtract(digitSec);
	}
	/**
	 * <p class="detail">
	 * 功能：乘法计算
	 * </p>
	 * @return
	 */
	public BigDecimal multiply(){
		if(digitFirst==null || digitSec==null){
			return null;
		}
		return digitFirst.multiply(digitSec);
	}
	/**
	 * <p class="detail">
	 * 功能：除法计算
	 * </p>
	 * @return
	 */
	public BigDecimal divide(){
		if(digitFirst==null || digitSec==null){
			return null;
		}
		return digitFirst.divide(digitSec,this.scale,this.roundingMode);
	}
	/**
	 * <p class="detail">
	 * 功能：带精度和舍入规则的除法计算
	 * </p>
	 * @param scale 保留小数位数
	 * @param roundingMode 舍入规则，如：BigDecimal.ROUND_HALF_UP
	 * @return
	 */
	public BigDecimal divide(int scale,int roundingMode){
		if(digitFirst==null || digitSec==null){
			return null;
		}
		return digitFirst.divide(digitSec,scale,roundingMode);
	}
	/**
	 * <p class="detail">
	 * 功能：判断一个double值是否等于0
	 * <br/>直接等于0或者值在(-0.0001,0.0001)之间的都视为等于0
	 * </p>
	 * @param value 待判断double值
	 * @return true：等于0，false：不等于0
	 */
	public static boolean isEquelZeroForDouble(Double value){
		if(value==null){
			return false;
		}else if(value==0 || (value<0.0001 && value>-0.0001)){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args){
		DigitUtil uitl = new DigitUtil("10","3");
		System.out.println(uitl.divide());
	}
	/**
	 * <p class="detail">
	 * 功能：实数格式化(保留两位小数)
	 * </p>
	 * @return
	 */
	public static String numberFormat(Double number){
		if(number==null){
			return null;
		}
		DecimalFormat df = new DecimalFormat("###########0.00");
		return df.format(number);
	}
}
