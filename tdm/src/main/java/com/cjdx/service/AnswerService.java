package com.cjdx.service;

import com.cjdx.pojo.Answer;
import com.cjdx.pojo.VO.NaireAndAnswerVO;
import com.cjdx.pojo.VO.NaireVO;
import com.cjdx.pojo.VO.QuestionAndAnswerVO;

import java.util.List;

public interface AnswerService {
    void addAnswerList(List<Answer> answerList, String token) throws Exception;
}
