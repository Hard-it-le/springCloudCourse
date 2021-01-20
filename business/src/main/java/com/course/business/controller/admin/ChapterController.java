package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.utils.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;


    /**
     * 查询章节列表
     *
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto chapter(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 新增章节
     *
     * @param chapterDto
     * @return
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        //后台为空或者长度不等于校验
        ValidatorUtil.require(chapterDto.getName(),"名称");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程id");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程长度",1,8);
        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
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
        chapterService.delete(id);
        responseDto.setContent(id);
        return responseDto;
    }

    /**
     * 根据id修改章节
     *
     * @param chapterDto
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResponseDto edit(@RequestBody ChapterDto chapterDto) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.edit(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }


}
