package cn.xiaoyu.learning.common.serialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * @author roin.zhang
 * @date 2019-08-19
 */
public final class JacksonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);
    private static ObjectMapper jackson;

    static {
        jackson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JacksonUtils() {
    }

    /**
     * Json To Object
     *
     * @param json  Json
     * @param clazz Class<T>
     * @param <T>   T
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            if (!Strings.isNullOrEmpty(json)) {
                return jackson.readValue(json, clazz);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * Object To Json
     *
     * @param obj Object
     * @return Json
     */
    public static String toJson(Object obj) {
        try {
            if (obj == null) {
                return "";
            } else if (obj instanceof String) {
                return (String) obj;
            } else {
                return jackson.writeValueAsString(obj);
            }
        } catch (Exception ex) {
            return "";
        }
    }
}
