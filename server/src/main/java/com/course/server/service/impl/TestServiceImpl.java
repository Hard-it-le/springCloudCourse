package com.course.server.service.impl;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.dto.TestDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.TestMapper;
import com.course.server.service.TestService;
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
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    /**
     * 查询列表
     *
     * @param pageDto
     */
    @Override
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TestExample testExample = new TestExample();
        List<Test> testList = testMapper.selectByExample(testExample);
        PageInfo<Test> pageInfo = new PageInfo<>(testList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TestDto> testDtoList = new ArrayList<TestDto>();
        for (int i = 0; i < testList.size(); i++) {
            Test test = testList.get(i);
            TestDto testDto = new TestDto();
            BeanUtils.copyProperties(test, testDto);
            testDtoList.add(testDto);
        }
        pageDto.setList(testDtoList);
    }

    /**
     * 添加章节目录
     *
     * @param testDto
     */
    @Override
    public void save(TestDto testDto) {
        testDto.setId(UuidUtil.getShortUuid());
        Test test = new Test();
        BeanUtils.copyProperties(testDto, test);
        testMapper.insert(test);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        testMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改章节
     *
     * @param testDto
     */
    @Override
    public void edit(TestDto testDto) {
          /*原始方法封装Dto
          Test test = new Test();
        BeanUtils.copyProperties(testDto, test);*/
        Test test= CopyUtil.copy(testDto,Test.class);
        testMapper.updateByPrimaryKey(test);
    }
}
