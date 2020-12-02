package com.jxlt.udic.riskcontrol.swagger2.service;

import com.jxlt.udic.riskcontrol.swagger2.dto.Table;

import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/1/12.
 * author:
 */
public interface WordService {
    // http://localhost:8044/udic/v2/api-docs?group=website
    List<Table> tableList(String groupName);
}
