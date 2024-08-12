package com.ruoyi.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务对象 task
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    @Excel(name = "任务编号")
    private Integer id;

    /** 加急状态 */
    @Excel(name = "加急状态" , readConverterExp = "1=加急,2=优先,3=常规")
    private Long priority;

    /** 提交单位 */
//    @Excel(name = "提交单位")
    private Long source;

    /** 提交单位 */
    @TableField(exist = false)
    @Excel(name = "提交单位")
    private String sourceValue;

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
//    @Excel(name = "任务类型")
    private Long type;

    /** 任务类型 */
    @Excel(name = "任务类型")
    @TableField(exist = false)
    private String typeValue;

    /** 提交方内部编号 */
    @Excel(name = "提交方内部编号")
    private String clientCode;

    /** 任务网址 */
    @Excel(name = "任务网址")
    private String url;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;
//
//    /** 任务创建时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "任务创建时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date startTime;
//
//    /** 任务创建人 */
//    @Excel(name = "任务创建人")
//    private Long startUser;

    /** 渗透完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "渗透完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date penetrationEndTime;

    /** 研判完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "研判完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date analysisEndTime;

    /** 渗透状态 0.失败 1.成功 2.进行中 */
    @Excel(name = "渗透状态" , readConverterExp = "0=失败,1=成功,2=进行中")
    private Long penetrationStatus;

    /** 研判状态  0.失败1.成功2.进行中*/
    @Excel(name = "研判状态", readConverterExp = "0=失败,1=成功,2=进行中")
    private Long analysisStatus;

    /** 渗透跟进人 */
    @Excel(name = "渗透跟进人")
    private String penetrationFollowPerson;

    /** 研判跟进人 */
    @Excel(name = "研判跟进人")
    private String analysisFollowPerson;

    /** 后台地址 */
    @Excel(name = "后台地址")
    private String backendAddress;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 管理员备注 */
    @Excel(name = "管理员备注")
    private String employerRemark;

    /** 渗透备注 */
    @Excel(name = "渗透备注")
    private String penetrationRemark;

    /** 研判备注 */
    @Excel(name = "研判备注")
    private String analysisRemark;

    /** 父级id，0为父级 */
    private Integer parentTaskId;

    //渗透状态的结果
//    @Excel(name = "打击状态")
    private Integer penetrationStatusResult;


    //渗透状态的结果
    @Excel(name = "打击状态")
    @TableField(exist = false)
    private String penetrationStatusResultValue;


    //前端传的地区
    @TableField(exist = false)
    private String[] area;

    @TableField(exist = false)
    private Integer[] subTaskIds;

    @TableField(exist = false)
    private String againType;


    /** 控制前端按钮是否为按钮-1.不是 0是 */
    private Integer parentTaskRadio;

    public Integer getParentTaskRadio() {
        return parentTaskRadio;
    }

    public void setParentTaskRadio(Integer parentTaskRadio) {
        this.parentTaskRadio = parentTaskRadio;
    }

    public Integer[] getSubTaskIds() {
        return subTaskIds;
    }

    public void setSubTaskIds(Integer[] subTaskIds) {
        this.subTaskIds = subTaskIds;
    }

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String getAgainType() {
        return againType;
    }

    public void setAgainType(String againType) {
        this.againType = againType;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPriority(Long priority) 
    {
        this.priority = priority;
    }

    public Long getPriority() 
    {
        return priority;
    }
    public void setSource(Long source) 
    {
        this.source = source;
    }

    public Long getSource() 
    {
        return source;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCounty(String county) 
    {
        this.county = county;
    }

    public String getCounty() 
    {
        return county;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setClientCode(String clientCode) 
    {
        this.clientCode = clientCode;
    }

    public String getClientCode() 
    {
        return clientCode;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }

    public void setPenetrationEndTime(Date penetrationEndTime) 
    {
        this.penetrationEndTime = penetrationEndTime;
    }

    public Date getPenetrationEndTime() 
    {
        return penetrationEndTime;
    }
    public void setAnalysisEndTime(Date analysisEndTime) 
    {
        this.analysisEndTime = analysisEndTime;
    }

    public Date getAnalysisEndTime() 
    {
        return analysisEndTime;
    }
    public void setPenetrationStatus(Long penetrationStatus) 
    {
        this.penetrationStatus = penetrationStatus;
    }

    public Long getPenetrationStatus() 
    {
        return penetrationStatus;
    }
    public void setAnalysisStatus(Long analysisStatus) 
    {
        this.analysisStatus = analysisStatus;
    }

    public Long getAnalysisStatus() 
    {
        return analysisStatus;
    }
    public void setPenetrationFollowPerson(String penetrationFollowPerson)
    {
        this.penetrationFollowPerson = penetrationFollowPerson;
    }

    public String getPenetrationFollowPerson()
    {
        return penetrationFollowPerson;
    }
    public void setAnalysisFollowPerson(String analysisFollowPerson)
    {
        this.analysisFollowPerson = analysisFollowPerson;
    }

    public String getAnalysisFollowPerson()
    {
        return analysisFollowPerson;
    }
    public void setBackendAddress(String backendAddress) 
    {
        this.backendAddress = backendAddress;
    }

    public String getBackendAddress() 
    {
        return backendAddress;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setEmployerRemark(String employerRemark) 
    {
        this.employerRemark = employerRemark;
    }

    public String getEmployerRemark() 
    {
        return employerRemark;
    }
    public void setPenetrationRemark(String penetrationRemark) 
    {
        this.penetrationRemark = penetrationRemark;
    }

    public String getPenetrationRemark() 
    {
        return penetrationRemark;
    }
    public void setAnalysisRemark(String analysisRemark) 
    {
        this.analysisRemark = analysisRemark;
    }

    public String getAnalysisRemark() 
    {
        return analysisRemark;
    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Integer getPenetrationStatusResult() {
        return penetrationStatusResult;
    }

    public void setPenetrationStatusResult(Integer penetrationStatusResult) {
        this.penetrationStatusResult = penetrationStatusResult;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getPenetrationStatusResultValue() {
        return penetrationStatusResultValue;
    }

    public void setPenetrationStatusResultValue(String penetrationStatusResultValue) {
        this.penetrationStatusResultValue = penetrationStatusResultValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("priority", getPriority())
            .append("source", getSource())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("type", getType())
            .append("clientCode", getClientCode())
            .append("url", getUrl())
            .append("ip", getIp())
            .append("penetrationEndTime", getPenetrationEndTime())
            .append("analysisEndTime", getAnalysisEndTime())
            .append("penetrationStatus", getPenetrationStatus())
            .append("analysisStatus", getAnalysisStatus())
            .append("penetrationFollowPerson", getPenetrationFollowPerson())
            .append("analysisFollowPerson", getAnalysisFollowPerson())
            .append("backendAddress", getBackendAddress())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("employerRemark", getEmployerRemark())
            .append("penetrationRemark", getPenetrationRemark())
            .append("analysisRemark", getAnalysisRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
