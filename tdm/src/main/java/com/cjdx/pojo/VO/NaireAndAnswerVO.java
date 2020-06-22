package com.cjdx.pojo.VO;

import java.util.List;

public class NaireAndAnswerVO {
    private Integer id;
    private String title;
    private String introduce;
    private Integer userId;
    //包含选项和作答结果
    private List<QuestionAndAnswerVO> questionVOList;

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

    public List<QuestionAndAnswerVO> getQuestionVOList() {
        return questionVOList;
    }

    public void setQuestionVOList(List<QuestionAndAnswerVO> questionVOList) {
        this.questionVOList = questionVOList;
    }
}
