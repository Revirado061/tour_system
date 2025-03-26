package org.example.springboottoursystem.service;

import org.example.springboottoursystem.domain.Diary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DiaryService {
     int getDiaryNumber();
     Diary updateGrade(long id, double star);

     Diary updateHeat(long id);

     List<Diary> sortAllByHeat(List<Diary> table);

     List<Diary> sortAllByGrade(List<Diary> table);

     List<Diary> searchBySpot(String keyword);

     List<Diary> searchByTitle(String keyword);

     List<Diary> searchBySpotAndTitle(String spotKeyword, String titleKeyword);

     List<Diary> findAllDiary();

     Diary writeDiary(String title, String spot, String author, String text) throws IOException;

     Diary readDiary(long id);

    void loadAllDiariesToElasticsearch() throws IOException;
}
