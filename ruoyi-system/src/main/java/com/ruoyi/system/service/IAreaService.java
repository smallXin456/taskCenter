package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Area;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-08-09
 */
public interface IAreaService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Area selectAreaById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param area 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Area> selectAreaList(Area area);

    /**
     * 新增【请填写功能名称】
     * 
     * @param area 【请填写功能名称】
     * @return 结果
     */
    public int insertArea(Area area);

    /**
     * 修改【请填写功能名称】
     * 
     * @param area 【请填写功能名称】
     * @return 结果
     */
    public int updateArea(Area area);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAreaByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAreaById(Integer id);
}
