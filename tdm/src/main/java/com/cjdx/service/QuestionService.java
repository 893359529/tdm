package com.cjdx.service;

import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.Question;

import java.util.List;

public interface QuestionService {
    Integer addCheckQuestion(Integer naireId, String title, Integer type,
                        List<String> options) throws Exception;

    Integer addQAQuestion(Integer naireId, String title, Integer type) throws Exception;

    void updateQuestion(Integer queId,Integer naireId, String title, Integer type) throws Exception;

    List<Question> queryQuestion(Integer naireId) throws Exception;

    void delQuestion(Integer naireId) throws Exception;
}
