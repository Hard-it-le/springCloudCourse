package com.course.server.service.impl;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.service.ChapterService;
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
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 查询列表
     *
     * @param pageDto
     */
    @Override
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);
    }

    /**
     * 添加章节目录
     *
     * @param chapterDto
     */
    @Override
    public void save(ChapterDto chapterDto) {
        chapterDto.setId(UuidUtil.getShortUuid());
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto, chapter);
        chapterMapper.insert(chapter);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改章节
     *
     * @param chapterDto
     */
    @Override
    public void edit(ChapterDto chapterDto) {
          /*原始方法封装Dto
          Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto, chapter);*/
        Chapter chapter= CopyUtil.copy(chapterDto,Chapter.class);
        chapterMapper.updateByPrimaryKey(chapter);
    }


}
