package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Diary;

import java.util.List;

@Mapper
public interface DiaryMapper {
    Diary findById(long id);

    List<Diary> findAll();

    void save(Diary diary);
<<<<<<< HEAD

    void updateHeat(Diary diary);

    void updateGrade(Diary diary);
=======
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
}
