package com.example.demo.repository;

import com.example.demo.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

//question表名，long主键类型
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Page<Question> findByQuestioner(String questioner,Pageable pageable);
    Page<Question> findByTeacherTag(String teacher,Pageable pageable);
}
