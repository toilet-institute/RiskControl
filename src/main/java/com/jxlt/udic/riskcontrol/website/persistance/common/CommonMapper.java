package com.jxlt.udic.riskcontrol.website.persistance.common;



import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper，被其他mapper所继承 
 * 特别注意，该接口不能被扫描到，否则会出错
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {    
}
