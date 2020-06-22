package com.cjdx.controller;

import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.pojo.QueOption;
import com.cjdx.service.QueOptionService;
import com.cjdx.utils.CommonReturnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/option")
public class QuestionOptionController {
    @Autowired
        private QueOptionService queOptionService;

//    @RequestMapping("/add")
//    public CommonReturnType addOption(List<QueOption> options) throws Exception {
//
//        if(options == null) {
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
//        queOptionService.addQueOption(options);
//        return CommonReturnType.create(null);
//    }

}
