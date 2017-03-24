package com.example.glproject.businessobjects;

public class GenericTask {
	private String taskNumber;
	private int zone;
	private String descr;
	private long periodicity;
	private boolean hangar;
	private long duration;
	private int men;
	private String applicability; //type avion

	public GenericTask() {
	}

	public GenericTask(String taskNumber, int zone, String descr, long periodicity, boolean hangar, long duration,
			int men, String applicability) {
		super();
		this.taskNumber = taskNumber;
		this.zone = zone;
		this.descr = descr;
		this.periodicity = periodicity;
		this.hangar = hangar;
		this.duration = duration;
		this.men = men;
		this.applicability = applicability;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public long getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(long periodicity) {
		this.periodicity = periodicity;
	}

	public boolean isHangar() {
		return hangar;
	}

	public void setHangar(boolean hangar) {
		this.hangar = hangar;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getMen() {
		return men;
	}

	public void setMen(int men) {
		this.men = men;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}

	@Override
	public String toString() {
		return "GenericTask [taskNumber=" + taskNumber + ", zone=" + zone + ", descr=" + descr + ", periodicity="
				+ periodicity + ", hangar=" + hangar + ", duration=" + duration + ", men=" + men + ", applicability="
				+ applicability + "]";
	}
}