package com.yffd.jecap.common.base.util;

import com.yffd.jecap.common.base.constants.BaseConst;
import com.yffd.jecap.common.base.entity.BaseEntity;
import com.yffd.jecap.common.base.login.ILoginInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

public class EntityUtil {

    public static void initPropsForAdd(Object pojo) {
        if(pojo instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) pojo;
            if (null == entity.getVersion()) entity.setVersion(0);
            if (StringUtils.isBlank(entity.getDelFlag())) entity.setDelFlag("0");
            if (null == entity.getCreateTime()) entity.setCreateTime(new Date());
            if (StringUtils.isBlank(entity.getCreateBy())) {
                entity.setCreateBy(getUserName());
            }
        } else if(pojo instanceof Map) {
            Map<String, Object> parameter = (Map<String, Object>) pojo;
            if (null == parameter.get("version")) parameter.put("version", 0);
            if (null == parameter.get("delFlag")) parameter.put("delFlag", "0");
            if (null == parameter.get("createTime")) parameter.put("createTime", new Date());
            if (null == parameter.get("createBy")) {
                parameter.put("createBy", getUserName());
            }
        }
    }

    public static void initPropsForUpdate(Object pojo) {
        if(pojo instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) pojo;
            if (null == entity.getUpdateBy()) entity.setUpdateTime(new Date());
            if (StringUtils.isBlank(entity.getUpdateBy())) {
                entity.setUpdateBy(getUserName());
            }
        } else if(pojo instanceof Map) {
            Map<String, Object> parameter = (Map<String, Object>) pojo;
            if (null == parameter.get("updateTime")) parameter.put("updateTime", new Date());
            if (null == parameter.get("updateBy")) {
                parameter.put("updateBy", getUserName());
            }
        }
    }

    public static void initPropsForQuery(Object pojo) {
        if(pojo instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) pojo;
            if (StringUtils.isBlank(entity.getDelFlag()))  entity.setDelFlag("0");
        } else if(pojo instanceof Map) {
            Map<String, Object> parameter = (Map<String, Object>) pojo;
            if (null == parameter.get("delFlag")) parameter.put("delFlag", "0");
        }
    }

    public static void initPropsForDelete(Object pojo) {

    }


    private static String getUserName() {
        HttpSession session = SpringWebContextUtil.getSession();
        if (null == session) return null;
        ILoginInfo info = (ILoginInfo) session.getAttribute(BaseConst.ACCESS_TOKEN_KEY);
        return null == info ? null : info.getUserName();
    }

}
