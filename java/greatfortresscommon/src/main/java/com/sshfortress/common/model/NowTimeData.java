package com.sshfortress.common.model;
public class NowTimeData implements java.io.Serializable{

	private static final long serialVersionUID = 4667831746501153151L;

	/** 某个域名的每秒请求量 req/s 页面的Req/s 即QPS 保留一位小数 */
	private Double requestSecond;

	/** 拦截比例 http状态码为4xx的除以总请求数 */
	private Double interceptRatio;

	/** 每秒发送流量 单位：B/s 页面的Sent/s 保留一位小数 */
	private Double trafficSentSecond;

	/** 每秒接收流量 单位：B/s 页面的Rcvd/s 保留一位小数 */
	private Double trafficRcvdSecond;


	public Double getRequestSecond(){
		return this.requestSecond;
	}
	public void setRequestSecond(Double requestSecond){
		this.requestSecond=requestSecond;
	}


	public Double getTrafficSentSecond(){
		return this.trafficSentSecond;
	}
	public void setTrafficSentSecond(Double trafficSentSecond){
		this.trafficSentSecond=trafficSentSecond;
	}

	public Double getTrafficRcvdSecond(){
		return this.trafficRcvdSecond;
	}
	public void setTrafficRcvdSecond(Double trafficRcvdSecond){
		this.trafficRcvdSecond=trafficRcvdSecond;
	}

	public Double getInterceptRatio() {
		return interceptRatio;
	}
	public void setInterceptRatio(Double interceptRatio) {
		this.interceptRatio = interceptRatio;
	}


}
