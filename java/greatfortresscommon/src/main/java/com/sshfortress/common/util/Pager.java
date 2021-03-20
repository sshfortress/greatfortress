package com.sshfortress.common.util;


import java.io.Serializable;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: Pager 
 * @version V1.0  
 */
public class Pager implements Serializable {

    private static final long serialVersionUID = -3012104743160557484L;

    /** 当前第几页传参 */
    private Integer           currentPage;

    /** 总页数 */
    private Integer           totalPage;

    // 总记录数
    private Integer           totalRecord;

    /** mysql 每页起始行 */
    private Integer           startRow;

    // 每页条数
    private Integer           pageSize;

    /**
     * @return the currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the totalPage
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage
     *            the totalPage to set
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the totalRecord
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * @param totalRecord
     *            the totalRecord to set
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * @return the startRow
     */
    public Integer getStartRow() {
        return startRow;
    }

    /**
     * @param startRow
     *            the startRow to set
     */
    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
