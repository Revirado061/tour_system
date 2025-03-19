package org.example.springboottoursystem.controller;

import jakarta.annotation.Resource;
//import org.example.springboottoursystem.service.SpotService;
import org.example.springboottoursystem.domain.Spot;
import org.example.springboottoursystem.domain.Spot;
import org.example.springboottoursystem.service.SpotService;
import org.example.springboottoursystem.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/spot")  //是这个控制器类的基路由
public class SpotController {

    @Resource
    private SpotService spotService;

    @RequestMapping("/showAll")
    public Result<List> showAllSpotController(){
        List<Spot> topTable = spotService.showAllSpot();
        return new Result<>(topTable);
    }


    @RequestMapping("/sortByHeat")
    public Result<List> sortByHeatController(){
        List<Spot> topTable = spotService.findTopHeat( 10);
        return new Result<>(topTable);
    }


    @RequestMapping("/sortByGrade")
    public Result<List> sortByGradeController(){
        List<Spot> topTable = spotService.findTopGrade(10);
        return new Result<>(topTable);
    }

    @RequestMapping("/search/nameOrType")  //搜索结果，名称、类别二选一
    public Result<List> searchByNameOrTypeController(@RequestParam("choice") String choice,  //name/type
                                                      @RequestParam("keyword") String keyword,
                                                      @RequestParam("rule") String rule)   //heat/grade
    {
        List<Spot> searchResult = null;
        List<Spot> result = null;
        if(Objects.equals(choice, "name")){
            searchResult = spotService.searchByName(keyword);
        }else if(Objects.equals(choice, "type")){
            searchResult = spotService.searchByType(keyword);
        }
        if(Objects.equals(rule, "heat")){
            result =  spotService.sortAllByHeat(searchResult);
        }else if (Objects.equals(rule, "grade")){
            result =  spotService.sortAllByGrade(searchResult);
        }
        return new Result<>(result);
    }

    @RequestMapping("/search/nameAndType")  //搜索结果，名称和类别
    public Result<List> searchByNameAndTypeController(@RequestParam("nameKeyword") String nameKeyword,
                                                       @RequestParam("typeKeyword") String typeKeyword,
                                                       @RequestParam("rule") String rule)   //heat/grade
    {
        List<Spot> searchResult = spotService.searchByNameAndType(nameKeyword, typeKeyword);
        List<Spot> result = null;
        if(Objects.equals(rule, "heat")){
            result =  spotService.sortAllByHeat(searchResult);
        }else if (Objects.equals(rule, "grade")){
            result =  spotService.sortAllByGrade(searchResult);
        }
        return new Result<>(result);
    }

}
