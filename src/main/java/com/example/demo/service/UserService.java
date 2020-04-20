package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import com.example.demo.mapper.*;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service

public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;

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
        if (userRepositoryAll != null || userRepositoryAll.getSize() != 0)
            return userRepositoryAll;
        else
            return null;
    }

    //删除用户
    public String deleteByUserId(Integer id) {
        userRepository.deleteById(id);
        return "success";
    }

    //管理员根据ID查看用户
    public User findByUserId(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    //管理员更新用户信息
    public String UpdateUser(User user) {

        String power = user.getPower();
        Integer id = user.getId();
        //当权限等于学生时，从学生表查数据
        if (power.equals("学生")) {
            ArrayList<Student> student = studentMapper.findStudent(id);
            //当学生表没有当前用户数据时，将当前用户数据插入学生表
            if (student.size() == 0) {
                //表明当前用户不在学生表，因为要将用户转入学生表，这里简单从teacher表和admin表直接根据id删除数据。
                teacherMapper.deleteTeacherById(id);
                adminMapper.deleteAdminById(id);
                userMapper.updatePower(id,power);

                Student student1 = new Student();
                student1.setStu_name(user.getName());
                student1.setId(user.getId());
                studentMapper.insertStudent(student1);
                return "success";
            }
           // 当学生表有当前用户数据时，则表示修改密码
            else {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                String format = simpleDateFormat.format(date);
                user.setGmt_modified(format);
                userRepository.save(user);
                return "success";
            }
        } else if (power.equals("教师")) {
            ArrayList<Teacher> teacher = teacherMapper.findTeacher(id);
            if (teacher.size() == 0) {

                studentMapper.deleteStudentById(id);
                adminMapper.deleteAdminById(id);
                userMapper.updatePower(id,power);

                Teacher teacher1 = new Teacher();
                teacher1.setTeacher_name(user.getName());
                teacher1.setId(user.getId());
                teacherMapper.insertTeacher(teacher1);
                return "success";
            }else {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                String format = simpleDateFormat.format(date);
                user.setGmt_modified(format);
                userRepository.save(user);
                return "success";
            }
        } else if (power.equals("管理员")) {
            ArrayList<Admin> admin = adminMapper.findAdmin(id);
            if (admin.size() == 0) {

                teacherMapper.deleteTeacherById(id);
                studentMapper.deleteStudentById(id);
                userMapper.updatePower(id,power);

                Admin admin1 = new Admin();
                admin1.setAdmin_name(user.getName());
                admin1.setId(user.getId());
                adminMapper.insertAdmin(admin1);
                return "success";
            }else {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                String format = simpleDateFormat.format(date);
                user.setGmt_modified(format);
                userRepository.save(user);
                return "success";
            }

        }
        return "success";
    }
}
