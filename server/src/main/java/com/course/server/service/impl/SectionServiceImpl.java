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

@Service
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionMapper SectionMapper;

    /**
     * 查询列表
     *
     * @param pageDto
     */
    @Override
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample SectionExample = new SectionExample();
        List<Section> SectionList = SectionMapper.selectByExample(SectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(SectionList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> SectionDtoList = new ArrayList<SectionDto>();
        for (int i = 0; i < SectionList.size(); i++) {
            Section Section = SectionList.get(i);
            SectionDto SectionDto = new SectionDto();
            BeanUtils.copyProperties(Section, SectionDto);
            SectionDtoList.add(SectionDto);
        }
        pageDto.setList(SectionDtoList);
    }

    /**
     * 添加章节目录
     *
     * @param SectionDto
     */
    @Override
    public void save(SectionDto SectionDto) {
        SectionDto.setId(UuidUtil.getShortUuid());
        Section Section = new Section();
        BeanUtils.copyProperties(SectionDto, Section);
        SectionMapper.insert(Section);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        SectionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改章节
     *
     * @param SectionDto
     */
    @Override
    public void edit(SectionDto SectionDto) {
          /*原始方法封装Dto
          Section Section = new Section();
        BeanUtils.copyProperties(SectionDto, Section);*/
        Section Section= CopyUtil.copy(SectionDto,Section.class);
        SectionMapper.updateByPrimaryKey(Section);
    }
}
