package com.jxlt.udic.riskcontrol.website.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * ProvRiskControl
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 10:24
 **/
@ApiModel(value = "省分廉洁风险防控措施", description = "省分廉洁风险防控措施")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="dic_prov_risk_control")
public class ProvRiskControl implements java.io.Serializable{
    @Id
    private String id; //主键
    @Column(name="TARGETID")
    private Integer targetid; //风险点ID
    @Column(name="REMARK")
    private String remark; //防控措施
    @Column(name="ORDERID")
    private int orderid; //排序值
    @Column(name = "RESPCODE")
    private String respcode;//责任人
    @Column(name = "DEPTCODE")
    private String deptcode;//责任部门
    @Column(name = "ORGCODE")
    private String orgcode;//责任公司
    @Column(name="CREATETIME")
    private Date createtime; //创建时间
    @Column(name="OPERATOR")
    private Integer operator; //操作人
    @Column(name="STATE")
    private Integer state; //状态
}
