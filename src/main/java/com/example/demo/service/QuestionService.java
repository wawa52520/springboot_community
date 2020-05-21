package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public Page<Question> searchQuestion(String title, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Question> byTitleLike = questionRepository.findByTitleLike("%"+title+"%", pageRequest);
        return byTitleLike;
    }

    public List<Map> tagCount(){
        List<Map> tagCount = questionMapper.findTagCount();
        return tagCount;
    }

    public List<Map> userCount(){
        List<Map> userCount = questionMapper.findUserCount();
        return userCount;
    }
}
