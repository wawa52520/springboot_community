package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Login")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/findByUserAndPassword/{user}/{pass}/{type}")
    public List<User> findUserAndPass(@PathVariable String user,
                                      @PathVariable String pass,
                                      @PathVariable String type) {
        String s = userService.checkPower(user, pass, type);
        List<User> byNameAndPasswordAndPower = userRepository.findByNameAndPasswordAndPower(user, pass, type);
        if (s.equals("success")){
            return byNameAndPasswordAndPower;
        }else
            return null;
    }


    //更改密码
    @PostMapping("/updatePasswordById")
    public String updatePasswordById(@RequestBody User user) {
        //创建日期
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = dateFormat.format(date);
        user.setGmt_modified(format);
        userMapper.updateById(user);
        return null;
    }


    //创建用户，先判断用户名是否存在，存在return false，否则则存入
    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
        String s = userService.createUser(user);
        if (s.equals("success")){
            return "success";
        }else
            return "false";
    }
}
