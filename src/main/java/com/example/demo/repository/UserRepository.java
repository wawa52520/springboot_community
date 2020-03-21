package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

     List<User> findByNameAndPassword(String name, String password);


     List<User> findByName(String name);

     List<User> findByNameAndPasswordAndPower(String name,String Password,String power);
}
