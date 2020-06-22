package com.cjdx.service.impl;

import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.mapper.QuestionNaireMapper;
import com.cjdx.mapper.UserNaireMapper;
import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.UserInfo;
import com.cjdx.pojo.VO.NaireAndAnswerVO;
import com.cjdx.pojo.VO.NaireVO;
import com.cjdx.pojo.VO.QuestionAndAnswerVO;
import com.cjdx.pojo.VO.QuestionVO;
import com.cjdx.service.QuestionNaireService;
import com.cjdx.service.QuestionService;
import io.swagger.models.auth.In;
import javafx.geometry.VPos;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionNaireServiceImpl implements QuestionNaireService{
    @Autowired
    private QuestionNaireMapper questionNaireMapper;
    @Autowired
    private UserNaireMapper userNaireMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private QuestionService questionService;

    @Override
    public List<QuestionNaire> queryAllNaire(String token) throws Exception{
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<QuestionNaire> list = questionNaireMapper.queryAllNaire(userInfo.getId());
        return list;
    }

    @Override
    public List<QuestionNaire> queryNaireByTitle(String title) {
        List<QuestionNaire> list = questionNaireMapper.queryNaireByTitle(title);
        return list;
    }

    @Override
    public List<QuestionNaire> queryMyPushedNaire(String token) throws Exception{
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = userInfo.getId();
        List<QuestionNaire> list = questionNaireMapper.queryMyPushedNaire(userId);

        return list;
    }
    @Override
    public List<QuestionNaire> queryMyNotPushNaire(String token) throws Exception{
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = userInfo.getId();
        List<QuestionNaire> list = questionNaireMapper.queryMyNotPushNaire(userId);
        return list;
    }
    @Override
    public List<QuestionNaire> queryMyNaire(String token) throws Exception{
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = userInfo.getId();
        List<QuestionNaire> list = questionNaireMapper.queryMyNaire(userId);
        return list;
    }

    @Override
    public List<QuestionNaire> queryMyWrittenNaire(String token) throws Exception{
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = userInfo.getId();
        List<QuestionNaire> list = userNaireMapper.queryMyNaireListByOperatorId(userId);
        return list;
    }

    @Override
    public void delNaireById(Integer naireId) throws Exception{
        questionNaireMapper.deleteByPrimaryKey(naireId);
        questionService.delQuestion(naireId);
        return;
    }

    @Override
    public NaireVO queryNaireContent(Integer naireId) {
        QuestionNaire naire = questionNaireMapper.selectByPrimaryKey(naireId);
        List<QuestionVO> questionVO = questionNaireMapper.queryNaireContentById(naireId);
        NaireVO naireVO = transferNaireTOVO(naire,questionVO);
        return naireVO;
    }

    @Override
    public NaireAndAnswerVO queryNaireAndAnswer(Integer naireId, String token) throws Exception{
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = userInfo.getId();
        QuestionNaire naire = questionNaireMapper.selectByPrimaryKey(naireId);
        List<QuestionAndAnswerVO> queryNaireAndAnswer = questionNaireMapper.queryQuestionAndAnswer(naireId,userId);
        NaireAndAnswerVO vo = transferToNaireAndAnswer(naire,queryNaireAndAnswer);
        return vo;
    }

    @Override
    public Integer addNaireAndSave(String title, String introduce, String type, String token) throws Exception {
        UserInfo userInfo = (UserInfo)redisTemplate.opsForValue().get(token);
        if (userInfo == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Integer userId = userInfo.getId();

        QuestionNaire naire = new QuestionNaire();
        naire.setTitle(title);
        naire.setIntroduce(introduce);
        naire.setCreateTime(new Timestamp(System.currentTimeMillis()));
        naire.setType(type);
        naire.setUserId(userId);
        naire.setIsPublished(0);
        questionNaireMapper.insert(naire);
        Integer naireId = naire.getId();
        return naireId;
    }
    @Override
    public Integer nairePush(Integer naireId) throws Exception {
        questionNaireMapper.updatePublishStateToYes(naireId);
        return naireId;
    }

    @Override
    public Integer nairePull(Integer naireId) throws Exception {
        questionNaireMapper.updatePublishStateToNo(naireId);
        return naireId;
    }

    public NaireVO transferNaireTOVO(QuestionNaire naire, List<QuestionVO> list) {
        NaireVO naireVO = new NaireVO();
        naireVO.setId(naire.getId());
        naireVO.setTitle(naire.getTitle());
        naireVO.setIntroduce(naire.getIntroduce());
        naireVO.setUserId(naire.getUserId());
        naireVO.setQuestionVOList(list);
        return naireVO;
    }

    private NaireAndAnswerVO transferToNaireAndAnswer(QuestionNaire naire, List<QuestionAndAnswerVO> queryNaireAndAnswer) {
        NaireAndAnswerVO vo = new NaireAndAnswerVO();
        vo.setId(naire.getId());
        vo.setTitle(naire.getTitle());
        vo.setIntroduce(naire.getIntroduce());
        vo.setUserId(naire.getUserId());
        vo.setQuestionVOList(queryNaireAndAnswer);
        return vo;
    }
}
