package com.cjdx.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Answer {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 答案内容
     */
    private String content;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 问卷id
     */
    @Column(name = "naire_id")
    private Integer naireId;

    /**
     * 问题id
     */
    @Column(name = "question_id")
    private Integer questionId;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取答案内容
     *
     * @return content - 答案内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置答案内容
     *
     * @param content 答案内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取问卷id
     *
     * @return naire_id - 问卷id
     */
    public Integer getNaireId() {
        return naireId;
    }

    /**
     * 设置问卷id
     *
     * @param naireId 问卷id
     */
    public void setNaireId(Integer naireId) {
        this.naireId = naireId;
    }

    /**
     * 获取问题id
     *
     * @return question_id - 问题id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置问题id
     *
     * @param questionId 问题id
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}