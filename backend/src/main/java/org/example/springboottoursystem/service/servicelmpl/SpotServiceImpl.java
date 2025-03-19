package org.example.springboottoursystem.service.servicelmpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.Spot;
import org.example.springboottoursystem.domain.Spot;
import org.example.springboottoursystem.domain.Spot;
import org.example.springboottoursystem.repository.SpotDao;
import org.example.springboottoursystem.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {

    @Resource
    private final SpotDao spotDao;

    @Autowired
    public SpotServiceImpl(SpotDao spotDao) {
        this.spotDao = spotDao;
    }


    @Override
    public List<Spot> showAllSpot() {
        List<Spot> table = spotDao.findAll();
        return table;
    }

    @Override
    public List<Spot> findTopHeat(int n) {
        List<Spot> table = spotDao.findAll();
        List<Spot> topTable = new ArrayList<>();

        int spotNum = table.size();
        boolean[] chosen = new boolean[spotNum];
        int max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < spotNum; j++){
                if(table.get(j).getHeat() > max && !chosen[j]){
                    max = table.get(j).getHeat();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Spot topN = new Spot();
            topN.setName(table.get(pos[i]).getName());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setType(table.get(pos[i]).getType());
            topTable.add(topN);
        }
        return topTable;
    }


    @Override
    public Spot getSpotByName(String name) {
        return spotDao.findByName(name);
    }


    @Override
    public List<Spot> findTopGrade(int n) {
        List<Spot> table = spotDao.findAll();
        List<Spot> topTable = new ArrayList<>();
        int spotNum = table.size();
        boolean[] chosen = new boolean[spotNum];
        double max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < spotNum; j++){
                if(table.get(j).getGrade() > max && !chosen[j]){
                    max = table.get(j).getGrade();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Spot topN = new Spot();
            topN.setName(table.get(pos[i]).getName());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setType(table.get(pos[i]).getType());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Spot> searchByName(String keyword) {
        List<Spot> table = spotDao.findAll();
        List<Spot> result = new ArrayList<>();
        for (Spot spot : table) {
            if(spot.getName().contains(keyword)){
                result.add(spot);
            }
        }
        return result;
    }

    @Override
    public List<Spot> searchByType(String keyword) {
        List<Spot> table = spotDao.findAll();
        List<Spot> result = new ArrayList<>();
        for (Spot spot : table) {
            if(spot.getType().contains(keyword)){
                result.add(spot);
            }
        }
        return result;
    }

    @Override
    public List<Spot> searchByNameAndType(String nameKeyword, String typeKeyword) {
        List<Spot> table = spotDao.findAll();
        List<Spot> result = new ArrayList<>();
        for (Spot spot : table) {
            if(spot.getName().contains(nameKeyword) && spot.getType().contains(typeKeyword)){
                result.add(spot);
            }
        }
        return result;
    }

    @Override
    public List<Spot> sortAllByHeat(List<Spot> table) {
        List<Spot> topTable = new ArrayList<>();
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
            Spot topN = new Spot();
            topN.setName(table.get(pos[i]).getName());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setType(table.get(pos[i]).getType());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Spot> sortAllByGrade(List<Spot> table) {
        List<Spot> topTable = new ArrayList<>();
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
            Spot topN = new Spot();
            topN.setName(table.get(pos[i]).getName());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setType(table.get(pos[i]).getType());
            topTable.add(topN);
        }
        return topTable;
    }
}
