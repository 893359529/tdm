package com.cjdx.service.impl;

import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.mapper.AnswerMapper;
import com.cjdx.mapper.QuestionNaireMapper;
import com.cjdx.pojo.Answer;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.UserInfo;
import com.cjdx.pojo.VO.NaireAndAnswerVO;
import com.cjdx.pojo.VO.QuestionAndAnswerVO;
import com.cjdx.pojo.VO.NaireVO;
import com.cjdx.pojo.VO.QuestionVO;
import com.cjdx.service.AnswerService;
import com.cjdx.service.QuestionNaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private QuestionNaireService questionNaireService;
    @Autowired
    private QuestionNaireMapper questionNaireMapper;

    @Override
    public void addAnswerList(List<Answer> answerList, String token) throws Exception{
        Object userinfo = redisTemplate.opsForValue().get(token);
        if (userinfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = ((UserInfo)redisTemplate.opsForValue().get(token)).getId();
        for(Answer answer : answerList) {
            answer.setUserId(userId);
        }
        answerMapper.insertList(answerList);
    }




}
