package com.sshfortress.common.beans;

import java.io.Serializable;

/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */

/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: SimpleAreaDo 
 * @version V1.0  
 * 树形结构的查询
 */



public class SimpleAreaDo implements Serializable {

    private static final long serialVersionUID = 2938178606408339644L;

    private Long              id;

    private Long              leftId;

    private Long              rightId;

    private String            aname;

    private Integer           nlevel;

    /**
     * @return id
     */

    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return leftId
     */

    public Long getLeftId() {
        return leftId;
    }

    /**
     * @param leftId
     */
    public void setLeftId(Long leftId) {
        this.leftId = leftId;
    }

    /**
     * @return rightId
     */

    public Long getRightId() {
        return rightId;
    }

    /**
     * @param rightId
     */
    public void setRightId(Long rightId) {
        this.rightId = rightId;
    }

    /**
     * @return aname
     */

    public String getAname() {
        return aname;
    }

    /**
     * @param aname
     */
    public void setAname(String aname) {
        this.aname = aname;
    }

    /**
     * @return nlevel
     */

    public Integer getNlevel() {
        return nlevel;
    }

    /**
     * @param nlevel
     */
    public void setNlevel(Integer nlevel) {
        this.nlevel = nlevel;
    }

}
