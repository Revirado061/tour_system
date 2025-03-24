package org.example.springboottoursystem.service.serviceImpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.Diary;
import org.example.springboottoursystem.domain.DiaryEs;
import org.example.springboottoursystem.mapper.DiaryMapper;
import org.example.springboottoursystem.service.DiarySearchService;
import org.example.springboottoursystem.service.DiaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class DiaryServiceImpl implements DiaryService {

    @Resource
    private final DiaryMapper diaryMapper;
    @Resource
    private final DiarySearchService diarySearchService;

    public DiaryServiceImpl(DiaryMapper diaryMapper, DiarySearchService diarySearchService) {
        this.diaryMapper = diaryMapper;
        this.diarySearchService = diarySearchService;
    }

    @Override
    public int getDiaryNumber() {
        List<Diary> table = diaryMapper.findAll();
        return table.size();
    }

    @Override
    public Diary updateHeat(long id) {
        Diary diary = diaryMapper.findById(id);
        diary.setHeat(diary.getHeat() + 1);
        diaryMapper.save(diary);

        // 同步到 Elasticsearch
        DiaryEs diaryEs = new DiaryEs();
        BeanUtils.copyProperties(diary, diaryEs);
        try {
            diarySearchService.syncDiaryToElasticsearch(diaryEs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return diary;
    }

    @Override
    public Diary updateGrade(long id, double star) {
        Diary diary = diaryMapper.findById(id);
        double grade = diary.getGrade() * 0.9 + star * 0.1;
        if (grade >= 10) {
            grade = 10.0;
        }
        diary.setGrade(grade);
        diaryMapper.save(diary);

        // 同步到 Elasticsearch
        DiaryEs diaryEs = new DiaryEs();
        BeanUtils.copyProperties(diary, diaryEs);
        try {
            diarySearchService.syncDiaryToElasticsearch(diaryEs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return diary;
    }

    @Override
    public List<Diary> sortAllByHeat(List<Diary> table) {
        List<Diary> topTable = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        int max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = -1;
            for(int j = 0; j < n; j++){
                if(table.get(j).getHeat() > max && !chosen[j]){
                    max = table.get(j).getHeat();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Diary topN = new Diary();
            topN.setId(table.get(pos[i]).getId());
            topN.setSpot(table.get(pos[i]).getSpot());
            topN.setAuthor(table.get(pos[i]).getAuthor());
            topN.setTitle(table.get(pos[i]).getTitle());
            topN.setDate(table.get(pos[i]).getDate());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setText(table.get(pos[i]).getText());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Diary> sortAllByGrade(List<Diary> table){
        List<Diary> topTable = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        double max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < n; j++){
                if(table.get(j).getGrade() > max && !chosen[j]){
                    max = table.get(j).getGrade();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Diary topN = new Diary();
            topN.setId(table.get(pos[i]).getId());
            topN.setSpot(table.get(pos[i]).getSpot());
            topN.setTitle(table.get(pos[i]).getTitle());
            topN.setAuthor(table.get(pos[i]).getAuthor());
            topN.setDate(table.get(pos[i]).getDate());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setText(table.get(pos[i]).getText());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Diary> searchBySpot(String keyword) {
        List<Diary> table = diaryMapper.findAll();
        List<Diary> result = new ArrayList<>();
        for (Diary diary : table) {
            if(diary.getSpot().contains(keyword)){
                result.add(diary);
            }
        }
        return result;
    }

    @Override
    public List<Diary> searchByTitle(String keyword) {
        List<Diary> table = diaryMapper.findAll();
        List<Diary> result = new ArrayList<>();
        for (Diary diary : table) {
            if(diary.getTitle().contains(keyword)){
                result.add(diary);
            }
        }
        return result;
    }

    @Override
    public List<Diary> searchBySpotAndTitle(String spotKeyword, String TitleKeyword) {
        List<Diary> table = diaryMapper.findAll();
        List<Diary> result = new ArrayList<>();
        for (Diary diary : table) {
            if(diary.getSpot().contains(spotKeyword) && diary.getTitle().contains(TitleKeyword)){
                result.add(diary);
            }
        }
        return result;
    }

    @Override
    public List<Diary> findAllDiary() {
        return diaryMapper.findAll();
    }

    @Override
    public Diary writeDiary(String title, String spot, String author, String text) throws IOException {
        diarySearchService.ensureIndexExists(); // 确保索引存在
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = Diary.formatLocalDateTime(now);
        Diary diary = new Diary(title, spot, author, formattedDate, 0, 7.0, text);
        diaryMapper.save(diary);

        // 同步到 Elasticsearch
        DiaryEs diaryEs = new DiaryEs();
        BeanUtils.copyProperties(diary, diaryEs);
        try {
            diarySearchService.syncDiaryToElasticsearch(diaryEs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return diary;
    }

    @Override
    public Diary readDiary(long id) {
        return diaryMapper.findById(id);
    }

    @Override
    public void loadAllDiariesToElasticsearch() throws IOException {
        // 确保索引存在
        diarySearchService.ensureIndexExists();

        // 查询 MySQL 数据库中的所有日记数据
        List<Diary> allDiaries = diaryMapper.findAll();

        // 将每条日记数据同步到 Elasticsearch
        for (Diary diary : allDiaries) {
            DiaryEs diaryEs = new DiaryEs();
            BeanUtils.copyProperties(diary, diaryEs);

            // 转换日期格式
            LocalDateTime dateTime = LocalDateTime.parse(diary.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            diaryEs.setDate(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            try {
                diarySearchService.syncDiaryToElasticsearch(diaryEs);
                System.out.println("Synced diary with ID: " + diary.getId());
            } catch (IOException e) {
                System.err.println("Error syncing diary with ID: " + diary.getId() + " to Elasticsearch: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
