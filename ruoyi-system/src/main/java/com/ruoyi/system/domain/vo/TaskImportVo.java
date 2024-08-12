package com.ruoyi.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 任务对象 task
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public class TaskImportVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 提交单位 */
    @Excel(name = "提交单位")
//    private Long source;
    private String source;

    /** 加急状态 */
    @Excel(name = "加急状态")
//    private Long priority;
    private String priority;



    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String county;

    /** 任务类型 */
    @Excel(name = "案件类型")
//    private Long type;
    private String type;

    /** 提交方内部编号 */
    @Excel(name = "提交方内部编号")
    private String clientCode;

    /** URL网址 */
    @Excel(name = "URL网址")
    private String url;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 渗透跟进人 */
    @Excel(name = "打击跟进人")
    private String penetrationFollowPerson;

    /** 研判跟进人 */
    @Excel(name = "研判跟进人")
    private String analysisFollowPerson;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPenetrationFollowPerson() {
        return penetrationFollowPerson;
    }

    public void setPenetrationFollowPerson(String penetrationFollowPerson) {
        this.penetrationFollowPerson = penetrationFollowPerson;
    }

    public String getAnalysisFollowPerson() {
        return analysisFollowPerson;
    }

    public void setAnalysisFollowPerson(String analysisFollowPerson) {
        this.analysisFollowPerson = analysisFollowPerson;
    }
}
