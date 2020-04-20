package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Admin {
    @Id//表明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置自增
    private Integer id;
    private String admin_name;
}
