package com.cjdx.mapper;

import com.cjdx.my.mapper.MyMapper;
import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.VO.NaireVO;
import com.cjdx.pojo.VO.QuestionAndAnswerVO;
import com.cjdx.pojo.VO.QuestionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionNaireMapper extends MyMapper<QuestionNaire> {

    List<QuestionNaire> queryAllNaire(Integer userId);

    List<QuestionNaire> queryNaireByTitle(String title);

    List<QuestionVO> queryNaireContentById(Integer naireId);

    List<QuestionAndAnswerVO> queryQuestionAndAnswer(@Param("naireId")Integer naireId,
                                                  @Param("userId")Integer userId);

    void updatePublishStateToYes(@Param("naireId")Integer naireId);

    void updatePublishStateToNo(@Param("naireId")Integer naireId);

    List<QuestionNaire> queryMyPushedNaire(@Param("userId")Integer userId);

    List<QuestionNaire> queryMyNotPushNaire(@Param("userId")Integer userId);

    List<QuestionNaire> queryMyNaire(@Param("userId")Integer userId);

}