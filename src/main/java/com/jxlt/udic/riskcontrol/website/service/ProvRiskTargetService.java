package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.ProvRiskTarget;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskStatic;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskTo;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.ProvRiskTargetMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ProvRiskTargetService
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:17
 **/
@Service
@Slf4j
public class ProvRiskTargetService extends AbstractCommonService<ProvRiskTarget> {

    @Resource
    private ProvRiskTargetMapper provRiskTargetMapper;

    @Override
    public CommonMapper<ProvRiskTarget> getMapper() {
        return this.provRiskTargetMapper;
    }

    public List<ProvRiskTo> queryProvTargetByName(String target){
        Map<String, Object> data = new HashedMap();
        data.put("targets",target);
        return provRiskTargetMapper.queryProvTargetByName(data);
    }
    public List<ProvRiskTo>  queryProvTargetByState(int state){
        Map<String, Object> data = new HashedMap();
        data.put("targets",state);
        return provRiskTargetMapper.queryProvTargetByState(data);
    }
    public List<ProvRiskTo> queryProvRiskTargetBySql(int currPage, int pageSize, List<String> deptCodes, List<String> orgCodes, String sort){
        Map<String, Object> data = new HashedMap();
        if(!deptCodes.isEmpty()&&!orgCodes.isEmpty()){
            data.put("currPage", (currPage-1)*pageSize);
            data.put("pageSize", pageSize);
            data.put("deptCodes", deptCodes);
            data.put("orgCodes", orgCodes);
            return provRiskTargetMapper.queryProvRiskTargetBySql(data);
        }else {
            data.put("currPage", (currPage-1)*pageSize);
            data.put("pageSize", pageSize);
            return provRiskTargetMapper.queryProvRiskTargetAllBySql(data);
        }

    }

    public ProvRiskStatic queryProvRiskTargetCountBySql(String domainId, String orgCode, String deptCode){

        Map<String, Object> data = new HashedMap();
        data.put("domainId", domainId);
        data.put("orgCode", orgCode);
        data.put("deptCode", deptCode);
        return provRiskTargetMapper.queryProvRiskTargetCountBySql(data);
    }
    public int deleteProvTargetById(List<String> Ids){
        Map<String, Object> data = new HashedMap();
        data.put("Ids",Ids);
        return provRiskTargetMapper.deleteProvTargetById(data);
    }
    public int addProvTarget(int domainid,String items,String flows,String links,String targets,String type,String rspcode,String deptcode,String orgcode,int operator,int state,int isauto){
        ProvRiskTarget provRiskTarget=new ProvRiskTarget();
        provRiskTarget.setDomainid(domainid);
        provRiskTarget.setFlows(flows);
        provRiskTarget.setItems(items);
        provRiskTarget.setLinks(links);
        provRiskTarget.setTargets(targets);
        provRiskTarget.setType(type);
        provRiskTarget.setRespcode(rspcode);
        provRiskTarget.setDeptcode(deptcode);
        provRiskTarget.setOrgcode(orgcode);
        provRiskTarget.setState(state);
        provRiskTarget.setOperator(1);
        provRiskTarget.setIsauto(isauto);
        provRiskTargetMapper.addProvTarget(provRiskTarget);
        int num=provRiskTarget.getId();
        return num;
    }

}

