package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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

    public String checkPower(String name, String password, String power) {

        List<User> byNameAndPasswordAndPower = userRepository.findByNameAndPasswordAndPower(name, password, power);
        if (byNameAndPasswordAndPower.size() != 0 && byNameAndPasswordAndPower != null) {
            return "success";
        } else {
            return "false";
        }
    }

    //管理员查找所有用户
    public Page<User> findAllUser(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> userRepositoryAll = userRepository.findAll(pageRequest);
        if (userRepositoryAll != null ||userRepositoryAll.getSize()!=0)
            return userRepositoryAll;
        else
            return null;
    }

    //删除用户
    public String deleteByUserId(Integer id){
        userRepository.deleteById(id);
        return "success";
    }

    //管理员根据ID查看用户
    public User findByUserId(Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }

    //管理员更新用户信息
    public String UpdateUser(User user){
//        String power = user.getPower();
//        if (power.equals());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = simpleDateFormat.format(date);
        user.setGmt_modified(format);
        userRepository.save(user);
        return "success";
    }
}
