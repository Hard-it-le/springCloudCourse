package com.course.server.service.impl;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.SectionDto;
import com.course.server.mapper.SectionMapper;
import com.course.server.service.SectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class  SectionServiceImpl  implements SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
     *小结查询列表
     * @param pageDto
     */
    @Override
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        List<Section> sectionsList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = new ArrayList<SectionDto>();
        for (int i = 0; i < sectionsList.size(); i++) {
            Section section = sectionsList.get(i);
            SectionDto sectionDto = new SectionDto();
            BeanUtils.copyProperties(section, sectionDto);
            sectionDtoList.add(sectionDto);
        }
        pageDto.setList(sectionDtoList);

    }
}
