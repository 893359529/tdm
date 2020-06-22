package com.cjdx.service;

import com.cjdx.pojo.QuestionNaire;
import com.cjdx.pojo.VO.NaireAndAnswerVO;
import com.cjdx.pojo.VO.NaireVO;
import com.cjdx.pojo.VO.QuestionVO;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.List;

public interface QuestionNaireService {
    List<QuestionNaire> queryAllNaire(String token) throws Exception;

    List<QuestionNaire> queryNaireByTitle(String title);

    List<QuestionNaire> queryMyNaire(String token) throws Exception;

    List<QuestionNaire> queryMyPushedNaire(String token) throws Exception;

    List<QuestionNaire> queryMyNotPushNaire(String token) throws Exception;

    List<QuestionNaire> queryMyWrittenNaire(String token) throws Exception;

    NaireVO queryNaireContent(Integer naireId);

    NaireAndAnswerVO queryNaireAndAnswer(Integer naireId, String token) throws Exception;

    void delNaireById(Integer naireId) throws Exception;

    Integer addNaireAndSave(String title, String introduce, String type, String token) throws Exception;

    Integer nairePush(Integer naireId) throws Exception;

    Integer nairePull(Integer naireId) throws Exception;
}
