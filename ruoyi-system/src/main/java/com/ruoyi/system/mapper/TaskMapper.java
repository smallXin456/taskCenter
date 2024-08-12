package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Task;

/**
 * 任务Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface TaskMapper extends BaseMapper<Task>
{
    /**
     * 查询任务
     * 
     * @param id 任务主键
     * @return 任务
     */
    public Task selectTaskById(Integer id);

    /**
     * 查询任务列表
     * 
     * @param task 任务
     * @return 任务集合
     */
    public List<Task> selectTaskList(Task task);


    /**
     * 链表任务列表
     *
     * @param task 任务
     * @return 任务集合
     */
    public List<Task> selectTaskListByExport(Task task);


    /**
     * 新增任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int updateTask(Task task);



    /**
     * 批量修改任务
     *
     * @param task 任务
     * @return 结果
     */
    public int batchUdateTask(Task task);


    /**
     * 删除任务
     * 
     * @param id 任务主键
     * @return 结果
     */
    public int deleteTaskById(Integer id);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Integer[] ids);
}
