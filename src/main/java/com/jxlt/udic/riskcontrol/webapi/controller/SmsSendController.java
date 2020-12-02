//package com.jxlt.udic.riskcontrol.webapi.controller;
//
//import com.jxlt.udic.riskcontrol.website.util.MacInfoUtil;
//import com.jxlt.udic.riskcontrol.website.util.Uuid8Util;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang.StringUtils;
//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Slf4j
//@Api(description = "短信发送接口")
//@RestController
//public class SmsSendController {
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Resource
//    private RestTemplate restTemplate;
//
//    @Resource
//    private CacheManager caffeineCacheManager;
//
//    @Value("${defaultInterParam.tenantId}")
//    private String defaultTenantId;
//
//    private static String SUCCCODE = "2000";
//
//    // 是否启用MAC地址验证
//    private final static boolean IS_MAC_CHECK = true;
//
//    private final static DateTimeFormatter FORMATTER_YYYYMMDDHHMMSS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//    private final static SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmss");
//
//    @ApiOperation(value = "短信发送接口", notes = "短信发送接口")
//    @RequestMapping(value = "/smsSendOut", method = RequestMethod.POST)
//    public String smsSendOut(String jsonreq) {
//        log.info("接收参数：{}", jsonreq);
//        JSONObject object = new JSONObject();
//        log.info(">> callingLogAdd job published!");
//
//        object.put("code", "2000");
//        object.put("msg", "短信发送成功");
//        return object.toJSONString();
//    }
//
//}
