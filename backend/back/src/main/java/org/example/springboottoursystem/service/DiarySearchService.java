package org.example.springboottoursystem.service;

import org.example.springboottoursystem.domain.DiaryEs;

import java.io.IOException;
import java.util.List;

public interface DiarySearchService {
    void ensureIndexExists() throws IOException;

    void syncDiaryToElasticsearch(DiaryEs diaryEs) throws IOException;

    List<DiaryEs> searchDiaries(String keyword) throws IOException;
}
