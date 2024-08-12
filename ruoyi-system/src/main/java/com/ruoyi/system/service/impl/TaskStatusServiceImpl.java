package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskStatusMapper;
import com.ruoyi.system.domain.TaskStatus;
import com.ruoyi.system.service.ITaskStatusService;

/**
 * 任务状态Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@Service
public class TaskStatusServiceImpl implements ITaskStatusService 
{
    @Autowired
    private TaskStatusMapper taskStatusMapper;

    /**
     * 查询任务状态
     * 
     * @param id 任务状态主键
     * @return 任务状态
     */
    @Override
    public TaskStatus selectTaskStatusById(Long id)
    {
        return taskStatusMapper.selectTaskStatusById(id);
    }

    /**
     * 查询任务状态列表
     * 
     * @param taskStatus 任务状态
     * @return 任务状态
     */
    @Override
    public List<TaskStatus> selectTaskStatusList(TaskStatus taskStatus)
    {
        return taskStatusMapper.selectTaskStatusList(taskStatus);
    }

    /**
     * 新增任务状态
     * 
     * @param taskStatus 任务状态
     * @return 结果
     */
    @Override
    public int insertTaskStatus(TaskStatus taskStatus)
    {
        return taskStatusMapper.insertTaskStatus(taskStatus);
    }

    /**
     * 修改任务状态
     * 
     * @param taskStatus 任务状态
     * @return 结果
     */
    @Override
    public int updateTaskStatus(TaskStatus taskStatus)
    {
        return taskStatusMapper.updateTaskStatus(taskStatus);
    }

    /**
     * 批量删除任务状态
     * 
     * @param ids 需要删除的任务状态主键
     * @return 结果
     */
    @Override
    public int deleteTaskStatusByIds(Long[] ids)
    {
        return taskStatusMapper.deleteTaskStatusByIds(ids);
    }

    /**
     * 删除任务状态信息
     * 
     * @param id 任务状态主键
     * @return 结果
     */
    @Override
    public int deleteTaskStatusById(Long id)
    {
        return taskStatusMapper.deleteTaskStatusById(id);
    }
}
