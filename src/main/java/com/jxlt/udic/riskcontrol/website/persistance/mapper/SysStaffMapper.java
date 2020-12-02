package com.jxlt.udic.riskcontrol.website.persistance.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jxlt.udic.riskcontrol.website.model.SysStaff;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;

public interface SysStaffMapper extends CommonMapper<SysStaff>{
	
	 int getMaxsort();
	 SysStaff getUserByUserName(String username);
	 SysStaff getUserByUserCode(String usercode);
	 int sysStaffEnable(Map params);
//	 void updateUserinfo(Map<String, Object> params);
	 List<SysStaff> queryListByOrg(String deptcode, String orgcode);
	 List<SysStaff> getListByIdList(@Param("idList") List<String> idList);
//	 int updateTokenByLoginName(@Param("loginname") String loginname, @Param("tokencode") String tokencode);
    List<SysStaff> queryIdListByDeptCodes(@Param("deptCodes") List<String> deptCodes);

}
