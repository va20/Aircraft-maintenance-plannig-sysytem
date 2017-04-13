package com.glproject.groupe3.businessobjects;

public class GenericTask {
	private String taskNumber;
	private String type;
	private int zone;
	private String descr;
	private String periodicity;
	private String hangar;
	private int men;
	private String applicability;

	public GenericTask() {
	}

	public GenericTask(String taskNumber, String type, int zone, String descr, String periodicity, String hangar,
			int men, String applicability) {
		super();
		this.taskNumber = taskNumber;
		this.type = type;
		this.zone = zone;
		this.descr = descr;
		this.periodicity = periodicity;
		this.hangar = hangar;
		this.men = men;
		this.applicability = applicability;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public String isHangar() {
		return hangar;
	}

	public void setHangar(String hangar) {
		this.hangar = hangar;
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
		return "GenericTask [taskNumber=" + taskNumber + ", type=" + type + ", zone=" + zone + ", descr=" + descr
				+ ", periodicity=" + periodicity + ", hangar=" + hangar + ", men=" + men + ", applicability="
				+ applicability + "]";
	}
}