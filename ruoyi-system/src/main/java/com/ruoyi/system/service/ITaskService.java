package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.domain.vo.TaskImportVo;

/**
 * 任务Service接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface ITaskService 
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
     * 链表查询任务列表
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
    public int batchUdateTask (Task task);



    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Integer[] ids);

    /**
     * 删除任务信息
     * 
     * @param id 任务主键
     * @return 结果
     */
    public int deleteTaskById(Integer id);

    /**
     * 导入任务数据
     *
     * @param taskList 数据列表
     * @return 结果
     */
    public String importUser(List<TaskImportVo> taskList);

}
