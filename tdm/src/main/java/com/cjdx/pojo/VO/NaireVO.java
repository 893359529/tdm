package com.cjdx.pojo.VO;

import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.Question;

import java.util.List;

public class NaireVO {
    private Integer id;
    private String title;
    private String introduce;
    private Integer userId;
    private List<QuestionVO> questionVOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<QuestionVO> getQuestionVOList() {
        return questionVOList;
    }

    public void setQuestionVOList(List<QuestionVO> questionVOList) {
        this.questionVOList = questionVOList;
    }
}
