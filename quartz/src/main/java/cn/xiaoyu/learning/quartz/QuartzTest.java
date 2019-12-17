package cn.xiaoyu.learning.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;
import java.util.Set;


/**
 * @author roin.zhang
 * @date 2019/12/6
 */
public class QuartzTest {
	public static void main(String[] args) throws SchedulerException {
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

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
		// tell quartz to schedule the job using our trigger
		Set triggers = new HashSet();
		triggers.add(trigger);
		scheduler.scheduleJob(job, triggers, true);

//        scheduler.shutdown();
	}
}
