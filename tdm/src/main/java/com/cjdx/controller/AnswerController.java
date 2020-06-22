package com.cjdx.controller;

import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.pojo.Answer;
import com.cjdx.pojo.VO.NaireAndAnswerVO;
import com.cjdx.service.AnswerService;
import com.cjdx.service.QuestionNaireService;
import com.cjdx.utils.CommonReturnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionNaireService questionNaireService;

    @RequestMapping("/add")
    public CommonReturnType addAnswerList(@RequestBody List<Answer> answerList,
                                           @RequestParam("token") String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (answerList == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        answerService.addAnswerList(answerList, token);

        return CommonReturnType.create(null);
    }
    @RequestMapping("/content")
    public CommonReturnType queryNaireAndAnswer(@RequestParam("naireId") Integer naireId,
                                                @RequestParam("token") String token) throws Exception{
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (naireId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        NaireAndAnswerVO vo = questionNaireService.queryNaireAndAnswer(naireId, token);
        return CommonReturnType.create(vo);
    }
}
