package test.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.*;

public class QuartzCronTest {

	public static void main(String[] args) {
		System.out.println("=== 배치 프로그램 시작됨 ===");

		// 스케줄러 팩토리 준비
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();

		try {
			// 스케줄러 객체 준비
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();

			// JobDetail 타입 객체 준비
			JobDetail jobDetail = newJob(TestJob.class)
					.withIdentity("jobName", Scheduler.DEFAULT_GROUP)
					.build();

			// Trigger 객체 준비
			Trigger trigger = newTrigger()
					.withIdentity("triggerName", Scheduler.DEFAULT_GROUP)
					.withSchedule(cronSchedule("0 * * * * ?"))
					.build();

			// 트리거를 스케줄러에 등록하면 스케줄에 따라서 실행됨
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // main

}
