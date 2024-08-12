package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.TaskRelation;
import com.ruoyi.system.service.ITaskRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务关系Controller
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/system/relation")
public class TaskRelationController extends BaseController
{
    @Autowired
    private ITaskRelationService taskRelationService;

    /**
     * 查询任务关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskRelation taskRelation)
    {
        startPage();
        List<TaskRelation> list = taskRelationService.selectTaskRelationList(taskRelation);
        return getDataTable(list);
    }

    /**
     * 导出任务关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "任务关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskRelation taskRelation)
    {
        List<TaskRelation> list = taskRelationService.selectTaskRelationList(taskRelation);
        ExcelUtil<TaskRelation> util = new ExcelUtil<TaskRelation>(TaskRelation.class);
        util.exportExcel(response, list, "任务关系数据");
    }

    /**
     * 获取任务关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(taskRelationService.selectTaskRelationById(id));
    }

    /**
     * 新增任务关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "任务关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskRelation taskRelation)
    {
        return toAjax(taskRelationService.insertTaskRelation(taskRelation));
    }

    /**
     * 修改任务关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "任务关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskRelation taskRelation)
    {
        return toAjax(taskRelationService.updateTaskRelation(taskRelation));
    }

    /**
     * 删除任务关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "任务关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(taskRelationService.deleteTaskRelationByIds(ids));
    }
}
