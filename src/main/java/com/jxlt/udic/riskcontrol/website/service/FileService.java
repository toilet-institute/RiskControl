package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.File;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.FileMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * FileService
 *
 * @author zhout26
 * @version 1.0
 * 2020/11/16 16:23
 **/
@Service
@Slf4j
public class FileService extends AbstractCommonService<File> {

    @Resource
    private FileMapper fileMapper;

    @Override
    public CommonMapper<File> getMapper() {
        return this.fileMapper;
    }
}
