package com.cjdx.service.impl;

import com.cjdx.mapper.QueOptionMapper;
import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.Question;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.service.QueOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueOptionServiceImpl implements QueOptionService {
    @Autowired
    private QueOptionMapper queOptionMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addQueOption(Integer queId, List<String> options) throws Exception {
        List<QueOption> list = new ArrayList<>();
        for(String content : options) {
            QueOption option = new QueOption();
            option.setContent(content);
            option.setQuestionId(queId);
            list.add(option);
        }
        queOptionMapper.insertList(list);

    }

    @Override
    public List<QueOption> queryQueOption(Integer questionId) throws Exception {
        Example example = new Example(QueOption.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("question_id",questionId);
        List<QueOption> list = queOptionMapper.selectByExample(example);
        return list;
    }

    @Override
    public void delQueOption(List<Integer> queId) throws Exception {

        queOptionMapper.deleteOptionByQuestionId(queId);
    }
}
