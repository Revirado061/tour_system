package org.example.springboottoursystem.controller;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.Diary;
import org.example.springboottoursystem.domain.HuffmanNode;
import org.example.springboottoursystem.domain.Spot;
import org.example.springboottoursystem.service.DiaryService;
import org.example.springboottoursystem.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/diary")  //是这个控制器类的基路由
public class DiaryController {
    @Resource
    private DiaryService diaryService;

    @RequestMapping("number")  //获取日记数目
    public Result<Integer> numberController(){
        int number = diaryService.getDiaryNumber();
        return new Result<>(number);
    }

    @RequestMapping("click")  //处理点击事件，热度+1
    public Result<Diary> diaryHeatController(@RequestParam("id") long id){
        Diary diary;
        diary = diaryService.updateHeat(id);
        return new Result<>(diary);
    }

    @RequestMapping("read")  //读日记
    public Result<Diary> readDiaryController(@RequestParam("id") long id){  //传入日记的id
        Diary diary = diaryService.readDiary(id);
        return new Result<>(diary);
    }

    @RequestMapping("star")  //给日记打分
    public Result<Diary> diaryGradeController(@RequestParam("id") long id,
                                              @RequestParam("star") double star){
        Diary diary;
        diary = diaryService.updateGrade(id, star);
        return new Result<>(diary);
    }

    @RequestMapping("write")  //写日记
    public Result<Diary> writeDiaryController(@RequestParam("title") String title,
                                              @RequestParam("spot") String spot,  //景点
                                              @RequestParam("author") String author,
                                              @RequestParam("text") String text){
        Diary diary;
        diary = diaryService.writeDiary(title, spot, author, text);
        return new Result<>(diary);
    }

    @RequestMapping("heat")  //展示所有日记，按热度排序
    public Result<List> sortAllByHeatController(){
        List<Diary> table = diaryService.findAllDiary();
        List<Diary> topTable = diaryService.sortAllByHeat(table);
        return new Result<>(topTable);
    }

    @RequestMapping("grade")  //展示所有日记，按评分排序
    public Result<List> sortAllByGradeController(){
        List<Diary> table = diaryService.findAllDiary();
        List<Diary> topTable = diaryService.sortAllByGrade(table);
        return new Result<>(topTable);
    }

    @RequestMapping("/search/spotOrTitle")  //搜索结果，景点、标题二选一
    public Result<List> searchBySpotOrTitleController(@RequestParam("choice") String choice,  //spot/title
                                             @RequestParam("keyword") String keyword,
                                             @RequestParam("rule") String rule)   //heat/grade
    {
        List<Diary> searchResult = null;
        List<Diary> result = null;
        if(Objects.equals(choice, "spot")){
            searchResult = diaryService.searchBySpot(keyword);
        }else if(Objects.equals(choice, "title")){
            searchResult = diaryService.searchByTitle(keyword);
        }
        if(Objects.equals(rule, "heat")){
            result =  diaryService.sortAllByHeat(searchResult);
        }else if (Objects.equals(rule, "grade")){
            result =  diaryService.sortAllByGrade(searchResult);
        }
        return new Result<>(result);
    }

    @RequestMapping("/search/spotAndTitle")  //搜索结果，景点和标题
    public Result<List> searchBySpotAndTitleController(@RequestParam("spotKeyword") String spotKeyword,
                                                       @RequestParam("titleKeyword") String titleKeyword,
                                                       @RequestParam("rule") String rule)   //heat/grade
    {
        List<Diary> searchResult = diaryService.searchBySpotAndTitle(spotKeyword, titleKeyword);
        List<Diary> result = null;
        if(Objects.equals(rule, "heat")){
            result =  diaryService.sortAllByHeat(searchResult);
        }else if (Objects.equals(rule, "grade")){
            result =  diaryService.sortAllByGrade(searchResult);
        }
        return new Result<>(result);
    }

}
