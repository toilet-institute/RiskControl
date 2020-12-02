package com.jxlt.udic.riskcontrol.website.persistance.mapper;

import com.jxlt.udic.riskcontrol.website.model.GroupRiskTarget;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;

import java.util.List;
import java.util.Map;

/**
 * GroupRiskTargetMapper
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:12
 **/
public interface GroupRiskTargetMapper extends CommonMapper<GroupRiskTarget> {
    //sql分页查询
     List<GroupRiskTarget> queryGroupRiskTargetBySql(Map<String,Object> data);
}
