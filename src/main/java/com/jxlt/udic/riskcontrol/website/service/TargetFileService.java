package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.TargetFile;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.TargetFileMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TargetFileService
 *
 * @author zhout26
 * @version 1.0
 * 2020/11/20 15:58
 **/
@Service
@Slf4j
public class TargetFileService extends AbstractCommonService<TargetFile> {

    @Resource
    private TargetFileMapper targetFileMapper;
    @Override
    public CommonMapper<TargetFile> getMapper() {
        return this.targetFileMapper;
    }
}
