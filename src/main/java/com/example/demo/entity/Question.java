package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity//实体类，class映射数据库的表
@Data
public class Question {
    @Id//表明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置自增
    private Integer id;
    private String title;
    private String description;
    private String gmt_create;
    private String questioner;
    private String tag;
    private String gmt_modified;
    private String teacherTag;
    private String replied;
    private String answer;
}
