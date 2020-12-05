package com.jxlt.udic.riskcontrol.website.persistance.mapper;

import com.jxlt.udic.riskcontrol.website.model.ProvAutoRisk;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;

import java.util.List;
import java.util.Map;

/**
 * ProvAutoRiskMapper
 *
 * @author zhout26
 * @version 1.0
 * 2020/12/5 9:08
 **/
public interface ProvAutoRiskMapper extends CommonMapper<ProvAutoRisk> {
    ProvAutoRisk  queryProvAutoRiskBySt(Map<String,Object> data);
}
