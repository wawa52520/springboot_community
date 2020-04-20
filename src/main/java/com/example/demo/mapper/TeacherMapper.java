package com.example.demo.mapper;

import com.example.demo.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TeacherMapper {
    @Delete("delete from teacher where id =#{id}")
    void deleteTeacherById(Integer id);

    @Select("select * from teacher where id = #{id}")
    ArrayList<Teacher> findTeacher(int id);

    @Insert("insert into teacher (id,teacher_name) values (#{id},#{teacher_name})")
    void insertTeacher(Teacher teacher);
}
