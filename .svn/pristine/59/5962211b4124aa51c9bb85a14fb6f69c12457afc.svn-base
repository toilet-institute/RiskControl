package com.jxlt.udic.riskcontrol.website.config;

import com.jxlt.udic.riskcontrol.website.util.RequestHolder;
import com.jxlt.udic.riskcontrol.website.util.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 静态工具类的动态配置
 *
 * @author
 * @create 2018-11-19 11:02
 **/
@Slf4j
@Configuration
public class StaticConfig {

    @Value("${use-temp-session-user}")
    private boolean useTempSessionUser;

    @PostConstruct
    private void initConfig() {
        //设置session中是否是用临时用户
        SessionContent.setUseTempSessionUser(useTempSessionUser);
        RequestHolder.setUseTempSessionUser(useTempSessionUser);
    }
}
