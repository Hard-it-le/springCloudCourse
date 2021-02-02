package com.course.business.controller.admin;

import com.course.server.dto.TestDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.TestService;
import com.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    public static final String BUSINESS_NAME = "测试";

    @Resource
    private TestService testService;


    /**
     * 查询business列表
     *
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        testService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 新增business
     *
     * @param testDto
     * @return
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody TestDto testDto) {

        // 保存校验

        ResponseDto responseDto = new ResponseDto();
        testService.save(testDto);
        responseDto.setContent(testDto);
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
        testService.delete(id);
        responseDto.setContent(id);
        return responseDto;
    }



    /**
     * 根据id修改business
     *
     * @param testDto
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResponseDto edit(@RequestBody TestDto testDto) {
        // 保存校验
        ResponseDto responseDto = new ResponseDto();
        testService.edit(testDto);
        responseDto.setContent(testDto);
        return responseDto;
    }


}
