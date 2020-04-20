package com.example.demo.mapper;

import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


@Mapper
public interface StudentMapper {
    @Select("select * from student where id = #{id}")
    ArrayList<Student> findStudent(int id);

    @Insert("insert into student (id,stu_name) values (#{id},#{stu_name})")
    void insertStudent(Student student);

    @Delete("delete from student where id =#{id}")
    void deleteStudentById(Integer id);
}
