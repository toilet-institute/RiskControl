package com.jxlt.udic.riskcontrol.website.model;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户表
 * @author Administrator
 *
 */
@ApiModel(value = "系统用户", description = "系统用户信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="sys_staff")
public class SysStaff implements java.io.Serializable{

	@Id
	private String id; //主键
    @Column(name="USERCODE")
    private String usercode; //用户ID
    @Column(name="DEPTCODE")
    private String deptcode; //部门
    @Column(name="ORGCODE")
    private String orgcode; //公司
    @Column(name="ROLEID")
    private String roleid; //角色ID
    @Column(name="ROLELEVEL")
    private String rolelevel; //角色等级
    @Column(name="USERNAME")
    private String username; //姓名
    @Column(name="ISDUTY")
    private String isduty; //主岗
    @Column(name="ORDERID")
    private String orderid; //排序
    @Column(name="CREATETIME")
    private Date createtime; //创建时间
    @Column(name="OPERATOR")
    private Integer operator; //操作人
    @Column(name="STATE")
    private Integer state; //状态
   
	public SysStaff(){
	}

	public SysStaff(String id){
		this.id = id;
	}

}
