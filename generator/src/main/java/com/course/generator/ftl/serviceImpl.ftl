package com.course.server.service.impl;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
import com.course.server.service.${Domain}Service;
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
public class ${Domain}ServiceImpl implements ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * 查询列表
     *
     * @param pageDto
     */
    @Override
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}DtoList = new ArrayList<${Domain}Dto>();
        for (int i = 0; i < ${domain}List.size(); i++) {
            ${Domain} ${domain} = ${domain}List.get(i);
            ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            BeanUtils.copyProperties(${domain}, ${domain}Dto);
            ${domain}DtoList.add(${domain}Dto);
        }
        pageDto.setList(${domain}DtoList);
    }

    /**
     * 添加章节目录
     *
     * @param ${domain}Dto
     */
    @Override
    public void save(${Domain}Dto ${domain}Dto) {
        ${domain}Dto.setId(UuidUtil.getShortUuid());
        ${Domain} ${domain} = new ${Domain}();
        BeanUtils.copyProperties(${domain}Dto, ${domain});
        ${domain}Mapper.insert(${domain});
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改章节
     *
     * @param ${domain}Dto
     */
    @Override
    public void edit(${Domain}Dto ${domain}Dto) {
          /*原始方法封装Dto
          ${Domain} ${domain} = new ${Domain}();
        BeanUtils.copyProperties(${domain}Dto, ${domain});*/
        ${Domain} ${domain}= CopyUtil.copy(${domain}Dto,${Domain}.class);
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }
}
