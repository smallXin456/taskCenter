package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TaskSource;

/**
 * 提交单位Service接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface ITaskSourceService 
{
    /**
     * 查询提交单位
     * 
     * @param id 提交单位主键
     * @return 提交单位
     */
    public TaskSource selectTaskSourceById(Long id);

    /**
     * 查询提交单位列表
     * 
     * @param taskSource 提交单位
     * @return 提交单位集合
     */
    public List<TaskSource> selectTaskSourceList(TaskSource taskSource);

    public List<TaskSource> selectTaskSourceAllList(TaskSource taskSource);

    /**
     * 新增提交单位
     * 
     * @param taskSource 提交单位
     * @return 结果
     */
    public int insertTaskSource(TaskSource taskSource);

    /**
     * 修改提交单位
     * 
     * @param taskSource 提交单位
     * @return 结果
     */
    public int updateTaskSource(TaskSource taskSource);

    /**
     * 批量删除提交单位
     * 
     * @param ids 需要删除的提交单位主键集合
     * @return 结果
     */
    public int deleteTaskSourceByIds(Long[] ids);

    /**
     * 删除提交单位信息
     * 
     * @param id 提交单位主键
     * @return 结果
     */
    public int deleteTaskSourceById(Long id);
}
