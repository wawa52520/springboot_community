package com.example.demo.repository;

import com.example.demo.entity.Question;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

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


//    @Test
//    void findPage(){
//        PageHelper.startPage(0,5);
//        ArrayList<Question> byQuestioner = questionRepository.findByQuestioner("root");
//        PageInfo<Question> pageInfo = new PageInfo<>(byQuestioner);
//        System.out.println("总数量：" + pageInfo.getTotal());
//        System.out.println("当前页查询记录：" + pageInfo.getList().size());
//        System.out.println("当前页码：" + pageInfo.getPageNum());
//        System.out.println("每页显示数量：" + pageInfo.getPageSize());
//        System.out.println("总页：" + pageInfo.getPages());
//    }

    @Test
    void editPassword(){
        User user = new User();
        user.setId(4);
        user.setPassword("demo123");
    }

    @Test
    void createUser(){
        User user = new User();
        user.setName("yaojiabin");
        user.setPassword("yaojiabin");
        user.setId(5);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = dateFormat.format(date);
        user.setGmt_create(format);
        userRepository.save(user);
    }

    @Test
    void multy(){
        List<User> list = userRepository.findByName("root");
        System.out.println(list.size());
    }

    @Test
    void add(){
//        User user = new User();
//        user.setName("test");
//        user.setPassword("test");
//        userRepository.save(user);

        List<User> getId = userRepository.findByName("test");
        Integer userId = getId.get(0).getId();
        String UserName = getId.get(0).getName();
        System.out.println("all:"+ userId);
        Student student = new Student();
        student.setId(userId);
        student.setStu_name(UserName);
        studentRepository.save(student);
    }

    @Test
    void checkPower(){
//        User user = new User();
//        user.setName("stu");
//        user.setPassword("stu");
//        user.setPower("学生");
        List<User> byNameAndPasswordAndPower = userRepository.findByNameAndPasswordAndPower("stu", "stu", "学生");
        System.out.println("原:"+byNameAndPasswordAndPower);
        System.out.println("size:"+byNameAndPasswordAndPower.size());
        System.out.println("get:"+byNameAndPasswordAndPower.get(0));
        List<User> byNameAndPasswordAndPower1 = userRepository.findByNameAndPasswordAndPower("stu", "stu1", "学生");
        System.out.println("原1:"+byNameAndPasswordAndPower1);
        System.out.println("size1:"+byNameAndPasswordAndPower1.size());
    }

    @Test
    void test(){
        ArrayList<Object> arrayList = new ArrayList<>(1000000);
        Long startTime=System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i+"");
        }
        Long endTime=System.currentTimeMillis();
        Long totalTime=endTime-startTime;

    }

    @Test
    void answer(){
        Question question = new Question();
        question.setAnswer("这是一个回复123");
        question.setId(1);
        question.setReplied("是");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = dateFormat.format(date);
        question.setGmt_modified(format);
        questionMapper.answerQuestion(question);
    }

    @Test
    void search(){
//        Page<Question> spring = questionService.searchQuestion("spring", 1, 5);
//        System.out.println("结果:"+spring);

        ArrayList<Question> spring = questionRepository.findByTitleLike("%啊%");
        System.out.println(spring);
    }
}