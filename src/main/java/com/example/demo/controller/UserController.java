package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/Login")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/findByUserAndPassword/{user}/{pass}")
    public List<User> findUserAndPass(@PathVariable String user,
                                      @PathVariable String pass,
                                              HttpServletRequest request) {

        List<User> byNameAndPassword = userRepository.findByNameAndPassword(user, pass);
        if (byNameAndPassword != null) {
            request.getSession().setAttribute("user",byNameAndPassword);
            System.out.println("session:"+request.getSession().getAttribute("user"));
            return byNameAndPassword;
        } else {
            byNameAndPassword.clear();
            return byNameAndPassword;
        }

    }
}
