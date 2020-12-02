package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.GroupRiskTarget;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.GroupRiskTargetMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * GroupRiskTargetService
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:16
 **/
@Service
@Slf4j
public class GroupRiskTargetService extends AbstractCommonService<GroupRiskTarget> {

    @Resource
    private GroupRiskTargetMapper groupRiskTargetMapper;

    @Override
    public CommonMapper<GroupRiskTarget> getMapper() {
        return this.groupRiskTargetMapper;
    }

    public List<GroupRiskTarget> queryGroupRiskTargetBySql(int currPage, int pageSize){
        Map<String, Object> data = new HashedMap();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return groupRiskTargetMapper.queryGroupRiskTargetBySql(data);
    }
}
