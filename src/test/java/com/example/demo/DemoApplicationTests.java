package com.example.demo;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    void contextLoads() {

        ArrayList<Question> byQuestioner = questionRepository.findByQuestioner("root");
        PageInfo<Question> pageInfo = new PageInfo<>(byQuestioner);
//        pageInfo.setPageNum(0);
//        pageInfo.setPageSize(1);
        PageHelper.startPage(0, 2);
        System.out.println("总数量：" + pageInfo.getTotal());
        System.out.println("当前页查询记录：" + pageInfo.getList().size());
        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("每页显示数量：" + pageInfo.getPageSize());
        System.out.println("总页：" + pageInfo.getPages());
        System.out.println(pageInfo);
    }

    @Test
    void page(){
        PageHelper.startPage(0,1);
        ArrayList<Question> byQuestioner = questionRepository.findByQuestioner("root");
        PageInfo pageInfo = new PageInfo(byQuestioner);
        System.out.println(pageInfo);
    }

}
