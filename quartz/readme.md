# 核心概念

1. **Job** 要执行的具体的任务；
2. **JobDetail** 可执行的调度任务，Job是这个可执行程序所要执行的内容，JobDetail还包含这个任务调度的方案和策略；
3. **Trigger** 调度参数的配置；
4. **Scheduler** 调度容器，一个调度容器中可以注册多个JobDetail和Trigger，当Trigger与JobDetail组合时，就可以被Schedular容器调用了。

## Job&JobDetail

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


## Trigger 

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
        
### SimpleTrigger

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
    
5分钟后触发且仅执行一次

    SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.MINUTE))
        .forJob("myJob", "group1")
        .build();  
        
立即触发，每5分钟执行一次，直到22:00

    SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInMilliseconds(5)
            .repeatForever())
        .endAt(DateBuilder.dateOf(22, 0, 0))
        .forJob("myJob", "group1")
        .build();
        
建立在下一个整点触发，然后每2小时重复一次的触发器
    
    SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .startAt(DateBuilder.evenHourDate(null))
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInHours(2)
            .repeatForever())
        .build();
    
> TriggerBuilder和SimpleScheduleBuilder提供的方法，没有显式设置的属性都会设置合理的默认值。
> 例如，如果没有设置startAt()，则默认使用的是当前时间，即trigger立即生效。

### SimpleTrigger Misfire策略

    MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
    MISFIRE_INSTRUCTION_FIRE_NOW
    MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT
    MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT
    MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT
    MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT
    
所有的Trigger都有一个Trigger.MISFIRE_INSTRUCTION_SMART_POLICY策略可以使用，该策略也是所有trigger的策略。
如果使用smart policy，SimpleTrigger会根据实例的配置及状态，在所有MISFIRE策略中选择一种Misfire策略。  
在使用SimpleTrigger构造trigger时，misfire策略作为基本调度（simple schedule）的一部分进行配置

    SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInMinutes(5)
            .repeatForever()
            .withMisfireHandlingInstructionNextWithExistingCount())
        .build();

### CronTrigger
CronTrigger可以指定好时间表，而不必像SimpleTrigger的精确指定间隔进行重新启动的作业启动计划。

#### Cron Expressions
Cron-Expressions用于配置CronTrigger实例。Cron-Expression由七个子表达式用空格分隔，并表示：

1. Seconds 
2. Minutes
3. Hours
4. Day-of-Month
5. Month
6. Day-of-Week
7. Year

完整的Cron-Expressions的例子是字符串"0 0 12? * WED  " - “每周三下午12:00”。
单个表达式可以包含范围和/或列表。


##### Cron-Expressions示例

1. 创建触发器的表达式，每5分钟触发一次```0 0/5 * * *? ```
2. 每5分钟触发一次，分钟的第10秒```10 0/5 * * *? ```
3. 在每个星期三和星期五的10:30，11:30，12:30，13:30创建触发器的表达式```0 30 10-30? * WED,FRI ```
4. 每个月5日和20日上午8点至10点之间每半小时触发一次，```0 0/30 8-9 5,20 *? ```  
注：不会在10:00触发，只会在8:00，8:30，9:00和9:30触发

备注：如果一些调度太复杂，无法使用单一触发表示，例如：“每天上午9:00-10:00之间每5分钟”
以及“下午1:00至晚上10之间每20分钟”触发一次。这种情况最简单的解决方案就是简单地创建两个触发器，
并注册它们来运行相同的作业。

##### 构建CronTriggers
CronTrigger实例使用TriggerBuilder（触发器的主要属性）和CronScheduleBuilder（CronTrigger特定属性）构建。

每天上午8点至下午5点之间，每隔一分钟触发一次

    CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
        .forJob("myJob", "group1")
        .build();
        
每天上午10:42触发

    CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
        .withIdentity("trigger", "groupName")
        .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 42))
        // .withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ?"))
        .forJob("myJob", "group1")
        .build();
        
##### CronTrigger Misfire说明
通知Quartz当CronTrigger发生Misfire时应该做什么。CronTrigger的Misfire指令常数

    MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
    MISFIRE_INSTRUCTION_DO_NOTHION
    MISFIRE_INSTRUCTION_FIRE_NOW
    
所有的Trigger都有一个Trigger.MISFIRE_INSTRUCTION_SMART_POLICY策略可用，
并且该指令也是所有触发器类型的默认值。SMART_POLICY指令由CronTrigger解释为
MISFIRE_INSTRUCTION_FIRE_NOW。

    CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
         .withIdentity("trigger", "groupName")
         .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?")
             .withMisfireHandlingInstructionFireAndProceed())
         .build();
         
## TriggerListeners和JobListeners

Listeners是创建的对象，用于根据调度程序中发生的事件执行操作。
TriggerListeners接收到与触发器（Trigger）相关的事件；
JobListeners接收到jobs相关的事件。

与触发相关的事件包括：触发器触发，触发失灵，并触发完成（触发器关闭的作业完成）；
与Job相关的时间包括：Job即将执行的通知以及Job完成执行时的通知。

### 创建自己的Listener
创建一个listener，需要创建一个实现org.quartz.TriggerListener或org.quartz.JobListener接口的对象。
然后listener在运行时会向调度程序注册，并且必须给出一个名称。也可以扩展JobListenerSupport或
TriggerListenerSupport类，并且只需要覆盖你感兴趣的事件。
listener与调度程序的ListenerManager一起注册，并配有描述listener希望接收事件的job和触发器Matcher。

> 在运行时间内与调度程序一起注册，并且不与jobs和触发器一起存储在JobStore中。这是因为Listener通常
> 是与应用程序的集成点。因此，每次运行程序时，都需要注册该调度程序，

添加特定Job感兴趣JobListener

    scheduler.getListenerManager().addJobListener(myJobListener, JobKey.jobKey("jobName", "groupName"));

添加对特定组的所有Job感兴趣的JobListener

    scheduler.getListenerManager().addJobListener(myJobListener, GroupMatcher.jobGroupEquals("groupName"));
    
添加对两个特定组的所有job感兴趣的JobListener

    scheduler.getListenerManager().addJobListener(myJobListener, OrMatcher.or(GroupMatcher.jobGroupEquals("myGroupName"), GroupMatcher.jobGroupEquals("yourGroupName")));

添加对所有job感兴趣的JobListener

    scheduler.getListenerManager().addJobListener(myJobListener, EverythingMatcher.allJobs());
    
注册TriggerListeners的工作原理相同。Quartz用户并不经常使用，但是当应用程序需求创建需要事件通知时
不需要Job本身就必须明确地通知应用程序。

## SchedulerListener
SchedulerListener类似于TriggerListener和JobListener。
与计划程序相关的事件包括：添加job/触发器，删除job/触发器，调度程序中的严重错误，关闭调度程序的通知等。

    scheduler.getListenerManager().addSchedulerListener(mySchedulerListener);
    scheduler.getListenerManager().removeSchedulerListener(mySchedulerListener);
    
## JobStore

JobStore负责跟踪您提供给程序的所有“工作数据”：jobs，triggers，日历等。

### RAMJobStore
最简单的JobStore，也是性能最高的（在CPU时间方面）。它将所有的数据保存在RAM中，缺点是应用程序结束或崩溃时，
所有的调度信息都将丢失。

> org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore

### JDBC JobStore
通过JDBC将所有数据保存在数据库中。因此，配置比RAMJobStore要复杂一点，而且也不是很快。
但是，性能下降的并不是很糟糕，特别是如果在主键上构建具有索引的数据库。