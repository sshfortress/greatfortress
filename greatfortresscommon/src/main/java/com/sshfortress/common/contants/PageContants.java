/**
 * Alipay.com Inc.
 */
package com.sshfortress.common.contants;

/*
 * 分页常量
 * @version $Id: PageContants.java, v 0.1 2014-5-27 下午10:57:29 kaneiqi Exp $
 */
public interface PageContants {

	/** 每页展示最大行数 */
	int MAX_PAGE_SIZE = 100;

	/** 每页展示最小行数 */
	int MIN_PAGE_SIZE = 1;

	/** 默认第一页 */
	int DEFULT_CURRENT_PAGE = 1;

	/** 默认每页展示行数 */
	int DEFULT_PAGE_SIZE = 15;

	/** 请求首页 */
	String REQUEST_FIRST_PAGE = "REQUEST_FIRST_PAGE";

	/** 请求下一页 */
	String REQUEST_NEXT_PAGE = "REQUEST_NEXT_PAGE";

	/** 请求上一页 */
	String REQUEST_ON_PAGE = "REQUEST_ON_PAGE";

	/** 请求尾页 */
	String REQUEST_TAIR_PAGE = "REQUEST_TAIR_PAGE";

	/** 请求选择页 */
	String REQUEST_CHOICE_PAGE = "REQUEST_CHOICE_PAGE";

	/** 改变每页大小 */
	String REQUEST_CHANGE_PAGE_SIZE = "REQUEST_CHANGE_PAGE_SIZE";
}
