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
 * ImportDomain
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 10:17
 **/
@ApiModel(value = "重点领域", description = "重点领域信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="dic_group_important_domain")
public class ImportDomain implements java.io.Serializable{


    @Id
    @JSONField(name = "id")
    private Integer id; //主键
    @Column(name="DOMAIN")
    @JSONField(name = "domain")
    private String domain; //重点领域名称
    @Column(name="REMARK")
    @JSONField(name = "remark")
    private String remark; //重点领域说明
    @Column(name="ORDERID")
    @JSONField(name = "orderid")
    private Integer orderid; //排序值
    @Column(name="CREATETIME")
    private Date createtime; //创建时间
    @Column(name="OPERATOR")
    private String operator; //操作人
    @Column(name="STATE")
    private Integer state; //状态

    public ImportDomain(){
        this.id=1;
        this.domain="1";
        this.remark="!";
        this.orderid=1;
        this.createtime=new Date();
        this.operator="ajax";
        this.state=1;
    }

    public ImportDomain(Integer id, String domain, String remark, Integer orderid, Date createtime, String operator, int state) {
        super();
        this.id = id;
        this.domain = domain;
        this.remark = remark;
        this.orderid = orderid;
        this.createtime = createtime;
        this.operator = operator;
        this.state = state;
    }

    public ImportDomain(String domain, String remark, int orderid, Date createtime, String operator, int state) {
        this.domain = domain;
        this.remark = remark;
        this.orderid = orderid;
        this.createtime = createtime;
        this.operator = operator;
        this.state = state;
    }
}
