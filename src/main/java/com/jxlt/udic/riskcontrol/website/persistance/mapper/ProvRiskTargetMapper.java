package com.jxlt.udic.riskcontrol.website.persistance.mapper;

import com.jxlt.udic.riskcontrol.website.model.ProvRiskTarget;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskStatic;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskTo;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;

import java.util.List;
import java.util.Map;

/**
 * ProvRiskTargetMapper
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:11
 **/
public interface ProvRiskTargetMapper extends CommonMapper<ProvRiskTarget> {
     List<ProvRiskTo> queryProvRiskTargetBySql(Map<String,Object> data);
     List<ProvRiskTo> queryProvTargetByName(Map<String,Object> data);
     List<ProvRiskTo> queryProvRiskTargetAllBySql(Map<String,Object> data);
     ProvRiskStatic queryProvRiskTargetCountBySql(Map<String,Object> data);
     int deleteProvTargetById(Map<String,Object> data);
}
