package org.example.springboottoursystem.controller;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.Food;
import org.example.springboottoursystem.service.FoodService;
import org.example.springboottoursystem.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")  //是这个控制器类的基路由
public class FoodController {
    @Resource
    private FoodService foodService;

    @RequestMapping("/showAll")  //展示所有美食
    public Result<List> showAllSpotController(){
        List<Food> topTable = foodService.showAllFood();
        return new Result<>(topTable);
    }

    @RequestMapping("/top10")  //排序，展示前10个美食
    public Result<List> sortController(@RequestParam("rule") String rule ){   //heat/grade/distance（距离）
        int n = 10;
        List<Food> topTable = switch (rule) {
            case "heat" -> foodService.findTopHeat(n);
            case "grade" -> foodService.findTopGrade(n);
            case "distance" -> foodService.findClosest(n);
            default -> null;
        };
        return new Result<>(topTable);
    }


    @RequestMapping("/search")  //搜索并排序
    public Result<List> searchController(@RequestParam("choice") String choice,  //name/kind（菜系）/restaurant
                                         @RequestParam("keyword") String keyword,
                                         @RequestParam("rule") String rule       //heat/grade/distance（距离）
    ){
        List<Food> searchResult = foodService.search(choice, keyword);
        List<Food> result = switch (rule) {
            case "heat" -> foodService.sortAllByHeat(searchResult);
            case "grade" -> foodService.sortAllByGrade(searchResult);
            case "distance" -> foodService.sortAllByDistance(searchResult);
            default -> null;
        };
        return new Result<>(result);
    }

}
