package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.entity.User;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionMapper questionMapper;

    //分页
    @GetMapping("/findAll/{page}/{size}")
    public Page<Question> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return questionRepository.findAll(pageRequest);
    }


    //保存问题
    @PostMapping("/save")
    public String Save(@RequestBody Question question) {
        //创建日期
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = dateFormat.format(date);
        question.setGmt_create(format);
        question.setReplied("否");

        Question result = questionRepository.save(question);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }


    @GetMapping("/findById/{id}")
    public Question findById(@PathVariable Integer id) {
        return questionRepository.findById(id).get();
    }

    @GetMapping("/findByQuestioner/{questioner}/{page}/{size}")
    public Page<Question> findByQuestioner(@PathVariable("page") Integer page,
                                           @PathVariable("size") Integer size,
                                           @PathVariable String questioner) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Question> byQuestioner = questionRepository.findByQuestioner(questioner, pageRequest);
        return byQuestioner;
    }

    //修改问题
    @PostMapping("/update")
    public String update(@RequestBody Question question) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = dateFormat.format(date);
        question.setGmt_modified(format);
        question.setReplied("否");
        Question result = questionRepository.save(question);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable Integer id) {
        questionRepository.deleteById(id);
    }

    //教师查看回复页面
    @GetMapping("/teacherAnswer/{username}/{replied}/{page}/{size}")
    public Page<Question> findTeacher(@PathVariable String username,
                                      @PathVariable String replied,
                                      @PathVariable("page") Integer page,
                                      @PathVariable("size") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Question> byTeacherTag = questionRepository.findByTeacherTagAndReplied(username, replied, pageRequest);
        return byTeacherTag;
    }


    //教师回复功能
    @PostMapping("/answerQuestion")
    public String answerQuestion(@RequestBody Question question) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = dateFormat.format(date);
        question.setGmt_modified(format);
        question.setReplied("是");
        questionMapper.answerQuestion(question);
        return "success";
    }

    //搜索功能
    @GetMapping("/searchQuestion/{title}/{page}/{size}")
    public Page<Question> searchQuestion(@PathVariable String title,
                                         @PathVariable("page") Integer page,
                                         @PathVariable("size") Integer size) {
        Page<Question> questions = questionService.searchQuestion(title, page, size);
        return questions;
    }


}
