package org.example.springboottoursystem.service;
import org.example.springboottoursystem.domain.Spot;

import java.util.List;


public interface SpotService {

    List<Spot> showAllSpot();

    List<Spot> findTopHeat(int n);


    /**
     * 按评价排序业务逻辑
     * @param n 排序结果显示个数
     * @return
     */
    List<Spot> findTopGrade(int n);

    List<Spot> searchByName(String keyword);

    List<Spot> searchByType(String keyword);

    List<Spot> searchByNameAndType(String nameKeyword, String typeKeyword);

    List<Spot> sortAllByHeat(List<Spot> table);

    List<Spot> sortAllByGrade(List<Spot> table);

    Spot getSpotByName(String name);

}
