package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskRelationMapper;
import com.ruoyi.system.domain.TaskRelation;
import com.ruoyi.system.service.ITaskRelationService;

/**
 * 任务关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@Service
public class TaskRelationServiceImpl implements ITaskRelationService 
{
    @Autowired
    private TaskRelationMapper taskRelationMapper;

    /**
     * 查询任务关系
     * 
     * @param id 任务关系主键
     * @return 任务关系
     */
    @Override
    public TaskRelation selectTaskRelationById(Integer id)
    {
        return taskRelationMapper.selectTaskRelationById(id);
    }

    /**
     * 查询任务关系列表
     * 
     * @param taskRelation 任务关系
     * @return 任务关系
     */
    @Override
    public List<TaskRelation> selectTaskRelationList(TaskRelation taskRelation)
    {
        return taskRelationMapper.selectTaskRelationList(taskRelation);
    }

    /**
     * 新增任务关系
     * 
     * @param taskRelation 任务关系
     * @return 结果
     */
    @Override
    public int insertTaskRelation(TaskRelation taskRelation)
    {
        taskRelation.setCreateTime(DateUtils.getNowDate());
        return taskRelationMapper.insertTaskRelation(taskRelation);
    }

    /**
     * 修改任务关系
     * 
     * @param taskRelation 任务关系
     * @return 结果
     */
    @Override
    public int updateTaskRelation(TaskRelation taskRelation)
    {
        taskRelation.setUpdateTime(DateUtils.getNowDate());
        return taskRelationMapper.updateTaskRelation(taskRelation);
    }

    /**
     * 批量删除任务关系
     * 
     * @param ids 需要删除的任务关系主键
     * @return 结果
     */
    @Override
    public int deleteTaskRelationByIds(Integer[] ids)
    {
        return taskRelationMapper.deleteTaskRelationByIds(ids);
    }

    /**
     * 删除任务关系信息
     * 
     * @param id 任务关系主键
     * @return 结果
     */
    @Override
    public int deleteTaskRelationById(Integer id)
    {
        return taskRelationMapper.deleteTaskRelationById(id);
    }
}
