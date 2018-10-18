package com.dk.gfm.utli;

import org.apache.commons.lang3.StringUtils;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName Convert
 * @Description 类型转换
 * @Author xiadekang
 * @Date 2018/9/28
 * @Version 1.0
 **/
public  class Convert {

    public static String orEles(Object value, Object def){
        if (value == null || "".equals(value)) {

            return String.valueOf(def);
        }

        return String.valueOf(value);
    }

}
