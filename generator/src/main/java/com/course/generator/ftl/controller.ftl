package com.course.${module}.controller.admin;

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {
    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Resource
    private ${Domain}Service ${domain}Service;


    /**
     * 查询${module}列表
     *
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto ${domain}(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 新增${module}
     *
     * @param ${domain}Dto
     * @return
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {

        // 保存校验
        <#list fieldList as field>
            <#if !field.nullAble && field.name != "id" && field.nameHump !="createAt" && field.nameHump !="updateAt">
        ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
            </#if>
        </#list>

        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
     * 删除${module}
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable("id") String id) {
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.delete(id);
        responseDto.setContent(id);
        return responseDto;
    }



    /**
     * 根据id修改${module}
     *
     * @param ${domain}Dto
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResponseDto edit(@RequestBody ${Domain}Dto ${domain}Dto) {
        // 保存校验
        <#list fieldList as field>
            <#if !field.nullAble && field.name != "id" && field.nameHump !="createAt" && field.nameHump !="updateAt">
        ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
            </#if>
        </#list>

        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.edit(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }


}
