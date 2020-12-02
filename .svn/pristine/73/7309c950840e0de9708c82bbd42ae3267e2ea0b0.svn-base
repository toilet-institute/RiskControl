
package com.jxlt.udic.riskcontrol.website.service;

import com.jxlt.udic.riskcontrol.website.model.SysStaff;
import com.jxlt.udic.riskcontrol.website.enums.ResponseResultCodeEnum;
import com.jxlt.udic.riskcontrol.website.exception.BusinessException;
import com.jxlt.udic.riskcontrol.website.persistance.common.CommonMapper;
import com.jxlt.udic.riskcontrol.website.service.common.AbstractCommonService;
import com.jxlt.udic.riskcontrol.website.util.CommonUtil;
import com.jxlt.udic.riskcontrol.website.persistance.mapper.SysStaffMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysStaffService extends AbstractCommonService<SysStaff> {

    @Resource
    private SysStaffMapper mapper;

    @Override
    public CommonMapper<SysStaff> getMapper() {
        return this.mapper;
    }

    public int getMaxsort() {
        return mapper.getMaxsort();
    }

    public List<SysStaff> queryListByOrg(String deptcode, String orgcode) {

        List<SysStaff> iuserList = mapper.queryListByOrg(deptcode, orgcode);
        return iuserList;
    }

    public SysStaff getUserByUserName(String username) {

        return mapper.getUserByUserName(username);
    }

    public SysStaff getUserByUserCode(String usercode) {

        return mapper.getUserByUserCode(usercode);
    }

    public int sysUserEnable(Map params) {
        return mapper.sysStaffEnable(params);
    }

//    public String updatePersonPassword(PersonUpdatePasswordVO updatePasswordVO) {
//        SysStaff person = mapper.getPersonByLoginName(updatePasswordVO.getLoginName());
//        if (person == null) {
//            log.info("没有查到该用户{}", updatePasswordVO.getLoginName());
//            throw new BusinessException(ResponseResultCodeEnum.PERSON_NOT_FIND_ERROR);
//        }
//        String oldPassword = CommonUtil.getLoginPasswod(updatePasswordVO.getOldPassword());
//        if (oldPassword == null) {
//            log.error("【修改密码解析出错】用户姓名:{},用户老密码:{}", updatePasswordVO.getLoginName(), updatePasswordVO.getOldPassword());
//            throw new BusinessException(ResponseResultCodeEnum.REQUEST_PARAMS_ERROR);
//        }
//        if (!oldPassword.equals(person.getLoginpwd())) {
//            log.error("【修改密码出错】新老密码对,用户姓名:{},用户老密码:{}", updatePasswordVO.getLoginName(),
//                updatePasswordVO.getOldPassword());
//            throw new BusinessException(ResponseResultCodeEnum.PASSWORD_VALIDATE_ERROR);
//        }
//        String newPassword = CommonUtil.getLoginPasswod(updatePasswordVO.getNewPassword());
//        if (newPassword == null) {
//            log.error("【修改密码解析出错】用户姓名:{},用户新密码:{}", updatePasswordVO.getLoginName(), updatePasswordVO.getNewPassword());
//            throw new BusinessException(ResponseResultCodeEnum.REQUEST_PARAMS_ERROR);
//        }
//        mapper.updatePasswordByLoginName(updatePasswordVO.getLoginName(), newPassword);
//        return updatePasswordVO.getLoginName();
//    }

//    public Map<String, SysStaff> getIdMaps(List<String> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            return Maps.newHashMap();
//        }
//
//        List<SysStaff> iuserList = this.queryListByIds(idList);
//        return iuserList.stream()
//            .collect(Collectors.toMap(SysStaff::getId, Function.identity()));
//
//    }

    private List<SysStaff> queryListByIds(List<String> idList) {
        return mapper.getListByIdList(idList);
    }

    public List<SysStaff> queryIdListByDeptCodes(List<String> deptCodes) {
        return mapper.queryIdListByDeptCodes(deptCodes);
    }
}
