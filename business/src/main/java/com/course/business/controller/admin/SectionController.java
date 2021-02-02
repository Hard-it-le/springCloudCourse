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
@RequestMapping("/admin/section")
public class SectionController {
    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private SectionService sectionService;


    /**
     * 查询business列表
     *
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto section(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sectionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 新增business
     *
     * @param sectionDto
     * @return
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {

        // 保存校验
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    /**
     * 删除business
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable("id") String id) {
        ResponseDto responseDto = new ResponseDto();
        sectionService.delete(id);
        responseDto.setContent(id);
        return responseDto;
    }



    /**
     * 根据id修改business
     *
     * @param sectionDto
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResponseDto edit(@RequestBody SectionDto sectionDto) {
        // 保存校验
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        sectionService.edit(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }


}
