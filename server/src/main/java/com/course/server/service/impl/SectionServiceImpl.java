package com.course.server.service.impl;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
import com.course.server.service.SectionService;
import com.course.server.utils.CopyUtil;
import com.course.server.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
     * 查询列表
     *
     * @param pageDto
     */
    @Override
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = new ArrayList<SectionDto>();
        for (int i = 0; i < sectionList.size(); i++) {
            Section section = sectionList.get(i);
            SectionDto sectionDto = new SectionDto();
            BeanUtils.copyProperties(section, sectionDto);
            sectionDtoList.add(sectionDto);
        }
        pageDto.setList(sectionDtoList);
    }

    /**
     * 添加章节目录
     *
     * @param sectionDto
     */
    @Override
    public void save(SectionDto sectionDto) {
        Date now = new Date();
        sectionDto.setCreatedAt(now);
        sectionDto.setUpdatedAt(now);
        sectionDto.setId(UuidUtil.getShortUuid());
        Section section = new Section();
        BeanUtils.copyProperties(sectionDto, section);
        sectionMapper.insert(section);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改章节
     *
     * @param sectionDto
     */
    @Override
    public void edit(SectionDto sectionDto) {
        sectionDto.setUpdatedAt(new Date());
          /*原始方法封装Dto
        Section section = new Section();
        BeanUtils.copyProperties(sectionDto, section);*/
        Section section= CopyUtil.copy(sectionDto,Section.class);
        sectionMapper.updateByPrimaryKey(section);
    }
}
