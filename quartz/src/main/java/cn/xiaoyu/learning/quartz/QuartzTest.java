package cn.xiaoyu.learning.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * @author roin.zhang
 * @date 2019/12/6
 */
public class QuartzTest {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();

		// define the job and tie it to HelloJob class
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob", "group1")
				// 在构建JobDetail时，可以在JobDataMap中存储些基本类型的数据
				.usingJobData("JobSays", "HelloWorld!")
				.usingJobData("FloatValue", 3.14f)
				.build();

		// trigger the job to run now, and then every 40 seconds
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(20)
						.repeatForever())
				.build();

		CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity("trigger", "groupName")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?")
						.withMisfireHandlingInstructionFireAndProceed())
				.build();


		// tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(job, trigger);

//        scheduler.shutdown();
	}
}
