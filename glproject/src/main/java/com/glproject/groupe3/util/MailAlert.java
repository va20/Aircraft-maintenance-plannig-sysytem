package com.glproject.groupe3.util;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MailAlert {
	private Scheduler scheduler;

	public MailAlert() throws SchedulerException {
		JobDetail job = JobBuilder.newJob(Alert.class).withIdentity("dummyJobName", "group1").build();

		// Quartz 1.6.3
		// SimpleTrigger trigger = new SimpleTrigger();
		// trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
		// trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		// trigger.setRepeatInterval(30000);

		// Trigger the job to run on the next round minute
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}

	class Alert implements Job {

		public void execute(JobExecutionContext arg0) throws JobExecutionException {
			System.out.println("ASJ?AOPZS JAOPIS JAIOPSJAIOPS JAOPSJ AOPI");

			System.out.println("notify the MCC");
			Util.mail("probleme avec la tache" + "", "Task Alert");

			try {
				scheduler.shutdown();

			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}
}