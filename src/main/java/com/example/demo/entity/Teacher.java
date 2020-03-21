package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Teacher {
    @Id//表明主键
    private Integer id;
    private String teacher_name;
    private Integer answer_id;
}
