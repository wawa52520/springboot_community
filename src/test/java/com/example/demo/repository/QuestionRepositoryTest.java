package com.example.demo.repository;

import com.example.demo.entity.Question;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() {
        System.out.println(questionRepository.findAll());
    }

    @Test
    void save() {
        Question question = new Question();
        question.setTitle("vue测试");
        question.setDescription("这是一个vue测试");
        question.setTag("vue");
        question.setGmt_modified(question.getGmt_create());
        Question question1 = questionRepository.save(question);
        System.out.println(question1);
    }

    @Test
    void findById() {
        Optional<Question> byId = questionRepository.findById(1);
        System.out.println(byId);
        Question question = questionRepository.findById(1).get();
        System.out.println(question);
    }

    @Test
    void update() {
        Question question = new Question();
        question.setId(14);
        question.setTitle("修改修改修改123");
        Question UpdateQuestion = questionRepository.save(question);
        System.out.println(UpdateQuestion);
    }

    @Test
    void delete() {
        questionRepository.deleteById(41);
    }

    @Test
    void show() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }

    @Test
    void login() {
        List<User> byNameAndPassword = userRepository.findByNameAndPassword("root", "root");
        User user = byNameAndPassword.get(0);
        System.out.println("get(0):" + user);
        System.out.println("no:" + byNameAndPassword);
    }
    @Test
    void findQuestionByUserName(){
        List<Question> byQuestioner = questionRepository.findByQuestioner("root");
        System.out.println(byQuestioner);
    }
}