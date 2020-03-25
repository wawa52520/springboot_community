package com.example.demo.mapper;

import com.example.demo.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QuestionMapper {
    @Update("update question set answer = #{answer},gmt_modified = #{gmt_modified},replied=#{replied} where id = #{id}")
    void answerQuestion(Question question);

}
