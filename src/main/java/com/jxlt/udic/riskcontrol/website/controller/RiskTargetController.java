package com.jxlt.udic.riskcontrol.website.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxlt.udic.riskcontrol.website.model.*;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskStatic;
import com.jxlt.udic.riskcontrol.website.model.datachange.ProvRiskTo;
import com.jxlt.udic.riskcontrol.website.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.*;

/**
 * @author: ajax
 * @create: 2020-09-30 15:56
 **/
@Slf4j
@RestController
@RequestMapping(value = "/riskTarget")
@Api(value = "省分风险防控")
public class RiskTargetController {

    @Autowired
    private GroupRiskControlService groupRiskControlService;

    @Autowired
    private GroupRiskTargetService groupRiskTargetService;

    @Autowired
    private ImportDomainService importDomainService;

    @Autowired
    private ProvRiskControlService provRiskControlService;

    @Autowired
    private ProvRiskTargetService provRiskTargetService;

    @Autowired
    private  FileService fileService;

    @Autowired
    private  TargetFileService targetFileService;

    public static final String basePath="/root/zt/files";

/*-----------------------------------------------------------重点领域管理---------------------------------------------*/

//    获取集团重点领域信息
    @RequestMapping(value = "/getDomainList",method = RequestMethod.GET)
    public String getDomainList(@RequestParam String sort){
        JSONObject result = new JSONObject();
        try{
            List<ImportDomain> importDomains= importDomainService.selectAll();
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(importDomains));
            result.put("status",2000);
            result.put("msg","成功");
            result.put("data",array);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","失败");
            return result.toJSONString();
        }

    }

//    集团重点领域信息查询
    @RequestMapping(value = "/queryDomainList",method = RequestMethod.GET)
    public String queryDomainList(@RequestParam int pageNum,@RequestParam int pageSize,@RequestParam String data){
        List<ImportDomain> importDomains =importDomainService.queryImportDomainBySql(pageNum,pageSize);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(importDomains));
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("data",array);
        return result.toJSONString();
    }

//    集团重点领域新增
    @RequestMapping(value = "/addDomain",method = RequestMethod.POST)
    public String addDomain(@RequestParam String domain,@RequestParam String remark,@RequestParam int orderId,@RequestParam int state){
        ImportDomain importDomain=new ImportDomain(domain,remark,orderId,new Date(),"1",state);
        importDomainService.insertSelective(importDomain);
        JSONObject  result = new JSONObject();
        result.put("status",2000);
        result.put("msg","插入成功");
        return result.toJSONString();
    }

//    集团重点领域信息修改
    @RequestMapping(value = "/modDomain",method = RequestMethod.POST)
    public String modDomain(@RequestParam Integer id, @RequestParam String domain, @RequestParam String remark, @RequestParam int orderId, @RequestParam int state) {
        JSONObject  result = new JSONObject();
        try {
            importDomainService.updateByPrimaryKey(new ImportDomain(id,domain,remark,orderId,new Date(),"1",state));
            result.put("status",2000);
            result.put("msg","修改成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }


    }

//    集团重点领域信息删除（批量）
    @RequestMapping(value = "dropDomain",method = RequestMethod.POST)
    public String dropDomain(@RequestParam String id){
        JSONObject  result = new JSONObject();
        try {
            int target=importDomainService.deleteByPrimaryKey(id);
            result.put("status",2000);
            result.put("msg","删除成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }
    }

//    集团重点领域文件上传
    @RequestMapping(value = "uploadDomainFile")
    public String uploadDomainFile(){
        return "uploadDomainFile";
    }

    /*-------------------------------------------------集团指引手册---------------------------------------------------------*/




//    获取集团廉洁风险点
    @RequestMapping(value = "/getGroupTargetList",method = RequestMethod.POST)
    public String getGroupTargetList(@RequestParam String sort){
        JSONObject result = new JSONObject();
        try{
            List<GroupRiskTarget> groupRiskTargets= groupRiskTargetService.selectAll();
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(groupRiskTargets));
            result.put("status",2000);
            result.put("msg","成功");
            result.put("data",array);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","失败");
            return result.toJSONString();
        }
    }

//    集团廉洁风险点防控措施指引信息查询
    @RequestMapping(value = "/queryGroupControlList",method = RequestMethod.POST)
    public String queryGroupControlList(@RequestParam int pageNum,@RequestParam int pageSize,@RequestParam JSON data){
        List<GroupRiskControl> groupRiskControls =groupRiskControlService.queryGroupRiskControlBySql(pageNum,pageSize);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(groupRiskControls));
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("data",array);
        return result.toJSONString();
    }
    // 集团廉洁风险点新增
    @RequestMapping(value = "/addGroupTarget")
    public String addGroupTarget(@RequestParam int domainId,@RequestParam String items,@RequestParam String flows,@RequestParam String links,@RequestParam String targets,@RequestParam String type,@RequestParam String ranges,@RequestParam String respDeptCode,@RequestParam int state){
        JSONObject result = new JSONObject();
        try{
            GroupRiskTarget groupRiskTarget=new GroupRiskTarget(domainId,items,flows,links,targets,type,ranges,respDeptCode,new Date(),1,1);
            groupRiskTargetService.insertSelective(groupRiskTarget);
            result.put("status",2000);
            result.put("msg","新增成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","新增失败");
            return result.toJSONString();
        }
    }
//    集团廉洁风险点信息修改
    @RequestMapping(value = "/modiGroupTarget",method = RequestMethod.PUT)
    public String modiGroupTarget(@RequestParam int id,@RequestParam int domainId,@RequestParam String items,@RequestParam String flows,@RequestParam String links,@RequestParam String targets,@RequestParam String type,@RequestParam String ranges,@RequestParam String respDeptCode,@RequestParam int state){
        GroupRiskTarget groupRiskTarget=new GroupRiskTarget(id,domainId,items,flows,links,targets,type,ranges,respDeptCode,new Date(),1,1);
        JSONObject result=new JSONObject();
        try{
            int target=groupRiskTargetService.updateByPrimaryKey(groupRiskTarget);
            result.put("status",2000);
            result.put("msg","更新成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","更新失败");
            return result.toJSONString();
        }
    }
//    集团廉洁风险点信息删除（批量）
    @RequestMapping(value = "/dropGroupTarget",method = RequestMethod.DELETE)
    public String dropGroupTarget(@RequestParam String id){
        JSONObject  result = new JSONObject();
        try {
            int target=groupRiskTargetService.deleteByPrimaryKey(id);
            result.put("status",2000);
            result.put("msg","删除成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }
    }

//    集团廉洁风险点文件上传
    @RequestMapping(value = "/uploadGroupFile")
    public String uploadGroupFile(){
        return "uploadGroupFile";
    }

//    廉洁风险点流程图上传
    @RequestMapping(value = "uploadFlowImage",method = RequestMethod.POST)
    public String uploadFlowImage(@RequestParam("file") MultipartFile uploadFile,@RequestParam String target_id) throws IOException {
        BASE64Encoder base64Encoder =new BASE64Encoder();
        String base64EncoderImg = base64Encoder.encode(uploadFile.getBytes());
        String fileId=Long.toString(System.currentTimeMillis());
        File file1=new File(fileId,base64EncoderImg,uploadFile.getOriginalFilename(),"/common/flows?targetId="+target_id);
        TargetFile targetFile=new TargetFile(target_id,fileId,1);
        targetFileService.insertSelective(targetFile);
        fileService.insertSelective(file1);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("url", file1.getVisitUrl());
        return result.toJSONString();
    }

//    集团廉洁风险点防控措施新增
    @RequestMapping(value = "/addGroupControl",method = RequestMethod.POST)
    public String addGroupControl(@RequestParam int targetId,@RequestParam String remark,@RequestParam int orderId,@RequestParam int state){
        JSONObject result=new JSONObject();
        try{
            GroupRiskControl groupRiskControl=new GroupRiskControl();
            groupRiskControl.setTargetid(targetId);
            groupRiskControl.setRemark(remark);
            groupRiskControl.setOrderid(orderId);
            groupRiskControl.setState(state);
            groupRiskControlService.insertSelective(groupRiskControl);
            result.put("status",2000);
            result.put("msg","删除成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }

    }
//    集团廉洁风险点防控措施信息修改
    @RequestMapping(value = "/modGroupControl",method = RequestMethod.PUT)
    public String modGroupControl(@RequestParam int id,@RequestParam int targetId,@RequestParam String remark,@RequestParam int orderId,@RequestParam int state){
        JSONObject result=new JSONObject();
        try{
            GroupRiskControl groupRiskControl=new GroupRiskControl();
            groupRiskControl.setId(id);
            groupRiskControl.setTargetid(targetId);
            groupRiskControl.setRemark(remark);
            groupRiskControl.setOrderid(orderId);
            groupRiskControl.setState(state);
            groupRiskControlService.updateByPrimaryKey(groupRiskControl);
            result.put("status",2000);
            result.put("msg","删除成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }
    }
//    集团廉洁风险点防控措施信息删除（批量）
    @RequestMapping(value = "/dropGroupControl",method = RequestMethod.DELETE)
    public String dropGroupControl(@RequestParam String id){
        JSONObject  result = new JSONObject();
        try {
            int target=groupRiskControlService.deleteByPrimaryKey(id);
            result.put("status",2000);
            result.put("msg","删除成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }
    }
    /*-------------------------------------------------省分风险防控-----------------------------------------------------*/
    @RequestMapping(value = "/queryProvTargetByName",method = RequestMethod.POST)
    public String queryProvTargetByName(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        try{
            String target = data.getString("target");
            List<ProvRiskTo> provRiskTargets=provRiskTargetService.queryProvTargetByName(target);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTargets));
            result.put("status",2000);
            result.put("msg","成功");
            result.put("total",provRiskTargets.size());
            result.put("list",array);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","字段解析出错");
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/queryProvTargetByState",method = RequestMethod.POST)
    public String queryProvTargetByState(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        try{
            int state = data.getInteger("state");
            List<ProvRiskTo> provRiskTargets=provRiskTargetService.queryProvTargetByState(state);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTargets));
            result.put("status",2000);
            result.put("msg","成功");
            result.put("total",provRiskTargets.size());
            result.put("list",array);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","字段解析出错");
            return result.toJSONString();
        }
    }

    // 根据风险点类型（自动，手动）筛选数据
    @RequestMapping(value = "/queryProvTargetByIsAuto",method = RequestMethod.POST)
    public String queryProvTargetByIsAuto(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        try{
            int isauto = data.getInteger("isauto");
            List<ProvRiskTo> provRiskTargets=provRiskTargetService.queryProvTargetByIsAuto(isauto);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTargets));
            result.put("status",2000);
            result.put("msg","成功");
            result.put("total",provRiskTargets.size());
            result.put("list",array);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","字段解析出错");
            return result.toJSONString();
        }
    }

//    省分廉洁风险点信息查询
    @RequestMapping(value = "/queryProvTarget",method = RequestMethod.POST)
    public String queryProvTarget(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        try{
            int pageNum=data.getInteger("pageNum");
            int pageSize=data.getInteger("pageSize");
            JSONObject currentData= data.getJSONObject("data");
            if(currentData==null){
                currentData=new JSONObject();
            }
            String deptCode=currentData.getString("deptCode");
            JSONArray deptCodes=JSONArray.parseArray(deptCode);
            List<String> deptArray = new ArrayList<>();
            if(deptCodes!=null){
                for (int i=0; i<deptCodes.size(); i++){
                    deptArray.add( deptCodes.getString(i));
                }
            }
            String orgCode=currentData.getString("orgCode");
            JSONArray orgCodes=JSONArray.parseArray(orgCode);
            List<String> orgArray = new ArrayList<>();
            if(orgCodes!=null){
                for (int i=0; i<orgCodes.size(); i++) {
                    orgArray.add( orgCodes.getString(i));
                }
            }
            String sort=currentData.getString("sort");
            List<ProvRiskTo> provRiskTargets=provRiskTargetService.queryProvRiskTargetBySql(pageNum,pageSize,deptArray,orgArray,sort);
            System.out.println("provrisk is"+provRiskTargets);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskTargets));
            result.put("status",2000);
            result.put("msg","成功");
            result.put("total",provRiskTargets.size());
            result.put("all",provRiskTargetService.queryProvRiskTargetBySql(1,100000,deptArray,orgArray,sort).size());
            result.put("list",array);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","字段解析出错");
            return result.toJSONString();
        }
    }

    //统计查询
    @RequestMapping(value = "/getStatic",method = RequestMethod.POST)
    public String queryStatic(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        try{
            String domainId=data.getString("domainId");
            String orgCode=data.getString("orgCode");
            String deptCode= data.getString("deptCode");
            ProvRiskStatic provRiskStatic=provRiskTargetService.queryProvRiskTargetCountBySql(domainId!=null?domainId:"-1",orgCode!=null?orgCode:"-1",deptCode!=null?deptCode:"-1");
            if(domainId!=null){
                result.put("domains",provRiskStatic.getDomains());
            }
            if(orgCode!=null){
                result.put("orgCode",provRiskStatic.getOrGs());
            }
            if(deptCode!=null){
                result.put("deptCode",provRiskStatic.getOrgDs());
            }
            result.put("status",2000);
            result.put("msg","成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","字段解析出错");
            return result.toJSONString();
        }

    }



//    省分廉洁风险点新增
    @RequestMapping(value = "/addProvTarget",method = RequestMethod.POST)
    public String addProvTarget(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        Integer domainId=data.getInteger("domainId");
        String items=data.getString("items");
        String flows=data.getString("flows");
        String links=data.getString("links");
        String targets=data.getString("targets");
        String type=data.getString("type");
        String respCode=data.getString("respCode");
        String deptCode=data.getString("deptCode");
        String orgCode=data.getString("orgCode");
        Integer state= data.getInteger("state");
        Integer isauto =data.getInteger("isauto");
        ProvRiskTarget provRiskTarget=new ProvRiskTarget();
        provRiskTarget.setDomainid(domainId);
        provRiskTarget.setFlows(flows);
        provRiskTarget.setItems(items);
        provRiskTarget.setLinks(links);
        provRiskTarget.setTargets(targets);
        provRiskTarget.setType(type);
        provRiskTarget.setRespcode(respCode);
        provRiskTarget.setDeptcode(deptCode);
        provRiskTarget.setOrgcode(orgCode);
        provRiskTarget.setState(state);
        provRiskTarget.setIsauto(isauto);
        provRiskTarget.setOperator(1);
        int id=provRiskTargetService.addProvTarget(domainId,items,flows,links,targets,type,respCode,deptCode,orgCode,1,state,isauto);
        System.out.println(provRiskTarget.getId());
        result.put("status",2000);
        result.put("id",id);
        result.put("msg","成功");
        return result.toJSONString();
//        try{
//
//        }catch (Exception e){
//            result.put("status",1000);
//            result.put("msg","失败");
//            return result.toJSONString();
//        }
    }
//    省分廉洁风险点信息修改
    @RequestMapping(value = "/modGroupTarget",method = RequestMethod.PUT)
    public String modGroupTarget(@RequestBody JSONObject data){
        JSONObject result = new JSONObject();
        try{
            int id=data.getInteger("id");
            Integer domainId=data.getInteger("domainId");
            String items=data.getString("items");
            String flows=data.getString("flows");
            String links=data.getString("links");
            String targets=data.getString("targets");
            String type=data.getString("type");
            String respCode=data.getString("respCode");
            String deptCode=data.getString("deptCode");
            String orgCode=data.getString("orgCode");
            Integer state= data.getInteger("state");
            Integer isauto =data.getInteger("isauto");
            ProvRiskTarget provRiskTarget=new ProvRiskTarget();
            provRiskTarget.setDomainid(domainId);
            provRiskTarget.setFlows(flows);
            provRiskTarget.setItems(items);
            provRiskTarget.setLinks(links);
            provRiskTarget.setTargets(targets);
            provRiskTarget.setType(type);
            provRiskTarget.setRespcode(respCode);
            provRiskTarget.setDeptcode(deptCode);
            provRiskTarget.setOrgcode(orgCode);
            provRiskTarget.setState(state);
            provRiskTarget.setIsauto(isauto);
            provRiskTarget.setOperator(1);
            provRiskTargetService.updateByPrimaryKey(provRiskTarget);
            result.put("status",2000);
            result.put("msg","成功");
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","修改失败");
            return result.toJSONString();
        }
    }
//    省分廉洁风险点信息删除（批量）
    @RequestMapping(value = "/dropProvTarget",method = RequestMethod.DELETE)
    public String dropProvTarget(@RequestBody JSONObject data){
        JSONObject result=new JSONObject();
        try{
            String ids=data.getString("ids");
            JSONArray Ids=JSONArray.parseArray(ids);
            List<String> IdArray = new ArrayList<>();
            if(Ids!=null){
                for (int i=0; i<Ids.size(); i++) {
                    IdArray.add( Ids.getString(i));
                }
            }
            int count=provRiskTargetService.deleteProvTargetById(IdArray);
            result.put("status",2000);
            result.put("msg","成功");
            result.put("count",count);
            return result.toJSONString();
        }catch (Exception e){
            result.put("status",1000);
            result.put("msg","失败");
            return result.toJSONString();
        }
    }

//    省分廉洁风险点文件上传
@ApiOperation(value="省分廉洁风险点文件上传", notes="上传风险点文件")
@ApiImplicitParams({
        @ApiImplicitParam(paramType="form", name = "targetId", value = "风险点", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType="form", name = "file", value = "风险点文件", required = true, dataType = "MultipartFile"),
})
    @RequestMapping(value = "/uploadProvFile")
    public String uploadProvFile(@RequestParam("file") MultipartFile uploadFile,@RequestParam String target_id) throws IOException {

        java.io.File file = new java.io.File(basePath, uploadFile.getOriginalFilename());
        File file1=new File();
        System.out.println(file.getAbsolutePath());
        file1.setExName(uploadFile.getOriginalFilename());
        String fileId=Long.toString(System.currentTimeMillis());
        file1.setFileId(fileId);
        file1.setVisitUrl(file.getAbsolutePath());
        uploadFile.transferTo(file);
        TargetFile targetFile=new TargetFile(target_id,fileId,2);
        targetFileService.insertSelective(targetFile);
        fileService.insertSelective(file1);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("url", "/common/files?fileId="+fileId);
        return result.toJSONString();
    }

    //    省分廉洁风险点防控措施查询
    @RequestMapping(value = "/queryProvRiskControlByTarget",method = RequestMethod.POST)
    public String queryProvRiskControlByTarget(@RequestBody JSONObject data){
        int targetId=data.getInteger("targetId");
        List<ProvRiskControl> provRiskControls=provRiskControlService.queryProvRiskControlByTarget(targetId);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(provRiskControls));
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("array",array);
        return result.toJSONString();
    }
//    省分廉洁风险点防控措施查询
    @RequestMapping(value = "/queryProvControl",method = RequestMethod.GET)
    public String queryProvControl(@RequestBody JSONObject data){
        int targetId=data.getInteger("targetId");
        return "省分廉洁风险点防控措施查询";
    }
//    省分廉洁风险点防控措施新增
    @RequestMapping(value = "/addProvControl")
    public String addProvControl(@RequestBody JSONObject data){
        int targetId=data.getInteger("targetId");
        JSONArray listData= data.getJSONArray("list");
        List<ProvRiskControl> provRiskControls=new LinkedList<>();
        for(int i=0;i<listData.size();i++){
            JSONObject jsonObject=listData.getJSONObject(i);
            ProvRiskControl provRiskControl=new ProvRiskControl();
            provRiskControl.setTargetid(targetId);
            provRiskControl.setOrderid(jsonObject.getInteger("orderid"));
            provRiskControl.setRemark(jsonObject.getString("remark"));
            provRiskControl.setState(jsonObject.getInteger("state"));
            provRiskControls.add(provRiskControl);
        }
        provRiskControlService.insertList(provRiskControls);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        return result.toJSONString();
    }
//    添加省分廉洁风险点防控措施
    @RequestMapping(value = "/modProvControl",method = RequestMethod.POST)
    public String modProvControl(){

        return "添加省分廉洁风险点防控措施";
    }

//  省分廉洁风险点防控措施信息删除（批量）
    @RequestMapping(value = "/dropProvControl")
    public String dropProvControl(){
        return "省分廉洁风险点防控措施信息删除（批量）";
    }
//   省分廉洁风险点发布审批流程查询
    @RequestMapping(value = "/queryProvTargetPressFlow")
    public String queryProvTargetPressFlow(){
        return "省分廉洁风险点发布审批流程查询";
    }

/*---------------------------------------------------------------发布审批----------------------------------------------------*/
//    审批待办事项查询
    @RequestMapping(value = "/queryProvTargetPressFlowApprove")
    public String queryProvTargetPressFlowApprove(){
        return "审批待办事项查询";
    }

//    审批结果提交
    @RequestMapping(value = "/addProvTargetPressFlow")
    public String addProvTargetPressFlow(){
        return "审批结果提交";
    }

//    获取流程节点标题
    @RequestMapping(value = "/getFlowStepTitle")
    public String getFlowStepTitle(){
        return "获取流程节点标题";
    }

}
