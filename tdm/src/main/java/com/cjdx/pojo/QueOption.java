package com.cjdx.pojo;

import javax.persistence.*;

@Table(name = "que_option")
public class QueOption {
    /**
     * 选项编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 选项内容
     */
    private String content;

    /**
     * 问题id
     */
    @Column(name = "question_id")
    private Integer questionId;


    /**
     * 获取选项编号
     *
     * @return id - 选项编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置选项编号
     *
     * @param id 选项编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取选项内容
     *
     * @return content - 选项内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置选项内容
     *
     * @param content 选项内容
     */
    public void setContent(String content) {
        this.content = content;
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