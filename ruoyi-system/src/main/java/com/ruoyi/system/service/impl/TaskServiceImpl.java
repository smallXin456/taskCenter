package com.ruoyi.system.service.impl;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.domain.Area;
import com.ruoyi.system.domain.TaskSource;
import com.ruoyi.system.domain.TaskType;
import com.ruoyi.system.domain.vo.TaskImportVo;
import com.ruoyi.system.mapper.*;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.service.ITaskService;


/**
 * 任务Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-06
 */
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskSourceMapper taskSourceMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 查询任务
     *
     * @param id 任务主键
     * @return 任务
     */
    @Override
    public Task selectTaskById(Integer id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询任务列表
     *
     * @param task 任务
     * @return 任务
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 链表任务列表
     *
     * @param task 任务
     * @return 任务
     */
    @Override
    public List<Task> selectTaskListByExport(Task task)
    {
        return taskMapper.selectTaskListByExport(task);
    }


    /**
     * 新增任务
     *
     * @param task 任务
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        task.setCreateTime(DateUtils.getNowDate());
        task.setCreateBy(loginUser.getUser().getNickName());

        //ip为空，解析url
        if (StringUtils.isEmpty(task.getIp())){
            try {
                task.setIp(getIp(task.getUrl()));
            } catch (Exception e) {
                // empty ip
            }
        }

        if (StringUtils.isNotEmpty(task.getArea())) {
            switch (task.getArea().length) {
                case 2:
                    task.setProvince(task.getArea()[0]);
                    task.setCity(task.getArea()[1]);
                    break;
                case 3:
                    task.setProvince(task.getArea()[0]);
                    task.setCity(task.getArea()[1]);
                    task.setCounty(task.getArea()[2]);
            }

        }

        return taskMapper.insertTask(task);
    }

    /**
     * 修改任务
     *
     * @param task 任务
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        task.setUpdateTime(DateUtils.getNowDate());
        task.setUpdateBy(loginUser.getUser().getNickName());
        return taskMapper.updateTask(task);
    }

    /**
     * 修改任务
     *
     * @param task 任务
     * @return 结果
     */
    @Override
    public int batchUdateTask(Task task)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        task.setUpdateTime(DateUtils.getNowDate());
        task.setUpdateBy(loginUser.getUser().getNickName());
        return taskMapper.batchUdateTask(task);
    }


    /**
     * 批量删除任务
     *
     * @param ids 需要删除的任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Integer[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除任务信息
     *
     * @param id 任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Integer id)
    {
        return taskMapper.deleteTaskById(id);
    }


    public String getIp(String url) {
        try {
            InetAddress address = InetAddress.getByName(url);
            String ip = address.getHostAddress();
            return ip;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 导入任务数据
     *
     * @param taskList 数据列表
     * @return 结果
     */
    @Override
    public String importUser(List<TaskImportVo> taskList)
    {
        if (StringUtils.isNull(taskList) || taskList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        QueryWrapper queryWrapper = new QueryWrapper<>();
        Task taskValue = new Task();
        Area area = null;
        List priorityList = new ArrayList<>();
        TaskSource taskSource = null;
        String dictValue = null;
        TaskType taskType = null;

        for (TaskImportVo task : taskList)
        {
            try
            {
                //判断提交单位
                if (StringUtils.isNotEmpty(task.getSource())){
                    queryWrapper.eq("name",task.getSource());
                    taskSource = taskSourceMapper.selectOne(queryWrapper);
                    queryWrapper.clear();
                    if (taskSource == null){
//                        throw new ServiceException("请填写正确的提交单位！"+task.getSource()+",提交单位不正确!");
                        failureMsg.append("请填写正确的提交单位！"+task.getSource()+",提交单位不正确!");
                    }
                    taskValue.setSource(taskSource.getId());
                }

                //判断加急状态
                if (StringUtils.isNotEmpty(task.getPriority())) {
                    queryWrapper.eq("dict_type","sys_priority");
                    List<SysDictData> list = sysDictDataMapper.selectList(queryWrapper);
                    queryWrapper.clear();
                    if (CollectionUtils.isNotEmpty(list)){

                        for (int i = 0; i < list.size(); i++) {
                            priorityList.add(list.get(i).getDictLabel());

                            if (list.get(i).getDictLabel().equals(task.getPriority())){
                                dictValue = list.get(i).getDictValue();
                            }

                        }

                        if (!priorityList.contains(task.getPriority())) {
                            throw new ServiceException("请填写正确的加急状态！"+task.getPriority()+",加急状态不正确!");
                        }
                        taskValue.setPriority(Long.valueOf(dictValue));
                    }
                }

                //判断案件类型
                if (StringUtils.isNotEmpty(task.getType())){
                    queryWrapper.eq("name",task.getType());
                    taskType = taskTypeMapper.selectOne(queryWrapper);
                    queryWrapper.clear();
                    if (taskType == null){
                        throw new ServiceException("请填写正确的案件类型！"+task.getType()+",案件类型不正确!");
//                        failureMsg.append("请填写正确的案件类型！"+task.getType()+",案件类型不正确!");
                    }
                    taskValue.setType(taskType.getId());
                }

                //判断省
                if (StringUtils.isNotEmpty(task.getProvince())){
                    queryWrapper.likeRight("name",task.getProvince());
                    area = areaMapper.selectOne(queryWrapper);
                    queryWrapper.clear();
                    if (null == area){
                        throw new ServiceException("请填写正确的省名称！"+task.getProvince()+",省名称不正确!");
//                        failureMsg.append("请填写正确的省名称！"+task.getProvince()+",省名称不正确!");
                    }
                }
                //判断市
                if (StringUtils.isNotEmpty(task.getCity())){
                    queryWrapper.likeRight("name",task.getCity());
                    List list = areaMapper.selectList(queryWrapper);
                    queryWrapper.clear();
                    if (Collections.isEmpty(list)){
                        throw new ServiceException("请填写正确的市名称！"+task.getCity()+",市名称不正确!");
//                        failureMsg.append("请填写正确的市名称！"+task.getCity()+",市名称不正确!");
                    }
                }

                //判断区
                if (StringUtils.isNotEmpty(task.getCounty())){
                    queryWrapper.likeRight("name",task.getCounty());
                    List list = areaMapper.selectList(queryWrapper);
                    queryWrapper.clear();
                    if (Collections.isEmpty(list)){
                        throw new ServiceException("请填写正确的区名称！"+task.getCounty()+",区名称不正确!");
//                        failureMsg.append("请填写正确的区名称！"+task.getCounty()+",区名称不正确!");
                    }
                }

                successNum++;
                successMsg.append("<br/>" + successNum +  " 导入成功");


            }
            catch (Exception e) {
                failureNum++;
            }

            //插入

            taskValue.setProvince(task.getProvince());
            taskValue.setCity(task.getCity());
            taskValue.setCounty(task.getCounty());
            taskValue.setClientCode(task.getClientCode());
            taskValue.setUrl(task.getUrl());
            taskValue.setIp(task.getIp());
            taskValue.setPenetrationFollowPerson(task.getPenetrationFollowPerson());
            taskValue.setAnalysisFollowPerson(task.getAnalysisFollowPerson());

            // 获取当前的用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            taskValue.setCreateTime(DateUtils.getNowDate());
            taskValue.setCreateBy(loginUser.getUser().getNickName());

            taskMapper.insertTask(taskValue);
        }



        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

}
