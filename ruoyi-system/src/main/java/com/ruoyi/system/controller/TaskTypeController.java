package com.ruoyi.system.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.system.mapper.TaskTypeMapper;
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
import com.ruoyi.system.domain.TaskType;
import com.ruoyi.system.service.ITaskTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务类型Controller
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/system/type")
public class TaskTypeController extends BaseController
{
    @Autowired
    private ITaskTypeService taskTypeService;

    @Resource
    private TaskTypeMapper taskTypeMapper;

    /**
     * 查询任务类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskType taskType)
    {
        startPage();
        List<TaskType> list = taskTypeService.selectTaskTypeList(taskType);
        return getDataTable(list);
    }

    /**
     * 查询任务类型列表
     */
//    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/allList")
    public TableDataInfo allList(TaskType taskType)
    {
        LambdaQueryWrapper<TaskType> wrapper = new LambdaQueryWrapper();
        wrapper.select(TaskType::getId,TaskType::getName);
        List<TaskType> list = taskTypeMapper.selectList(wrapper);
        return getDataTable(list);
    }


    /**
     * 导出任务类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "任务类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskType taskType)
    {
        List<TaskType> list = taskTypeService.selectTaskTypeList(taskType);
        ExcelUtil<TaskType> util = new ExcelUtil<TaskType>(TaskType.class);
        util.exportExcel(response, list, "任务类型数据");
    }

    /**
     * 获取任务类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskTypeService.selectTaskTypeById(id));
    }

    /**
     * 新增任务类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "任务类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskType taskType)
    {
        return toAjax(taskTypeService.insertTaskType(taskType));
    }

    /**
     * 修改任务类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "任务类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskType taskType)
    {
        return toAjax(taskTypeService.updateTaskType(taskType));
    }

    /**
     * 删除任务类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "任务类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskTypeService.deleteTaskTypeByIds(ids));
    }
}
