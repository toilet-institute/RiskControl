package com.jxlt.udic.riskcontrol.website.service.common;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;

import tk.mybatis.mapper.entity.Example;
/**
 * 通用service，被其他service所继承
 */
@Transactional//(readOnly=true)
public abstract class AbstractCommonService<T>{
	@Transactional(readOnly=false)
	public int insertList(List<T> recordList){
		return getMapper().insertList(recordList);
	}
	
	@Transactional(readOnly=false)
	public int insertUseGeneratedKeys(T record) throws Exception{
		return getMapper().insertUseGeneratedKeys(record);
	}

	@Transactional(readOnly=false)
	public int insertSelective(T t){
		return getMapper().insertSelective(t);
	}

	@Transactional(readOnly=false)
	public  int deleteByExample(Example example) throws Exception{
		return getMapper().deleteByExample(example);
	}
	
	@Transactional(readOnly=false)
	public int deleteByPrimaryKey(Object key) throws Exception{
		return getMapper().deleteByPrimaryKey(key);
	}
	
	@Transactional(readOnly=false)
	public int updateByPrimaryKeySelective(T record) throws Exception{
		return getMapper().updateByPrimaryKeySelective(record);
	}
	
	@Transactional(readOnly=false)
	public int updateByExampleSelective(T record, Example example) throws Exception{
		return getMapper().updateByExampleSelective(record, example);
	}
	
	@Transactional(readOnly=false)
	public int updateByExample(T record, Example example) throws Exception{
		return getMapper().updateByExample(record, example);
	}

	@Transactional(readOnly=false)
	public int updateByPrimaryKey(T t) throws Exception{
		return getMapper().updateByPrimaryKey(t);
	}
	
	public int selectCountByExample(Example example) throws Exception{
		return getMapper().selectCountByExample(example);
	}

	public int selectCount(T record) throws Exception{
		return getMapper().selectCount(record);
	}

	public List<T> selectByExample(Example example) throws Exception{
		return getMapper().selectByExample(example);
	}
	
	public T selectByPrimaryKey(Object key){
		return getMapper().selectByPrimaryKey(key);
	}
	
	public List<T> selectAll(){
		return getMapper().selectAll();
	}
	
    public abstract CommonMapper<T> getMapper();
}
