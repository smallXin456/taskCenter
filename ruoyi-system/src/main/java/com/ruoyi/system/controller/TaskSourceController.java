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
import com.ruoyi.system.domain.TaskSource;
import com.ruoyi.system.service.ITaskSourceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 提交单位Controller
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/system/source")
public class TaskSourceController extends BaseController
{
    @Autowired
    private ITaskSourceService taskSourceService;

    /**
     * 查询提交单位列表
     */
    @PreAuthorize("@ss.hasPermi('system:source:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskSource taskSource)
    {
        startPage();
        List<TaskSource> list = taskSourceService.selectTaskSourceList(taskSource);

        return getDataTable(list);
    }


    /**
     * 查询提交单位列表
     */
//    @PreAuthorize("@ss.hasPermi('system:source:list')")
    @GetMapping("/alllist")
    public TableDataInfo alllist(TaskSource taskSource)
    {
        List<TaskSource> list = taskSourceService.selectTaskSourceList(taskSource);
        return getDataTable(list);
    }




    /**
     * 导出提交单位列表
     */
    @PreAuthorize("@ss.hasPermi('system:source:export')")
    @Log(title = "提交单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskSource taskSource)
    {
        List<TaskSource> list = taskSourceService.selectTaskSourceList(taskSource);
        ExcelUtil<TaskSource> util = new ExcelUtil<TaskSource>(TaskSource.class);
        util.exportExcel(response, list, "提交单位数据");
    }

    /**
     * 获取提交单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:source:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskSourceService.selectTaskSourceById(id));
    }

    /**
     * 新增提交单位
     */
    @PreAuthorize("@ss.hasPermi('system:source:add')")
    @Log(title = "提交单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskSource taskSource)
    {
        return toAjax(taskSourceService.insertTaskSource(taskSource));
    }

    /**
     * 修改提交单位
     */
    @PreAuthorize("@ss.hasPermi('system:source:edit')")
    @Log(title = "提交单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskSource taskSource)
    {
        return toAjax(taskSourceService.updateTaskSource(taskSource));
    }

    /**
     * 删除提交单位
     */
    @PreAuthorize("@ss.hasPermi('system:source:remove')")
    @Log(title = "提交单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskSourceService.deleteTaskSourceByIds(ids));
    }
}
