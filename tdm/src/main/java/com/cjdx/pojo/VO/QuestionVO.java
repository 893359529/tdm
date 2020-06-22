package com.cjdx.pojo.VO;

import com.cjdx.pojo.QueOption;
import com.cjdx.pojo.Question;

import java.util.List;

public class QuestionVO {
    private String title;
    private String type;
    private List<QueOption> queOptionList;

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

    public List<QueOption> getQueOptions() {
        return queOptionList;
    }

    public void setQueOptions(List<QueOption> queOptionList) {
        this.queOptionList = queOptionList;
    }
}
