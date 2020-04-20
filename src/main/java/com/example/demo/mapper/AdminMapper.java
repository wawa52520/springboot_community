package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface AdminMapper {
    @Delete("delete from admin where id =#{id}")
    void deleteAdminById(Integer id);

    @Select("select * from admin where id = #{id}")
    ArrayList<Admin> findAdmin(int id);

    @Insert("insert into admin (id,admin_name) values (#{id},#{admin_name})")
    void insertAdmin(Admin admin);
}
