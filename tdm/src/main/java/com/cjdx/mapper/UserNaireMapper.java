package com.cjdx.mapper;

import com.cjdx.my.mapper.MyMapper;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserNaireMapper extends MyMapper<UserInfo> {
    List<QuestionNaire> queryMyNaireListByOperatorId(@Param("operatorId") Integer operatorId);
}