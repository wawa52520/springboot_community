package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionMapper questionMapper;


    //教师回复功能
//    public String answerQuesetion(Question question){
//
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
//        String format = dateFormat.format(date);
//        question.setGmt_modified(format);
//        question.setReplied("是");
//
//        questionMapper.answerQuestion(question);
//        return "success";
//    }

}
