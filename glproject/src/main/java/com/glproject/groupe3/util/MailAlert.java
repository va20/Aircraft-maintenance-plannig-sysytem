package com.glproject.groupe3.util;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class MailAlert {
	private Scheduler scheduler;

	public MailAlert() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void scheduleJob(Class <? extends Job> jobC, DateTime date, String job, String group) {
		
	}
}