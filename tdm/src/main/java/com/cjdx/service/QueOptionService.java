package com.cjdx.service;

import com.cjdx.pojo.QueOption;

import java.util.List;

public interface QueOptionService {
    void addQueOption(Integer queId, List<String> options) throws Exception;

    List<QueOption> queryQueOption(Integer questionId) throws Exception;

    void delQueOption(List<Integer> naireId) throws Exception;
}
