package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,password) values (#{name},#{password})")
    void ist(User user);

    //根据ID更改密码
    @Update("update user set password = #{password},gmt_modified=#{gmt_modified} where id = #{id}")
    void updateById(User user);

    @Update("update user set power = #{power} where id =#{id}")
    void updatePower(Integer id,String power);
}
