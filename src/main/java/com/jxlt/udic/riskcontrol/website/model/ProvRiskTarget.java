package com.jxlt.udic.riskcontrol.website.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * ProvRIskTarget
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 10:33
 **/
@ApiModel(value = "省分廉洁风险点", description = "省分廉洁风险点")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="dic_prov_risk_target")
public class ProvRiskTarget implements java.io.Serializable{
        @Id
        @JSONField(name = "id")
        private int id; //主键
        @Column(name="DOMAINID")
        @JSONField(name = "domainid")
        private int domainid; //风险点ID
        @Column(name="ITEMS")
        @JSONField(name = "items")
        private String items; //业务事项
        @Column(name = "FLOWS")
        @JSONField(name = "flows")
        private String flows;//流程示意图
        @Column(name = "LINKS")
        @JSONField(name = "links")
        private String links;//环节
        @Column(name = "TARGETS")
        @JSONField(name = "targets")
        private String targets;//廉洁风险点
        @Column(name = "TYPE")
        @JSONField(name = "type")
        private String type;//风险等级
        @Column(name = "RESPCODE")
        @JSONField(name = "respcode")
        private String respcode;//责任人
        @Column(name = "DEPTCODE")
        @JSONField(name = "deptcode")
        private String deptcode;//责任部门
        @Column(name = "ORGCODE")
        @JSONField(name = "orgcode")
        private String orgcode;//责任公司
        @Column(name="CREATETIME")
        private Date createtime; //创建时间
        @Column(name="OPERATOR")
        private Integer operator; //操作人
        @Column(name="STATE")
        private Integer state; //状态
}
