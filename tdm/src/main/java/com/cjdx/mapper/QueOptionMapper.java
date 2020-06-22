package com.cjdx.mapper;

import com.cjdx.my.mapper.MyMapper;
import com.cjdx.pojo.QueOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueOptionMapper extends MyMapper<QueOption> {

    void deleteOptionByQuestionId(@Param("questionIdList")List<Integer> questionIdList);
}