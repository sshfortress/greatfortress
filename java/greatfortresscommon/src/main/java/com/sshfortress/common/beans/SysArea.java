package com.sshfortress.common.beans;

/**
 * 系统区域表
 *
 */
public class SysArea {
    private Long id;

    private String aname;

    private String code;

    private String firstSpell;

    private String fullSpell;

    private String perSpell;

    private String aflag;

    private Integer nlevel;

    private Long leftId;

    private Long rightId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstSpell() {
        return firstSpell;
    }

    public void setFirstSpell(String firstSpell) {
        this.firstSpell = firstSpell;
    }

    public String getFullSpell() {
        return fullSpell;
    }

    public void setFullSpell(String fullSpell) {
        this.fullSpell = fullSpell;
    }

    public String getPerSpell() {
        return perSpell;
    }

    public void setPerSpell(String perSpell) {
        this.perSpell = perSpell;
    }

    public String getAflag() {
        return aflag;
    }

    public void setAflag(String aflag) {
        this.aflag = aflag;
    }

    public Integer getNlevel() {
        return nlevel;
    }

    public void setNlevel(Integer nlevel) {
        this.nlevel = nlevel;
    }

    public Long getLeftId() {
        return leftId;
    }

    public void setLeftId(Long leftId) {
        this.leftId = leftId;
    }

    public Long getRightId() {
        return rightId;
    }

    public void setRightId(Long rightId) {
        this.rightId = rightId;
    }
}