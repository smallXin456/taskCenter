package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AreaMapper;
import com.ruoyi.system.domain.Area;
import com.ruoyi.system.service.IAreaService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-09
 */
@Service
public class AreaServiceImpl implements IAreaService 
{
    @Autowired
    private AreaMapper areaMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Area selectAreaById(Integer id)
    {
        return areaMapper.selectAreaById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param area 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Area> selectAreaList(Area area)
    {
        return areaMapper.selectAreaList(area);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param area 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertArea(Area area)
    {
        return areaMapper.insertArea(area);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param area 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateArea(Area area)
    {
        return areaMapper.updateArea(area);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAreaByIds(Integer[] ids)
    {
        return areaMapper.deleteAreaByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAreaById(Integer id)
    {
        return areaMapper.deleteAreaById(id);
    }
}
