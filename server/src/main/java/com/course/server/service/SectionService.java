package com.course.server.service;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;

public interface SectionService {
      void list(PageDto pageDto);
      void save(SectionDto SectionDto);
      void  delete(String id);
      void edit(SectionDto SectionDto);
}
