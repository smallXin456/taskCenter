package com.ruoyi.system.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TaskType;
import com.ruoyi.system.mapper.TaskStatusMapper;
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
import com.ruoyi.system.domain.TaskStatus;
import com.ruoyi.system.service.ITaskStatusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务状态Controller
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/system/status")
public class TaskStatusController extends BaseController
{
    @Autowired
    private ITaskStatusService taskStatusService;

    @Resource
    private TaskStatusMapper taskStatusMapper;


    /**
     * 查询任务状态列表
     */
    @PreAuthorize("@ss.hasPermi('system:status:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskStatus taskStatus)
    {
        startPage();
        List<TaskStatus> list = taskStatusService.selectTaskStatusList(taskStatus);
        return getDataTable(list);
    }


    /**
     * 查询所有任务状态列表，where 状态
     */
//    @PreAuthorize("@ss.hasPermi('system:status:list')")
    @GetMapping("/allListByType")
    public TableDataInfo allListByType(TaskStatus taskStatus)
    {
        if (taskStatus.getStatusType() != null){
            taskStatus.setType(taskStatus.getStatusType());
        }
        LambdaQueryWrapper<TaskStatus> wrapper = new LambdaQueryWrapper();
        wrapper.select(TaskStatus::getId,TaskStatus::getName,TaskStatus::getSuccess)
                .eq(TaskStatus::getType, taskStatus.getType());
        List<TaskStatus> list = taskStatusMapper.selectList(wrapper);
        return getDataTable(list);
    }


    /**
     * 导出任务状态列表
     */
    @PreAuthorize("@ss.hasPermi('system:status:export')")
    @Log(title = "任务状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskStatus taskStatus)
    {
        List<TaskStatus> list = taskStatusService.selectTaskStatusList(taskStatus);
        ExcelUtil<TaskStatus> util = new ExcelUtil<TaskStatus>(TaskStatus.class);
        util.exportExcel(response, list, "任务状态数据");
    }

    /**
     * 获取任务状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:status:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskStatusService.selectTaskStatusById(id));
    }

    /**
     * 新增任务状态
     */
    @PreAuthorize("@ss.hasPermi('system:status:add')")
    @Log(title = "任务状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskStatus taskStatus)
    {
        return toAjax(taskStatusService.insertTaskStatus(taskStatus));
    }

    /**
     * 修改任务状态
     */
    @PreAuthorize("@ss.hasPermi('system:status:edit')")
    @Log(title = "任务状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskStatus taskStatus)
    {
        return toAjax(taskStatusService.updateTaskStatus(taskStatus));
    }

    /**
     * 删除任务状态
     */
    @PreAuthorize("@ss.hasPermi('system:status:remove')")
    @Log(title = "任务状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskStatusService.deleteTaskStatusByIds(ids));
    }
}
