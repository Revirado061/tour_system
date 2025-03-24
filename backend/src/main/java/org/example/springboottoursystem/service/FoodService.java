package org.example.springboottoursystem.service;

import org.example.springboottoursystem.domain.Food;

import java.util.List;

public interface FoodService {
    List<Food> showAllFood();

    List<Food> findTopHeat(int n);

    List<Food> findTopGrade(int n);

    List<Food> findClosest(int n);

    List<Food> search(String choice, String keyword);

    List<Food> sortAllByHeat(List<Food> table);

    List<Food> sortAllByGrade(List<Food> table);

    List<Food> sortAllByDistance(List<Food> table);
}
