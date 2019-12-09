package cn.xiaoyu.learning.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author roin.zhang
 * @date 2019/12/6
 */
public class HelloJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    private String jobSays;
    private float floatValue;
    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }
    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

//        String jobSays = jobDataMap.getString("JobSays");
//        float floatValue = jobDataMap.getFloat("FloatValue");

        LOGGER.info("key:{}, jobSays:{}, value:{}", key, jobSays, floatValue);
    }
}
