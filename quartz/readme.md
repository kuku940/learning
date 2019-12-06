## 核心概念

1. **Job** 要执行的具体的任务；
2. **JobDetail** 可执行的调度任务，Job是这个可执行程序所要执行的内容，JobDetail还包含这个任务调度的方案和策略；
3. **Trigger** 调度参数的配置；
4. **Scheduler** 调度容器，一个调度容器中可以注册多个JobDetail和Trigger，当Trigger与JobDetail组合时，就可以被Schedular容器调用了。

### Job&JobDetail

创建JobDetail，并且使用JobDataMap存储基本类型数据

    JobDetail job = JobBuilder.newJob(HelloJob.class)
        .withIdentity("myJob", "group1")
        .usingJobData("JobSays", "HelloWorld!")
        .usingJobData("FloatValue", 3.14f)
        .build();

创建Job，获取JobDataMap数据

    JobKey key = context.getJobDetail().getKey();
    JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

    String jobSays = jobDataMap.getString("JobSays");
    float floatValue = jobDataMap.getFloat("FloatValue");

    LOGGER.info("key:{}, jobSays:{}, value:{}", key, jobSays, floatValue);

JobFactory实现JobDataMap数据自动注入

    private String jobSays;
    private float floatValue;
    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }
    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }


### Trigger 

创建简单的配置任务，20秒循环执行

    Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("myTrigger", "group1")
        .startNow()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(20)
                .repeatForever())
        .build();

使用Calendar排除时间段，并建立调度计划

    HolidayCalendar cal = new HolidayCalendar();
    cal.addExcludedDate(someDate);
    cal.addExcludedDate(someOtherDate);
    
    scheduler.addCalendar("myHolidays", cal, false);
    
    Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("myTrigger", "group1")
        .forJob("myJob")
        .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(9, 30))
        .modifiedByCalendar("myHolidays")
        .build();
        
#### SimpleTrigger

SimpleTrigger可以满足以下两个调度需求：  
1. 在指定时间执行一次；
2. 在指定时间执行，并且以指定间隔重复执行若干次。

指定时间触发不重复

    Date startDate = new Date();
    SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .startAt(startDate)
        .forJob("myJob", "group1")
        .build();
        
指定时间触发，每隔10秒执行一次，执行10次

    Date startDate = new Date();
    SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .startAt(startDate)
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInSeconds(10)
            .withRepeatCount(10))
        .forJob("myJob", "group1")
        .build();        
    
    
    