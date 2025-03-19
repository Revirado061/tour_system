package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface DiaryDao extends JpaRepository<Diary, Long> {
    Diary findById(long id);

}
