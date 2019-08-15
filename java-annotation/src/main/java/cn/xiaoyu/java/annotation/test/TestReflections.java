package cn.xiaoyu.java.annotation.test;

import cn.xiaoyu.java.annotation.component.Person;
import cn.xiaoyu.java.annotation.component.Table;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

/**
 * @link https://www.programcreek.com/java-api-examples/index.php?api=org.reflections.scanners.MethodAnnotationsScanner
 */
public class TestReflections {
    private static final String DOT = ".";

    public static void main(String[] args) throws Exception {
        Collection<Method> methods = getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Person.class)) {
                // 获取方法注释并且反射实例化并执行
                Person person = method.getAnnotation(Person.class);
                System.out.println(person.name() + " - " + person.gender());
                Class clazz = method.getDeclaringClass();
                System.out.println(clazz.getName());
                Constructor constructor = clazz.getConstructor();
                method.invoke(constructor.newInstance());
            }
        }

        Set<Class<?>> classes = getClasses();
        classes.forEach(clazz -> System.out.println("======" + clazz.getName()));
    }

    /**
     * 获取所有被Person注释修饰的方法
     */
    public static Collection<Method> getMethods() {
        return new Reflections(
                new ConfigurationBuilder()
                        .addUrls(ClasspathHelper.forJavaClassPath())
                        .setScanners(new MethodAnnotationsScanner(), new TypeAnnotationsScanner())
                        .addClassLoader(Thread.currentThread().getContextClassLoader())
                        .filterInputsBy(in -> in.endsWith("java") || in.endsWith("class")))
                .getMethodsAnnotatedWith(Person.class);
    }

    /**
     * 获取所有table注释修饰的子类
     */
    public static Set<Class<?>> getClasses() {
        return new Reflections(
                new ConfigurationBuilder()
                        // 不扩展父类
                        .setExpandSuperTypes(false)
                        .addUrls(ClasspathHelper.forJavaClassPath())
                        .filterInputsBy(name -> {
                            if (name.endsWith("java") || name.endsWith("class")) {
                                try {
                                    String className = name.substring(0, name.lastIndexOf(DOT));
                                    ClasspathHelper.contextClassLoader().loadClass(className);
                                } catch (Throwable e) {
                                    // 过滤当前类加载器无法加载的类
                                    return false;
                                }
                                return true;
                            }
                            return false;
                        })
                        .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner())
                        .addClassLoader(Thread.currentThread().getContextClassLoader()))
                .getTypesAnnotatedWith(Table.class);
    }
}
