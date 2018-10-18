package com.dk.gfm.utli;

import com.dk.gfm.common.ResultInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @ClassName VerifyUtil
 * @Description 验证工具类
 * @Author xiadekang
 * @Date 2018/9/11 18:00
 * @Version 1.0
 **/
public class VerifyUtil {

    public static ResultInfo isNullParams(Map<String, Object> params){
        ResultInfo result = new ResultInfo();
        if (params == null) {
            result.code = -1;
            result.msg = "参数都为空";

            return result;
        }

        for(Map.Entry<String, Object> map : params.entrySet()) {
            if (StringUtils.isBlank(map.getKey()) || StringUtils.isBlank(String.valueOf(map.getValue()))) {
                result.code = -1;
                result.msg = "参数"+map.getKey()+"为空";

                return result;
            }
        }

        result.code = 1;
        result.msg = "参数都存在";

        return result;
    }
}
