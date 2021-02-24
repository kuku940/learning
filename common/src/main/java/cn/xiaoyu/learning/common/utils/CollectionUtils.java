package cn.xiaoyu.learning.common.utils;

import cn.xiaoyu.learning.common.exception.BizException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DoubleConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionUtils {
    public static final DoubleConverter sixDecimal;

    static {
        sixDecimal = new DoubleConverter();
        sixDecimal.setPattern("#.######");
    }

    public static List<Map<String, String>> object2MapList(List<?> list, Converter... converters) {
        try {
            for (Converter converter : converters) {
                ConvertUtils.register(converter, String.class);
            }

            List<Map<String, String>> result = new ArrayList<>();
            for (Object object : list) {
                result.add(BeanUtils.describe(object));
            }
            return result;
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    public static List<Map<String, String>> object2MapList(List<?> list) {
        return object2MapList(list, sixDecimal);
    }

    public static Map<String, String> object2Map(Object object, Converter... converters) {
        try {
            for (Converter converter : converters) {
                ConvertUtils.register(converter, String.class);
            }

            return BeanUtils.describe(object);
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    public static Map<String, String> object2Map(Object object) {
        return object2Map(object, sixDecimal);
    }
}
