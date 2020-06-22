package com.cjdx.pojo.VO;

import com.cjdx.pojo.Answer;
import com.cjdx.pojo.QueOption;

import java.util.List;

public class QuestionAndAnswerVO {
    private String title;
    private String type;
    private List<String> answerList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }
}
