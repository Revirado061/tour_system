package org.example.springboottoursystem.service.servicelmpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.Food;
import org.example.springboottoursystem.mapper.FoodMapper;
import org.example.springboottoursystem.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodServiceImpl implements FoodService {

    @Resource
    private final FoodMapper foodMapper;

    public FoodServiceImpl(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    @Override
    public List<Food> showAllFood() {
        return foodMapper.findAll();
    }

    @Override
    public List<Food> findTopHeat(int n) {
        List<Food> table = foodMapper.findAll();
        List<Food> topTable = new ArrayList<>();

        int foodNum = table.size();
        boolean[] chosen = new boolean[foodNum];
        int max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < foodNum; j++){
                if(table.get(j).getHeat() > max && !chosen[j]){
                    max = table.get(j).getHeat();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Food topN = new Food();
            topN.setId(table.get(pos[i]).getId());
            topN.setName(table.get(pos[i]).getName());
            topN.setDistance(table.get(pos[i]).getDistance());
            topN.setKind(table.get(pos[i]).getKind());
            topN.setRestaurant(table.get(pos[i]).getRestaurant());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Food> findTopGrade(int n) {
        List<Food> table = foodMapper.findAll();
        List<Food> topTable = new ArrayList<>();
        int foodNum = table.size();
        boolean[] chosen = new boolean[foodNum];
        double max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < foodNum; j++){
                if(table.get(j).getGrade() > max && !chosen[j]){
                    max = table.get(j).getGrade();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Food topN = new Food();
            topN.setId(table.get(pos[i]).getId());
            topN.setName(table.get(pos[i]).getName());
            topN.setDistance(table.get(pos[i]).getDistance());
            topN.setKind(table.get(pos[i]).getKind());
            topN.setRestaurant(table.get(pos[i]).getRestaurant());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Food> findClosest(int n) {
        List<Food> table = foodMapper.findAll();
        List<Food> topTable = new ArrayList<>();

        int foodNum = table.size();
        boolean[] chosen = new boolean[foodNum];
        double min = 5000;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            min = 5000;
            for(int j = 0; j < foodNum; j++){
                if(table.get(j).getDistance() < min && !chosen[j]){
                    min = table.get(j).getDistance();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Food topN = new Food();
            topN.setId(table.get(pos[i]).getId());
            topN.setName(table.get(pos[i]).getName());
            topN.setDistance(table.get(pos[i]).getDistance());
            topN.setKind(table.get(pos[i]).getKind());
            topN.setRestaurant(table.get(pos[i]).getRestaurant());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Food> search(String choice, String keyword) {
        List<Food> table = foodMapper.findAll();
        List<Food> result = new ArrayList<>();
        if(Objects.equals(choice, "name")){
            for (Food food : table) {
                if(food.getName().contains(keyword)){
                    result.add(food);
                }
            }
        }else if(Objects.equals(choice, "kind")){
            for (Food food : table) {
                if(food.getKind().contains(keyword)){
                    result.add(food);
                }
            }
        }else if(Objects.equals(choice, "restaurant")){
            for (Food food : table) {
                if(food.getRestaurant().contains(keyword)){
                    result.add(food);
                }
            }
        }
        return result;
    }


    @Override
    public List<Food> sortAllByHeat(List<Food> table) {
        List<Food> topTable = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        int max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < n; j++){
                if(table.get(j).getHeat() > max && !chosen[j]){
                    max = table.get(j).getHeat();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Food topN = new Food();
            topN.setId(table.get(pos[i]).getId());
            topN.setName(table.get(pos[i]).getName());
            topN.setDistance(table.get(pos[i]).getDistance());
            topN.setKind(table.get(pos[i]).getKind());
            topN.setRestaurant(table.get(pos[i]).getRestaurant());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Food> sortAllByGrade(List<Food> table) {
        List<Food> topTable = new ArrayList<>();
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
            Food topN = new Food();
            topN.setId(table.get(pos[i]).getId());
            topN.setName(table.get(pos[i]).getName());
            topN.setDistance(table.get(pos[i]).getDistance());
            topN.setKind(table.get(pos[i]).getKind());
            topN.setRestaurant(table.get(pos[i]).getRestaurant());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Food> sortAllByDistance(List<Food> table) {
        List<Food> topTable = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        double min = 5000;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            min = 5000;
            for(int j = 0; j < n; j++){
                if(table.get(j).getDistance() < min && !chosen[j]){
                    min = table.get(j).getDistance();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Food topN = new Food();
            topN.setId(table.get(pos[i]).getId());
            topN.setName(table.get(pos[i]).getName());
            topN.setDistance(table.get(pos[i]).getDistance());
            topN.setKind(table.get(pos[i]).getKind());
            topN.setRestaurant(table.get(pos[i]).getRestaurant());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topTable.add(topN);
        }
        return topTable;
    }
}
