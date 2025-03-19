package org.example.springboottoursystem.service;

import org.example.springboottoursystem.domain.Diary;
import org.example.springboottoursystem.domain.HuffmanNode;
import org.example.springboottoursystem.domain.Node;

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

     Diary writeDiary(String title, String spot, String author, String text);

     Diary readDiary(long id);

     String compressDiary(Diary diary, String inputText);

     void generateHuffmanCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes);

     String uncompressDiary(Diary diary);
}
