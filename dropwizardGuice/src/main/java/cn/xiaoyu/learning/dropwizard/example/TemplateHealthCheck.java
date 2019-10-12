package cn.xiaoyu.learning.dropwizard.example;

import com.codahale.metrics.health.HealthCheck;

/**
 * 创建健康检查
 *
 * @author roin.zhang
 * @date 2019/10/12
 */
public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
