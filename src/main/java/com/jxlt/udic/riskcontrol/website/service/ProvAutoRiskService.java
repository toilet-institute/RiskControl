package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.ProvAutoRisk;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.ProvAutoRiskMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ProvAutoRiskService
 *
 * @author zhout26
 * @version 1.0
 * 2020/12/5 9:13
 **/
@Service
@Slf4j
public class ProvAutoRiskService extends AbstractCommonService<ProvAutoRisk> {

    @Resource
    private ProvAutoRiskMapper provAutoRiskMapper;

    @Override
    public CommonMapper<ProvAutoRisk> getMapper() {
        return this.provAutoRiskMapper;
    }

    public ProvAutoRisk queryProvAutoRiskBySt(Integer targetId,String stat_date){
        Map<String, Object> data = new HashedMap();
        data.put("targetId",targetId);
        data.put("stat_date",stat_date);
        return provAutoRiskMapper.queryProvAutoRiskBySt(data);
    }
}
