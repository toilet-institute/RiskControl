package com.jxlt.udic.riskcontrol.website.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jxlt.udic.riskcontrol.website.model.File;
import com.jxlt.udic.riskcontrol.website.model.TargetFile;
import com.jxlt.udic.riskcontrol.website.service.FileService;
import com.jxlt.udic.riskcontrol.website.service.TargetFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Path;

/**
 * @author: ajax
 * @create: 2020-09-30 16:06
 **/
@Slf4j
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @Autowired
    private FileService fileService;

    @Autowired
    private TargetFileService targetFileService;

    public static final String basePath="/root/zt/files";

    //流程图上传
    //接口：/common/flowsUpload:返回url
    //接口：url:返回base64
    @RequestMapping(value = "/flowsUpload",method = RequestMethod.POST)
    public String flowsUpload(@RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
        JSONObject result = new JSONObject();
        BASE64Encoder base64Encoder =new BASE64Encoder();
        String base64EncoderImg = base64Encoder.encode(file.getBytes());
        String fileId=Long.toString(System.currentTimeMillis());
        File file1=new File(fileId,base64EncoderImg,file.getOriginalFilename(),"/common/flows?fileId="+fileId);
        fileService.insertSelective(file1);
        result.put("status",2000);
        result.put("msg","成功");
        result.put("url", "/common/flows?fileId="+fileId);
        return result.toJSONString();
    }

    //流程图获取
    @RequestMapping(value = "/flows",method = RequestMethod.GET)
    public String getFlows(@RequestParam String targetId){
        TargetFile targetFile=targetFileService.selectByPrimaryKey(targetId);
        File file=fileService.selectByPrimaryKey(targetFile.getFileId());
        return file.getBase64Text();
    }
    //文件下载
    @RequestMapping(value = "/files",method = RequestMethod.GET)
    public String getFiles(@RequestParam String fileId, HttpServletResponse resp) throws IOException {
        File file=fileService.selectByPrimaryKey(fileId);
        String path = file.getVisitUrl();
        java.io.File file1 =new java.io.File(path);
        //控制浏览器下载该文件
        resp.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(file.getExName(),"UTF-8"));
        FileInputStream fileInputStream = new FileInputStream(path);
        //创建输出流
        OutputStream fileOutputStream = resp.getOutputStream();
        //创建缓冲区
        byte []buffer =new byte[1024];
        int len =0;
        while ((len = fileInputStream.read(buffer))>0)
        {
            fileOutputStream.write(buffer,0,len);
        }
        fileInputStream.close();
        fileOutputStream.close();
        return "ok";
    }



    @RequestMapping(value = "/getDomainList")
    public String getDomainList(){
        return "getDomainList";
    }
//    获取集团廉洁风险点列表
    @RequestMapping(value = "/getGroupTargetList")
    public String getGroupTargetList(){
        return "获取集团廉洁风险点列表";
    }

//    获取省分廉洁风险点列表
    @RequestMapping(value = "/getProvTargetList")
    public String getProvTargetList(){
        return "获取省分廉洁风险点列表";
    }

//    获取省分廉洁风险点防控措施列表
    @RequestMapping(value = "/getProvControlList")
    public String getProvControlList(){
        return "getProvControlList";
    }

    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public String filesUpload(@RequestParam("file") MultipartFile uploadFile,HttpSession session) throws IOException {

        java.io.File file = new java.io.File(basePath, uploadFile.getOriginalFilename());
        File file1=new File();
        System.out.println(file.getAbsolutePath());
        file1.setExName(uploadFile.getOriginalFilename());
        String fileId=Long.toString(System.currentTimeMillis());
        file1.setFileId(fileId);
        file1.setVisitUrl(file.getAbsolutePath());
        uploadFile.transferTo(file);
        fileService.insertSelective(file1);
        JSONObject result = new JSONObject();
        result.put("status",2000);
        result.put("msg","成功");
        result.put("url", "/common/files?fileId="+fileId);
        return result.toJSONString();
    }
}
