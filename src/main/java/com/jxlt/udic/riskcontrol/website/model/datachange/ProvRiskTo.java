package com.jxlt.udic.riskcontrol.website.model.datachange;

import lombok.Data;

/**
 * ProvRiskTo
 *
 * @author zhout26
 * @version 1.0
 * 2020/11/25 11:10
 **/
@Data
public class ProvRiskTo {
//    a.id,a.domainid,b.remark,a.items,a.flows,a.links,a.targets,a.type,a.respcode,a.deptcode,a.orgcode

    private int id; //主键

    private int domainid; //风险点ID

    private String remark;//重点领域描述

    private String items; //业务事项

    private String flows;//流程示意图

    private String links;//环节

    private String targets;//廉洁风险点

    private String type;//风险等级

    private String respcode;//责任人

    private String deptcode;//责任部门

    private String orgcode;//责任公司

    private int isauto;//是否自动风险点

    private int state;//风险点状态

}
