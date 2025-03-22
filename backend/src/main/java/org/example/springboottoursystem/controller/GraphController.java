package org.example.springboottoursystem.controller;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.Building;
import org.example.springboottoursystem.domain.Facility;
import org.example.springboottoursystem.domain.Node;
import org.example.springboottoursystem.service.GraphService;
import org.example.springboottoursystem.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/graph")  //是这个控制器类的基路由
public class GraphController {

    @Resource
    private GraphService graphService;

    @RequestMapping("/allBuildings")  //展示所有设施
    public Result<List> showAllBuildingsController(){
        List<Building> buildings = graphService.showAllBuildings();
        return new Result<>(buildings);
    }


    @RequestMapping("/shortestLength")  //最短路径
    public Result<List> findShortestRouteController(@RequestBody Map<String, Object> request) {
        String start = (String) request.get("start");
        List<String> terminal = (List<String>) request.get("terminal");
        List<Node> route = graphService.findShortestLength(start, terminal);
        return new Result<>(route);
    }

    @RequestMapping("/shortestTime")  //最短时间
    public Result<List> findShortestTimeController(@RequestBody Map<String, Object> request){
        String start = (String) request.get("start");
        List<String> terminal = (List<String>) request.get("terminal");
        List<Node> route = graphService.findShortestTime(start, terminal);
        return new Result<>(route);
    }

    @RequestMapping("/allFacilities")  //展示所有设施
    public Result<List> showAllFacilitiesController(){
        List<Facility> facilities = graphService.showAllFacilities();
        return new Result<>(facilities);
    }

    @RequestMapping("/nearbyFacilities")  //附近的设施，按距离排序
    public Result<List> findNearbyFacilitiesController(@RequestParam("building") String buildingName,
                                                   @RequestParam("category") String category,  //"all"表示所有类别
                                                   @RequestParam("radius") double radius)  //半径
    {
        List<Facility> facilities = graphService.findNearbyFacilities(radius, buildingName, category);
        return new Result<>(facilities);
    }
}
