package com.example.demo.repository;

import com.example.demo.entity.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

//question表名，long主键类型
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    ArrayList<Question> findByQuestioner(String questioner);
}
