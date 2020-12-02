package com.jxlt.udic.riskcontrol.website.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * GroupRiskTarget
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 10:24
 **/
@ApiModel(value = "集团廉洁风险点", description = "集团廉洁风险点")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="dic_group_risk_target")
public class GroupRiskTarget implements java.io.Serializable{
    @Id
    private int id; //主键
    @Column(name="DOMAINID")
    private int domainid; //风险点ID
    @Column(name="ITEMS")
    private String items; //业务事项
    @Column(name = "FLOWS")
    private String flows;//流程示意图
    @Column(name = "LINKS")
    private String links;//环节
    @Column(name = "TARGETS")
    private String targets;//廉洁风险点
    @Column(name = "TYPE")
    private String type;//风险等级
    public GroupRiskTarget(){
        this.id=1;
        this.domainid=1;
        this.items="!";
        this.flows="1";
        this.links="1";
        this.createtime=new Date();
        this.state=1;
    }
    public GroupRiskTarget(int id, int domainid, String items, String flows, String links, String targets, String type, String ranges, String respdeptcode, Date createtime, int operator, int state) {
        this.id = id;
        this.domainid = domainid;
        this.items = items;
        this.flows = flows;
        this.links = links;
        this.targets = targets;
        this.type = type;
        this.ranges = ranges;
        this.respdeptcode = respdeptcode;
        this.createtime = createtime;
        this.operator = operator;
        this.state = state;
    }

    public GroupRiskTarget(int domainid, String items, String flows, String links, String targets, String type, String ranges, String respdeptcode, Date createtime, int operator, int state) {

        this.domainid = domainid;
        this.items = items;
        this.flows = flows;
        this.links = links;
        this.targets = targets;
        this.type = type;
        this.ranges = ranges;
        this.respdeptcode = respdeptcode;
        this.createtime = createtime;
        this.operator = operator;
        this.state = state;
    }

    @Column(name = "RANGES")
    private String ranges;//适用范围
    @Column(name = "RESPDEPTCODE")
    private String respdeptcode;//责任单位
    @Column(name="CREATETIME")
    private Date createtime; //创建时间
    @Column(name="OPERATOR")
    private Integer operator; //操作人
    @Column(name="STATE")
    private Integer state; //状态
}
