package com.example.demo.mapper;

import com.example.demo.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {
    @Update("update question set answer = #{answer},gmt_modified = #{gmt_modified},replied=#{replied} where id = #{id}")
    void answerQuestion(Question question);
//
//    @Select("select * from question")
//    List<Map> tst();

    @Select("select tag,count(*)as 次数 from question group by tag limit 3 ")
    List<Map> findTagCount();

    @Select("select questioner,count(*)as 次数 from question group by questioner order by 次数 DESC limit 3 ")
    List<Map> findUserCount();
}
