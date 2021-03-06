package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.ProvRiskControl;
import com.jxlt.udic.riskcontrol.website.model.ProvRiskTarget;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.ProvRiskControlMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ProvRiskControlService
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:17
 **/
@Service
@Slf4j
public class ProvRiskControlService extends AbstractCommonService<ProvRiskControl> {

    @Resource
    private ProvRiskControlMapper provRiskControlMapper;

    @Override
    public CommonMapper<ProvRiskControl> getMapper() {
        return this.provRiskControlMapper;
    }

    public List<ProvRiskControl>  queryProvRiskControlByTarget(Integer targetId){
        Map<String, Object> data = new HashedMap();
        data.put("targetId",targetId);
        return provRiskControlMapper.queryProvRiskControlByTarget(data);

    }

}
