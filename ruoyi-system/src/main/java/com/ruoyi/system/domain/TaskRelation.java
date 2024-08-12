package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务关系对象 task_relation
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public class TaskRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Integer id;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Integer taskId;

    /** 对应父任务ID, 为0表示该任务为父任务 */
    @Excel(name = "对应父任务ID, 为0表示该任务为父任务")
    private Integer parentTaskId;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTaskId(Integer taskId) 
    {
        this.taskId = taskId;
    }

    public Integer getTaskId() 
    {
        return taskId;
    }
    public void setParentTaskId(Integer parentTaskId) 
    {
        this.parentTaskId = parentTaskId;
    }

    public Integer getParentTaskId() 
    {
        return parentTaskId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("parentTaskId", getParentTaskId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
