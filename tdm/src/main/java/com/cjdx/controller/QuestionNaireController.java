package com.cjdx.controller;

import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.VO.NaireAndAnswerVO;
import com.cjdx.pojo.VO.NaireVO;
import com.cjdx.service.QuestionNaireService;
import com.cjdx.utils.CommonReturnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/naire")
public class QuestionNaireController {
    @Autowired
    private QuestionNaireService questionNaireService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/add")
    public CommonReturnType addNaire(@RequestParam("title") String title,
                                     @RequestParam("introduce") String introduce,
                                     @RequestParam("type") String type,
                                     @RequestParam("token")String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        if (StringUtils.isBlank(title)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"请输入标题");
        }
        if (StringUtils.isBlank(introduce)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"请输入问卷介绍");
        }
        if (StringUtils.isBlank(type)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"请输入问卷类型");
        }
        Integer naireId = questionNaireService.addNaireAndSave(title,introduce,type,token);
        return CommonReturnType.create(naireId);
    }

    @RequestMapping("/push")
    public CommonReturnType pushNaire(@RequestParam("token") String token,
                                      @RequestParam("naireId")Integer naireId) throws Exception {
        if (naireId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        questionNaireService.nairePush(naireId);
        List<QuestionNaire> list = questionNaireService.queryMyNaire(token);
        return CommonReturnType.create(list);
    }
    @RequestMapping("/pull")
    public CommonReturnType pullNaire(@RequestParam("token") String token,
                                      @RequestParam("naireId")Integer naireId) throws Exception {
        if (naireId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        questionNaireService.nairePull(naireId);
        List<QuestionNaire> list = questionNaireService.queryMyNaire(token);
        return CommonReturnType.create(list);
    }

    @RequestMapping("/all")
    public CommonReturnType getAllNaire(@RequestParam("token") String token) throws Exception {
        //String token = httpServletRequest.getParameterMap().get("token")[0];
        List<QuestionNaire> list = questionNaireService.queryAllNaire(token);
        return CommonReturnType.create(list);
    }

    @RequestMapping("/query")
    public CommonReturnType getAllNaireByTitle(String title) {
        //String token = httpServletRequest.getParameterMap().get("token")[0];
        List<QuestionNaire> list = questionNaireService.queryNaireByTitle(title);
        return CommonReturnType.create(list);
    }

    @RequestMapping("/myNaire")
    public CommonReturnType getMyNaire(@RequestParam("token") String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.USER_EXPIRE);
        }
        List<QuestionNaire> list = questionNaireService.queryMyNaire(token);
        return CommonReturnType.create(list);
    }
    @RequestMapping("/myPushedNaire")
    public CommonReturnType getMyPushedNaire(@RequestParam("token") String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.USER_EXPIRE);
        }
        List<QuestionNaire> list = questionNaireService.queryMyPushedNaire(token);
        return CommonReturnType.create(list);
    }
    @RequestMapping("/myNotPushNaire")
    public CommonReturnType getMyNotPushNaire(@RequestParam("token") String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.USER_EXPIRE);
        }
        List<QuestionNaire> list = questionNaireService.queryMyNotPushNaire(token);
        return CommonReturnType.create(list);
    }

    @RequestMapping("/myWritten")
    public CommonReturnType getMyWrittenNaire(@RequestParam("token") String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.USER_EXPIRE);
        }
        List<QuestionNaire> list = questionNaireService.queryMyWrittenNaire(token);
        return CommonReturnType.create(list);
    }

    @RequestMapping("/content")
    public CommonReturnType getNaireContent(Integer naireId) throws Exception{
        if (naireId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        NaireVO naireVO = questionNaireService.queryNaireContent(naireId);
        return CommonReturnType.create(naireVO);
    }

    @RequestMapping("/contentAndAnswer")
    public CommonReturnType getNaireContentAndAnswer(@RequestParam("token") String token,
                                                     @RequestParam("naireId")Integer naireId) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.USER_EXPIRE);
        }
        if (naireId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        NaireAndAnswerVO naireVO = questionNaireService.queryNaireAndAnswer(naireId,token);
        return CommonReturnType.create(naireVO);
    }



    @RequestMapping("/del")
    public CommonReturnType delNaireById(Integer naireId) throws Exception {
        if (naireId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        questionNaireService.delNaireById(naireId);
        return CommonReturnType.create(null);
    }


}
