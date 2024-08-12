package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TaskType;

/**
 * 任务类型Service接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface ITaskTypeService 
{
    /**
     * 查询任务类型
     * 
     * @param id 任务类型主键
     * @return 任务类型
     */
    public TaskType selectTaskTypeById(Long id);

    /**
     * 查询任务类型列表
     * 
     * @param taskType 任务类型
     * @return 任务类型集合
     */
    public List<TaskType> selectTaskTypeList(TaskType taskType);

    /**
     * 新增任务类型
     * 
     * @param taskType 任务类型
     * @return 结果
     */
    public int insertTaskType(TaskType taskType);

    /**
     * 修改任务类型
     * 
     * @param taskType 任务类型
     * @return 结果
     */
    public int updateTaskType(TaskType taskType);

    /**
     * 批量删除任务类型
     * 
     * @param ids 需要删除的任务类型主键集合
     * @return 结果
     */
    public int deleteTaskTypeByIds(Long[] ids);

    /**
     * 删除任务类型信息
     * 
     * @param id 任务类型主键
     * @return 结果
     */
    public int deleteTaskTypeById(Long id);
}
