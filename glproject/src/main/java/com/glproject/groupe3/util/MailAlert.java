package com.glproject.groupe3.util;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;
import com.glproject.groupe3.businessobjects.Task;

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

	protected void scheduleJob(Class<? extends Job> jobClass, DateTime alertDate, String jobName, String groupName) {
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, groupName).startAt(alertDate.toDate())
				.build();

		JobDetail job = JobBuilder.newJob(jobClass).withIdentity(jobName, groupName).build();

		try {
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			System.out.println("Failed to start the job " + jobName);
			e.printStackTrace();
		}
	}

	public void scheduleAlert(Task task) {
		scheduleAlert(String.valueOf(task.getId()));
	}

	public void scheduleAlert(String taskId) {
		Task task = ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO())
				.get(Constants.TASKS, String.valueOf(taskId));
		if (task == null) {
			System.out.println("Task " + taskId + " not found !");
			return;
		}

		// DateTime alertDate = new DateTime(task.getDepDate().getTime() - 12 *
		// 3600 * 1000);
		// scheduleJob(Alert.class, alertDate, taskId, "alert");
	}

	private class Alert implements Job {

		public void execute(JobExecutionContext arg0) throws JobExecutionException {
			String taskId = arg0.getJobDetail().getKey().getName();

			Task task = ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO())
					.get(Constants.TASKS, String.valueOf(taskId));
			if (task == null) {
				System.out.println("Task " + taskId + " not found !");
				return;
			}

			System.out.println("notify the MCC");
			Util.mail("probleme avec la tache" + taskId, "Task Alert");
		}
	}
}