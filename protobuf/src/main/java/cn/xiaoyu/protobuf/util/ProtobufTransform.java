package cn.xiaoyu.protobuf.util;

import com.google.protobuf.GeneratedMessageV3;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** https://www.jianshu.com/p/85c511cc2bf7 */
public class ProtobufTransform {
  /** ProtoBuffer object to POJO */
  public static <T> T fromProtoBuffer(GeneratedMessageV3 pbObject, Class<T> modelClass) {
    T model = null;
    try {
      model = modelClass.getConstructor().newInstance();
      Field[] modelFields = modelClass.getDeclaredFields();
      if (modelFields != null && modelFields.length > 0) {
        for (Field modelField : modelFields) {
          String fieldName = modelField.getName();
          String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

          try {
            Class<?> fieldType = modelField.getType();
            Method pbGetMethod = pbObject.getClass().getMethod("get" + name);
            Object value = pbGetMethod.invoke(pbObject);

            Method modelSetMethod = modelClass.getMethod("set" + name, fieldType);
            modelSetMethod.invoke(model, value);
          } catch (Exception e) {

          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return model;
  }

  /** POJO -> ProtoBuffer object */
  public static <T> T toProtoBuffer(Object model, Class<T> pbClass) {
    if (!GeneratedMessageV3.class.isAssignableFrom(pbClass)) {
      throw new RuntimeException("Not ProtoBuffer message type");
    }

    T pbObject = null;
    try {
      Object pbBuilder = pbClass.getDeclaredMethod("newBuilder").invoke(null);
      Field[] pbFields = pbClass.getDeclaredFields();
      if (pbFields != null && pbFields.length > 0) {
        for (Field pbField : pbFields) {
          String fieldName = pbField.getName().substring(0, pbField.getName().length() - 1);
          String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
          Class<?> fieldType = pbField.getType();
          if (pbField.getType() == Object.class) {
            fieldType = String.class;
          }

          try {
            Method modelGetMethod = model.getClass().getMethod("get" + name);
            Object value = modelGetMethod.invoke(model);
            if (value != null) {
              Method pbBuilderSetMethod = pbBuilder.getClass().getMethod("set" + name, fieldType);
              pbBuilderSetMethod.invoke(pbBuilder, value);
            }
          } catch (Exception e) {

          }
        }
      }
      pbObject = (T) pbBuilder.getClass().getDeclaredMethod("build").invoke(pbBuilder);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return pbObject;
  }
}
