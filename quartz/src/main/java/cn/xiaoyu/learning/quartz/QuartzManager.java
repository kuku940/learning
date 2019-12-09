package cn.xiaoyu.learning.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务工具类
 *
 * @author roin.zhang
 * @date 2019/12/9
 */
public class QuartzManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzManager.class);
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    /**
     * 添加任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param jobClass
     * @param cron
     */
    public static void addJob(String jobName, String jobGroupName, String triggerName,
                              String triggerGroupName, Class jobClass, String cron) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 触发器
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

            // start
            if (scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            LOGGER.error("addJob Error", e);
        }
    }

    /**
     * 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param cron
     */
    public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (trigger == null) {
                return;
            }

            String cronExpression = trigger.getCronExpression();
            if (!cronExpression.equalsIgnoreCase(cron)) {
                // 方法1
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerName, triggerGroupName)
                        .startNow()
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                        .build();
                scheduler.rescheduleJob(triggerKey, trigger);

                // 方法2 先删除，再创建
//                JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
//                Class<? extends Job> jobClass = jobDetail.getJobClass();
//                removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
//                addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
            }
        } catch (Exception e) {
            LOGGER.error("modifyJobTime Error", e);
        }
    }

    /**
     * 移除任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            LOGGER.error("removeJob Error", e);
        }
    }

    /**
     * 关闭所有任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            LOGGER.error("shutdownJobs Error", e);
        }
    }
}
