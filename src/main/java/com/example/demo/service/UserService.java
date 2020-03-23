package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service

public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;

    //用户注册
    public String createUser(User user) {
        List<User> NameNumber = userRepository.findByName(user.getName());
        if (NameNumber.size() < 1) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String format = dateFormat.format(date);
            user.setGmt_create(format);
            user.setPower("学生");
            userRepository.save(user);
            //用户表保存同时保存到学生表
            Student student = new Student();
            student.setStu_name(user.getName());
            student.setId(user.getId());
            studentRepository.save(student);
            return "success";
        } else
            return "false";
    }

    public String checkPower(String name,String password,String power) {

        List<User> byNameAndPasswordAndPower = userRepository.findByNameAndPasswordAndPower(name, password, power);
        if (byNameAndPasswordAndPower.size() !=0 && byNameAndPasswordAndPower!=null) {
            return "success";
        } else {
            return "false";
        }
    }
}
