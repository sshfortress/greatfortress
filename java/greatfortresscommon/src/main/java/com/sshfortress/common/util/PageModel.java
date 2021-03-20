/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.util;

import java.io.Serializable;

/** <p class="detail">
 * 功能：web端分页的结果数据对象
 * </p>
 * @ClassName: PageModel 
 * @version V1.0  
 */
public class PageModel implements Serializable {

    /** 
     * <p class="detail">
     * 功能：
     * </p>
     * @Fields serialVersionUID 
     */
    private static final long serialVersionUID = 423511444957574274L;

    public PageModel(Pager pager, Object dataList) {
        super();
        this.pager = pager;
        this.dataList = dataList;
    }

    /** 
     * <p class="detail">
     * 功能：前端需要的分页参数
     * </p>
     * @Fields pager 
     */
    private Pager  pager;

    /** 
     * <p class="detail">
     * 功能：实际的列表数据
     * </p>
     * @Fields obj 
     */
    private Object dataList;

    /**
     * @return pager
     */

    public Pager getPager() {
        return pager;
    }

    /**
     * @param pager
     */
    public void setPager(Pager pager) {
        this.pager = pager;
    }

    /**
     * @return dataList
     */

    public Object getDataList() {
        return dataList;
    }

    /**
     * @param dataList
     */
    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }

}
