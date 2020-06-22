package com.cjdx.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Question {
    /**
     * 题目ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 问卷ID
     */
    @Column(name = "naire_id")
    private Integer naireId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题类型
     */
    private Integer type;

    /**
     * 获取题目ID
     *
     * @return id - 题目ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置题目ID
     *
     * @param id 题目ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取问卷ID
     *
     * @return narie_id - 问卷ID
     */
    public Integer getNaireId() {
        return naireId;
    }

    /**
     * 设置问卷ID
     *
     * @param naireId 问卷ID
     */
    public void setNarieId(Integer naireId) {
        this.naireId = naireId;
    }

    /**
     * 获取问题标题
     *
     * @return title - 问题标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置问题标题
     *
     * @param title 问题标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取问题类型
     *
     * @return type - 问题类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置问题类型
     *
     * @param type 问题类型
     */
    public void setType(Integer type) {
        this.type = type;
    }
}