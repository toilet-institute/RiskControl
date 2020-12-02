package com.jxlt.udic.riskcontrol.website.model.input;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Id;

/**
 * 用户表
 *
 * @author Administrator
 */
@ApiModel(value = "系统用户", description = "系统用户信息")
@Data
public class SysStaffInputVO implements java.io.Serializable {

    @Id
    private String id; //主键
    private String usercode; //用户ID
    private String deptcode;//所属部门
    private String orgcode; //所属部公司
    private String roleid; //角色ID
    private String rolelevel; //角色等级
    private String username; //姓名
    private String isduty; //主岗
    private String orderid; //排序

}
