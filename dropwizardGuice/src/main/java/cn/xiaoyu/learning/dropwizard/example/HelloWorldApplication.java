package cn.xiaoyu.learning.dropwizard.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * start application failure
 *
 * @author roin.zhang
 * @link {https://www.jianshu.com/p/3bb308c9bbcb}
 * @date 2019/10/12
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),
                configuration.getDefaultName());
        // 添加健康检查
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

    /**
     * 用于在运行应用程序之前配置所需应用程序的内容，如bundle，配置源提供程序
     *
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }
}
