package com.course.business.controller.admin;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.SectionService;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/Section")
public class SectionController {
    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private SectionService SectionService;


    /**
     * 查询章节列表
     *
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto Section(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        SectionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 新增章节
     *
     * @param SectionDto
     * @return
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody SectionDto SectionDto) {

        // 保存校验

        ResponseDto responseDto = new ResponseDto();
        SectionService.save(SectionDto);
        responseDto.setContent(SectionDto);
        return responseDto;
    }

    /**
     * 删除章节
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable("id") String id) {
        ResponseDto responseDto = new ResponseDto();
        SectionService.delete(id);
        responseDto.setContent(id);
        return responseDto;
    }



    /**
     * 根据id修改章节
     *
     * @param SectionDto
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResponseDto edit(@RequestBody SectionDto SectionDto) {
        // 保存校验
        ResponseDto responseDto = new ResponseDto();
        SectionService.edit(SectionDto);
        responseDto.setContent(SectionDto);
        return responseDto;
    }


}
