package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TaskSource;
import com.ruoyi.system.domain.TaskStatus;
import com.ruoyi.system.domain.TaskType;
import com.ruoyi.system.domain.vo.TaskImportVo;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.mapper.TaskSourceMapper;
import com.ruoyi.system.mapper.TaskStatusMapper;
import com.ruoyi.system.mapper.TaskTypeMapper;
import com.ruoyi.system.service.impl.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.service.ITaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 任务Controller
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/system/task")
public class TaskController extends BaseController
{
    @Autowired
    private ITaskService taskService;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Resource
    private TaskMapper taskMapper;

    @Autowired
    private TaskSourceMapper taskSourceMapper;

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    @Autowired
    private TaskTypeMapper taskTypeMapper;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(Task task)
    {
        startPage();
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

        Task task1 = taskMapper.selectTaskById(task.getId());
        if (null != task1) {
            //是主任务，一起查询子任务
            if (task1.getParentTaskRadio() == 0){
                task.setParentTaskId(task.getId());
            }
        }

        List<Task>  list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    @GetMapping("/masterTaskList")
    public TableDataInfo masterTaskList(Task task)
    {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Task::getId).eq(Task::getParentTaskRadio,task.getParentTaskRadio());
        List<Task> list = taskMapper.selectList(wrapper);
        return getDataTable(list);
    }



    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Task task)
    {

//        List<TaskSource> taskSources = taskSourceMapper.selectList(null);
//        List<TaskStatus> taskStatuses = taskStatusMapper.selectList(null);
//        List<TaskType> taskTypes = taskTypeMapper.selectList(null);



        List<Task> list = taskService.selectTaskListByExport(task);
//        list.stream().forEach(taskList->{
//            if (taskList)
//        });
        System.err.println("list:"+list);


        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        util.exportExcel(response, list, "任务数据");
    }

    /**
     * 获取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(taskService.selectTaskById(id));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Task task)
    {
        return toAjax(taskService.insertTask(task));
    }


    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Task task)
    {

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

        return toAjax(taskService.updateTask(task));
    }

    /**
     * 修改任务
     */
    @Log(title = "取消关联任务", businessType = BusinessType.UPDATE)
    @PutMapping("/editContactTask")
    public AjaxResult editContactTask(@RequestBody Task task)
    {
        task.setParentTaskId(-1);
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.in("id",task.getSubTaskIds());

        return toAjax(taskMapper.update(task,wrapper));
    }





    @Log(title = "关联主任务", businessType = BusinessType.UPDATE)
    @PutMapping("/bindMasterTask")
    public AjaxResult bindMasterTask(@RequestBody Task task)
    {

        if (Arrays.binarySearch(task.getSubTaskIds(), task.getParentTaskId()) == 0 ){
            throw new ServiceException("关联任务中存在与主任务编号一致的任务："+task.getParentTaskId()+"，请重新选择关联任务");
        }

        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("parent_task_id","id")
                .in("id", task.getSubTaskIds());

        logger.info("【任务关联成功，被关联的主任务id为{}】",task.getParentTaskId());
        return toAjax(taskMapper.update(task,queryWrapper));
    }



    /**
     * 重新获取ip
     */
    @Log(title = "重新获取ip", businessType = BusinessType.UPDATE)
    @PutMapping("/againGetIp")
    public AjaxResult againGetIp(@RequestBody Task task)
    {

        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        //勾选的任务，ip重新获取
        if (task.getAgainType().equals("signle")){
            queryWrapper.in("id",task.getSubTaskIds());

        }
        //否则所有任务ip重新获取
        int result = 1;
        List<Task> tasks = taskMapper.selectList(queryWrapper);
        tasks.stream().forEach(value -> {
            value.setIp(taskServiceImpl.getIp(value.getUrl()));
            int i = taskMapper.updateById(value);
            if (i<1){
                throw new ServiceException("ip地址获取失败，"+value.getUrl()+"网站有误");
            }
        });

        return toAjax(result);
    }





    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(taskService.deleteTaskByIds(ids));
    }


    @Log(title = "任务模板下载", businessType = BusinessType.EXPORT)
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<TaskImportVo> util = new ExcelUtil<TaskImportVo>(TaskImportVo.class);
        util.importTemplateExcel(response, "任务数据");
    }



    @Log(title = "任务导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<TaskImportVo> util = new ExcelUtil<TaskImportVo>(TaskImportVo.class);
        List<TaskImportVo> userList = util.importExcel(file.getInputStream());

        String message = taskService.importUser(userList);
        return success(message);
    }
}
