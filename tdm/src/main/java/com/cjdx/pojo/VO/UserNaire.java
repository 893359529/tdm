package com.cjdx.pojo.VO;

public class UserNaire {
    //问卷与填写者关联实体

    private Integer id;
    private Integer naireId;
    private Integer operatorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNaireId() {
        return naireId;
    }

    public void setNaireId(Integer naireId) {
        this.naireId = naireId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
