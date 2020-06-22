package com.cjdx.service.impl;

import com.cjdx.mapper.QuestionMapper;
import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.Question;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.UserInfo;
import com.cjdx.service.QueOptionService;
import com.cjdx.service.QuestionService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private QueOptionService queOptionService;


    @Override
    public Integer addCheckQuestion(Integer naireId, String title, Integer type,
                               List<String> options) throws Exception {
        Question question = new Question();
        question.setNarieId(naireId);
        question.setTitle(title);
        question.setType(type);
        questionMapper.insert(question);
        Integer queId = question.getId();
        queOptionService.addQueOption(queId, options);
        return question.getId();
    }

    @Override
    public Integer addQAQuestion(Integer naireId, String title, Integer type) throws Exception {
        Question question = new Question();
        question.setNarieId(naireId);
        question.setTitle(title);
        question.setType(type);
        questionMapper.insert(question);

        return null;
    }

    @Override
    public void updateQuestion(Integer queId,Integer naireId, String title, Integer type) throws Exception {
        Question question = new Question();
        question.setId(queId);
        question.setNarieId(naireId);
        question.setTitle(title);
        question.setType(type);
        questionMapper.updateByPrimaryKey(question);
    }
    @Override
    public List<Question> queryQuestion(Integer naireId) throws Exception {
        Example example = new Example(Question.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("naire_id",naireId);
        List<Question> list = questionMapper.selectByExample(example);
        return list;
    }
    @Override
    @Transactional
    public void delQuestion(Integer naireId) throws Exception {
        Example example = new Example(Question.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("naireId",naireId);
        List<Integer> list = questionMapper.queryQuestionIdListByNaire(naireId);
        if (!list.isEmpty()) {
            queOptionService.delQueOption(list);
        }
        questionMapper.deleteByExample(example);
    }
}
