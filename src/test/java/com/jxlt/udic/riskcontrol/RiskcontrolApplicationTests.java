package com.jxlt.udic.riskcontrol;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxlt.udic.riskcontrol.website.model.*;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskStatic;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskTo;
import com.jxlt.udic.riskcontrol.website.service.*;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class RiskcontrolApplicationTests {
    @Autowired
    SysStaffService sysStaffService;
    @Autowired
    ImportDomainService importDomainService;
    @Autowired
    GroupRiskControlService groupRiskControlService;
    @Autowired
    GroupRiskTargetService groupRiskTargetService;

    @Autowired
    ProvRiskTargetService provRiskTargetService;

    @Autowired
    ProvRiskControlService provRiskControlService;

    @Autowired
    ProvAutoRiskService provAutoRiskService;

    @Test
    void autoRisk(){
        ProvAutoRisk provAutoRisk=new ProvAutoRisk(1,1,"2","cb","202010");
        provAutoRiskService.insertSelective(provAutoRisk);
    }

    public JSONObject Warning(JSONObject dataValue,Double rate){
        JSONObject result=new JSONObject();
        for (Map.Entry entry : dataValue.entrySet()){
            String[] ls=entry.getValue().toString().split("\\|");
            Double d=Double.parseDouble(ls[2]);
            if(d>rate){
                result.put(entry.getKey().toString(),"是");
            }else{
                result.put(entry.getKey().toString(),"否");
            }
        }
        return result;
    }
    @Test
    void queryProvAutoRiskBySt(){
        JSONObject result=new JSONObject();
        ProvAutoRisk provAutoRisk= provAutoRiskService.queryProvAutoRiskBySt(12,"202009");
        JSONObject value=JSON.parseObject(provAutoRisk.getDataValue());
        for (Map.Entry entry : value.entrySet()){
            result.put(entry.getKey().toString(),Warning(JSON.parseObject(entry.getValue().toString()),0.32));
        }
//        for (Map.Entry entry : value.entrySet()) {
//            String[] ls=entry.getValue().toString().split("\\|");
//            System.out.println(Arrays.toString(ls));
//            Double d=Double.parseDouble(ls[2]);
//            if(d>0.32){
//                result.put(entry.getKey().toString(),"是");
//            }else{
//                result.put(entry.getKey().toString(),"否");
//            }
//        }
        System.out.println(result.toJSONString());
    }


    @Test
    void getStaff(){
//        SysStaff sysStaff= sysStaffService.getUserByUserName("1");
//        System.out.println(sysStaff.toString());
        //ProvRiskStatic provRiskStatic = provRiskTargetService.queryProvRiskTargetCountBySql("1","1","1");
        List<String> orGs= Arrays.asList(new String[]{"1", "2"});
        List<String> dePs= Arrays.asList(new String[]{"1", "2"});
        List<ProvRiskTo> provRiskTos= provRiskTargetService.queryProvRiskTargetBySql(1,1,dePs,orGs,"1");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTos));
        //String str=JSON.toJSONString(provRiskStatic);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("array",array);
//        result.put("data",str);
        System.out.println(result.toJSONString());
    }

    @Test
    void queryProvRiskControlByTarget(){
        List<ProvRiskControl> provRiskControls=provRiskControlService.queryProvRiskControlByTarget(1);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskControls));
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("array",array);
        System.out.println(result.toJSONString());
    }


    @Test
    void delete() throws Exception {
        List<String> Ids= Arrays.asList(new String[]{"43", "44","45"});
        int i=provRiskTargetService.deleteProvTargetById(Ids);
        System.out.println(i);
    }

    @Test
    void queryProvRiskTargetBySql(){
        List<ProvRiskTo> provRiskTos= provRiskTargetService.queryProvTargetByName("建设");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTos));
        //String str=JSON.toJSONString(provRiskStatic);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("array",array);
//        result.put("data",str);
        System.out.println(result.toJSONString());
    }

//    @Test
//    void addProvTarget(){
//        int tar=provRiskTargetService.addProvTarget(11,"123","123","123","123","123","1231","123","123",1,1);
//        System.out.println("---------------"+tar+"---------------------");
//    }
    @Test
    void queryProvTargetByState(){
        List<ProvRiskTo> provRiskTos= provRiskTargetService.queryProvTargetByState(1);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTos));
        //String str=JSON.toJSONString(provRiskStatic);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("array",array);
//        result.put("data",str);
        System.out.println(result.toJSONString());
    }

    @Test
    void insert(){
        List<GroupRiskControl> groupRiskControls =groupRiskControlService.getGRCS("1","1");
        for (GroupRiskControl g:groupRiskControls) {
            System.out.println(g.toString());
        }
    }
    @Test
    void json(){
        List<ImportDomain> importDomains= importDomainService.selectAll();

        JSONArray array= JSONArray.parseArray(JSON.toJSONString(importDomains));
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("data",array);
        System.out.println(result.toJSONString());
    }

    @Test
    void queryBySql(){
        List<ImportDomain> importDomains =importDomainService.queryImportDomainBySql(2,2);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(importDomains));
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("data",array);
        System.out.println(result.toJSONString());
    }

    @Test
    void insertOrUpdateImportDomain() throws Exception {

        int target=importDomainService.insertSelective(new ImportDomain("insert","没有",1,new Date(),"1",1));
        System.out.println(target);
    }
    @Test
    void deletebyId() throws Exception {
        int target =importDomainService.deleteByPrimaryKey("23");
        System.out.println(target);
    }

    @Test
    void grtSelectAll(){
        JSONObject result=new JSONObject();
        List<GroupRiskTarget> groupRiskTargets= groupRiskTargetService.selectAll();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(groupRiskTargets));
        result.put("status",2000);
        result.put("msg","成功");
        result.put("data",array);
        System.out.println(result.toJSONString());
    }

    @Test
    void grcSelectBySql(){
        JSONObject result=new JSONObject();
        List<GroupRiskControl> groupRiskControls= groupRiskControlService.queryGroupRiskControlBySql(2,2);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(groupRiskControls));
        result.put("status",2000);
        result.put("msg","成功");
        result.put("data",array);
        System.out.println(result.toJSONString());
    }
//    @Test
//    void grtSelect(){
//        JSONObject result=new JSONObject();
//        List<ProvRiskTarget> provRiskTargets= provRiskTargetService.queryProvRiskTargetBySql(2,2,"1","1","1");
//        JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTargets));
//        result.put("status",2000);
//        result.put("msg","成功");
//        result.put("data",array);
//        System.out.println(result.toJSONString());
//    }
}
