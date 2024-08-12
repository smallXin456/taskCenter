package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TaskSourceMapper;
import com.ruoyi.system.domain.TaskSource;
import com.ruoyi.system.service.ITaskSourceService;

/**
 * 提交单位Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@Service
public class TaskSourceServiceImpl implements ITaskSourceService 
{
    @Autowired
    private TaskSourceMapper taskSourceMapper;

    /**
     * 查询提交单位
     * 
     * @param id 提交单位主键
     * @return 提交单位
     */
    @Override
    public TaskSource selectTaskSourceById(Long id)
    {
        return taskSourceMapper.selectTaskSourceById(id);
    }

    /**
     * 查询提交单位列表
     * 
     * @param taskSource 提交单位
     * @return 提交单位
     */
    @Override
    public List<TaskSource> selectTaskSourceList(TaskSource taskSource)
    {
        return taskSourceMapper.selectTaskSourceList(taskSource);
    }

    /**
     * 查询所有的提交单位列表
     *
     * @param taskSource 提交单位
     * @return 提交单位
     */
    @Override
    public List<TaskSource> selectTaskSourceAllList(TaskSource taskSource)
    {
        return taskSourceMapper.selectTaskSourceAllList(taskSource);
    }


    /**
     * 新增提交单位
     * 
     * @param taskSource 提交单位
     * @return 结果
     */
    @Override
    public int insertTaskSource(TaskSource taskSource)
    {
        return taskSourceMapper.insertTaskSource(taskSource);
    }

    /**
     * 修改提交单位
     * 
     * @param taskSource 提交单位
     * @return 结果
     */
    @Override
    public int updateTaskSource(TaskSource taskSource)
    {
        return taskSourceMapper.updateTaskSource(taskSource);
    }

    /**
     * 批量删除提交单位
     * 
     * @param ids 需要删除的提交单位主键
     * @return 结果
     */
    @Override
    public int deleteTaskSourceByIds(Long[] ids)
    {
        return taskSourceMapper.deleteTaskSourceByIds(ids);
    }

    /**
     * 删除提交单位信息
     * 
     * @param id 提交单位主键
     * @return 结果
     */
    @Override
    public int deleteTaskSourceById(Long id)
    {
        return taskSourceMapper.deleteTaskSourceById(id);
    }
}
