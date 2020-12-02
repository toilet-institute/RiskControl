package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.GroupRiskControl;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.GroupRiskControlMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * GroupRiskControlService
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:16
 **/
@Service
@Slf4j
public class GroupRiskControlService extends AbstractCommonService<GroupRiskControl> {

    @Resource
    private GroupRiskControlMapper groupRiskControlMapper;

    @Override
    public CommonMapper<GroupRiskControl> getMapper() {
        return this.groupRiskControlMapper;
    }

    public List<GroupRiskControl> getGRCS(String operator,String state){
       return groupRiskControlMapper.queryListByOpe(operator,state);
    }
    public List<GroupRiskControl> queryGroupRiskControlBySql(int currPage, int pageSize){
        Map<String, Object> data = new HashedMap();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return groupRiskControlMapper.queryGroupRiskControlBySql(data);
    }
}
