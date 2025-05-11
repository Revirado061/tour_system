package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Diary;

import java.util.List;

@Mapper
public interface DiaryMapper {
    Diary findById(long id);

    List<Diary> findAll();

    void save(Diary diary);

    void updateHeat(Diary diary);

    void updateGrade(Diary diary);
}
