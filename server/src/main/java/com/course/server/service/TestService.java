package com.course.server.service;

import com.course.server.dto.TestDto;
import com.course.server.dto.PageDto;

public interface TestService {
      void list(PageDto pageDto);
      void save(TestDto testDto);
      void  delete(String id);
      void edit(TestDto testDto);
}
