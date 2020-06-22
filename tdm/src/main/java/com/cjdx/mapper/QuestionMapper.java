package com.cjdx.mapper;

import com.cjdx.my.mapper.MyMapper;
import com.cjdx.pojo.Question;

import java.util.List;

public interface QuestionMapper extends MyMapper<Question> {

    List<Integer> queryQuestionIdListByNaire(Integer naireId);
}