package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TaskRelation;

/**
 * 任务关系Service接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface ITaskRelationService 
{
    /**
     * 查询任务关系
     * 
     * @param id 任务关系主键
     * @return 任务关系
     */
    public TaskRelation selectTaskRelationById(Integer id);

    /**
     * 查询任务关系列表
     * 
     * @param taskRelation 任务关系
     * @return 任务关系集合
     */
    public List<TaskRelation> selectTaskRelationList(TaskRelation taskRelation);

    /**
     * 新增任务关系
     * 
     * @param taskRelation 任务关系
     * @return 结果
     */
    public int insertTaskRelation(TaskRelation taskRelation);

    /**
     * 修改任务关系
     * 
     * @param taskRelation 任务关系
     * @return 结果
     */
    public int updateTaskRelation(TaskRelation taskRelation);

    /**
     * 批量删除任务关系
     * 
     * @param ids 需要删除的任务关系主键集合
     * @return 结果
     */
    public int deleteTaskRelationByIds(Integer[] ids);

    /**
     * 删除任务关系信息
     * 
     * @param id 任务关系主键
     * @return 结果
     */
    public int deleteTaskRelationById(Integer id);
}
