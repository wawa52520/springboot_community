package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

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

    @GetMapping("/findByQuestioner/{questioner}/{page}/{size}")
    public PageInfo<Question> findByQuestioner(@PathVariable("page") Integer page,
                                               @PathVariable("size") Integer size,
                                               @PathVariable String questioner) {
        PageHelper.startPage(page, size);
        ArrayList<Question> byQuestioner = questionRepository.findByQuestioner(questioner);
        PageInfo<Question> pageInfo = new PageInfo<>(byQuestioner);
        System.out.println("总数量：" + pageInfo.getTotal());
        System.out.println("当前页查询记录：" + pageInfo.getList().size());
        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("每页显示数量：" + pageInfo.getPageSize());
        System.out.println("总页：" + pageInfo.getPages());
        System.out.println(pageInfo.toString());
        return pageInfo;
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
