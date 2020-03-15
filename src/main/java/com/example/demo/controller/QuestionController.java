package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Question> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return questionRepository.findAll(pageRequest);
    }

    @GetMapping("/findQuestion/{questioner}/{page}/{size}")
    public List<Question> findByQuestioner(@PathVariable("page") Integer page,
                                           @PathVariable("size") Integer size,
                                           @PathVariable("questioner")String questioner) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return questionRepository.findByQuestioner(pageRequest);
    }

    //保存问题
    @PostMapping("/save")
    public String Save(@RequestBody Question question) {
        //创建日期
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
        String format = dateFormat.format(date);
        question.setGmt_create(format);


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

    @GetMapping("/findByQuestioner/{questioner}")
    public List<Question> findByQuestioner(@PathVariable String questioner) {
        return questionRepository.findByQuestioner(questioner);
    }

    @PostMapping("/update")
    public String update(@RequestBody Question question) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
        String format = dateFormat.format(date);
        question.setGmt_modified(format);
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
}
