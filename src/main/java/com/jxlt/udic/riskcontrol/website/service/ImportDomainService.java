package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.ImportDomain;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.ImportDomainMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ImportDomainService
 *
 * @author zhout26
 * @version 1.0
 * 2020/10/9 11:16
 **/

@Service
@Slf4j
public class ImportDomainService extends AbstractCommonService<ImportDomain> {

    @Resource
    private ImportDomainMapper importDomainMapper;

    @Override
    public CommonMapper<ImportDomain> getMapper() {

        return this.importDomainMapper;
    }

    public List<ImportDomain> queryImportDomainBySql(int currPage, int pageSize){
        Map<String, Object> data = new HashedMap();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return importDomainMapper.queryImportDomainBySql(data);
    }
}
