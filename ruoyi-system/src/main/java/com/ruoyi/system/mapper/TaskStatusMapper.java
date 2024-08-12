package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TaskStatus;

/**
 * 任务状态Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface TaskStatusMapper  extends BaseMapper<TaskStatus>
{
    /**
     * 查询任务状态
     * 
     * @param id 任务状态主键
     * @return 任务状态
     */
    public TaskStatus selectTaskStatusById(Long id);

    /**
     * 查询任务状态列表
     * 
     * @param taskStatus 任务状态
     * @return 任务状态集合
     */
    public List<TaskStatus> selectTaskStatusList(TaskStatus taskStatus);

    /**
     * 新增任务状态
     * 
     * @param taskStatus 任务状态
     * @return 结果
     */
    public int insertTaskStatus(TaskStatus taskStatus);

    /**
     * 修改任务状态
     * 
     * @param taskStatus 任务状态
     * @return 结果
     */
    public int updateTaskStatus(TaskStatus taskStatus);

    /**
     * 删除任务状态
     * 
     * @param id 任务状态主键
     * @return 结果
     */
    public int deleteTaskStatusById(Long id);

    /**
     * 批量删除任务状态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskStatusByIds(Long[] ids);
}
