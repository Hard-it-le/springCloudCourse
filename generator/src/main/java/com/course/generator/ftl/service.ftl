package com.course.server.service;

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;

public interface ${Domain}Service {
      void list(PageDto pageDto);
      void save(${Domain}Dto ${domain}Dto);
      void  delete(String id);
      void edit(${Domain}Dto ${domain}Dto);
}
