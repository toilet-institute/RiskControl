package com.jxlt.udic.riskcontrol.website.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * GroupRiskControl
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 10:23
 **/
@ApiModel(value = "集团廉洁风险防控指引", description = "集团廉洁风险防控指引")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="dic_group_risk_control")
public class GroupRiskControl implements java.io.Serializable{
    @Id
    private Integer id; //主键
    @Column(name="TARGETID")
    private Integer targetid; //风险点ID
    @Column(name="REMARK")
    private String remark; //防控措施
    @Column(name="ORDERID")
    private Integer orderid; //排序值
    @Column(name="CREATETIME")
    private Date createtime; //创建时间
    @Column(name="OPERATOR")
    private String operator; //操作人
    @Column(name="STATE")
    private Integer state; //状态

    public GroupRiskControl(){
        this.id=1;
        this.targetid=1;
        this.remark="!";
        this.orderid=1;
        this.createtime=new Date();
        this.operator="ajax";
        this.state=1;
    }
}
