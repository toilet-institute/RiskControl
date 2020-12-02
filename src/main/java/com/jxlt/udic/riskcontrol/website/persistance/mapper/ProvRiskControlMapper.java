package com.jxlt.udic.riskcontrol.website.persistance.mapper;

import com.jxlt.udic.riskcontrol.website.model.ProvRiskControl;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;

import java.util.List;
import java.util.Map;

/**
 * ProvRiskControlMapper
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:11
 **/
public interface ProvRiskControlMapper extends CommonMapper<ProvRiskControl> {
    List<ProvRiskControl> queryProvRiskControlByTarget(Map<String,Object> data);

}
