package com.cjdx.controller;

import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.Question;
import com.cjdx.service.QuestionService;
import com.cjdx.utils.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;

@RestController
@RequestMapping("/que")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/addCheck")
    public CommonReturnType addCheckQuestion(@RequestParam(value = "naireId")Integer naireId,
                                        @RequestParam(value = "title")String title,
                                        @RequestParam(value = "type")Integer type,
                                        @RequestParam(value = "options[]")List<String> options) throws Exception{

        questionService.addCheckQuestion(naireId,title,type,options);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/addQA")
    public CommonReturnType addQAQuestion(@RequestParam(value = "naireId")Integer naireId,
                                        @RequestParam(value = "title")String title,
                                        @RequestParam(value = "type")Integer type) throws Exception{

        questionService.addQAQuestion(naireId,title,type);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/query")
    public CommonReturnType queryQuestion(Integer naireId) throws Exception{

        List<Question> list = questionService.queryQuestion(naireId);
        return CommonReturnType.create(list);
    }

}
