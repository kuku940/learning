package cn.xiaoyu.java.annotation.test;

import cn.xiaoyu.java.annotation.component.Column;
import cn.xiaoyu.java.annotation.component.Table;
import cn.xiaoyu.java.annotation.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Roin_zhang
 * @date 2018/7/9 23:11
 */
public class AnnotationTest {
    public static void main(String[] args) {
        User user = new User(10086, "Jack", "male", 40);
        query(user);
    }

    private static void query(Object obj) {
        StringBuilder builder = new StringBuilder();
        Class clazz = obj.getClass();

        // 如果类不包含@Table直接结束
        if (!clazz.isAnnotationPresent(Table.class)) {
            return;
        }

        // 获得表名
        Table table = (Table) clazz.getAnnotation(Table.class);
        builder.append("select * from ").append(table.value()).append(" where 1=1");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 如果该字段不包含@Column注解，跳过
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String fieldName = field.getName();

            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method method = clazz.getDeclaredMethod(methodName);
                Object o = method.invoke(obj);
                if (o == null || (o instanceof Integer && (Integer) o == 0)) {
                    continue;
                }
                //拼装sql
                if (o instanceof String) {
                    builder.append(" and " + columnName + " = '" + o + "'");
                } else if (o instanceof Number) {
                    builder.append(" and " + columnName + " = " + o);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(builder.toString());
    }
}
