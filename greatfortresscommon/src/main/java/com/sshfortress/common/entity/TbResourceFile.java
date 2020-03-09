package com.sshfortress.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbResourceFile {
    private Long id;

    private String sourceUrl;

    private String sourceSuffix;

    private BigDecimal sourceSize;

    private String sourceOldName;

    private String sourceName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
 
    private Date sourceCreatetime;

    private Long sourceUserid;
    
    private String remark;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceSuffix() {
        return sourceSuffix;
    }

    public void setSourceSuffix(String sourceSuffix) {
        this.sourceSuffix = sourceSuffix;
    }

    public BigDecimal getSourceSize() {
        return sourceSize;
    }

    public void setSourceSize(BigDecimal sourceSize) {
        this.sourceSize = sourceSize;
    }

    public String getSourceOldName() {
        return sourceOldName;
    }

    public void setSourceOldName(String sourceOldName) {
        this.sourceOldName = sourceOldName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Date getSourceCreatetime() {
        return sourceCreatetime;
    }

    public void setSourceCreatetime(Date sourceCreatetime) {
        this.sourceCreatetime = sourceCreatetime;
    }

    public Long getSourceUserid() {
        return sourceUserid;
    }

    public void setSourceUserid(Long sourceUserid) {
        this.sourceUserid = sourceUserid;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}