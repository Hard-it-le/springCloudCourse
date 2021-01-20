package com.course.server.service;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;

public interface ChapterService {
      void list(PageDto pageDto);


      void save(ChapterDto chapterDto);
}
