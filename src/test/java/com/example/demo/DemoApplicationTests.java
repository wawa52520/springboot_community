package com.example.demo;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    void contextLoads() {
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<Question> page = questionRepository.findAll(pageRequest);
    }

}
