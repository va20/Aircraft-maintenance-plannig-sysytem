package com.example.glproject.businessobjects;

public class GenericTask {
	private String taskNumber;
	private int zone;
	private String descr;
	private String thresholdInterval;
	private String source;
	private String ref;
	private int men;
	private String mPerH;
	private String applicability;

	public GenericTask() {
	}

	public GenericTask(String taskNumber, int zone, String descr, String thresholdInterval, String source, String ref,
			int men, String mPerH, String applicability) {
		super();
		this.taskNumber = taskNumber;
		this.zone = zone;
		this.descr = descr;
		this.thresholdInterval = thresholdInterval;
		this.source = source;
		this.ref = ref;
		this.men = men;
		this.mPerH = mPerH;
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

	public String getThresholdInterval() {
		return thresholdInterval;
	}

	public void setThresholdInterval(String thresholdInterval) {
		this.thresholdInterval = thresholdInterval;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getMen() {
		return men;
	}

	public void setMen(int men) {
		this.men = men;
	}

	public String getmPerH() {
		return mPerH;
	}

	public void setmPerH(String mPerH) {
		this.mPerH = mPerH;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}

	@Override
	public String toString() {
		return "GenericTask [taskNumber=" + taskNumber + ", zone=" + zone + ", descr=" + descr + ", thresholdInterval="
				+ thresholdInterval + ", source=" + source + ", ref=" + ref + ", men=" + men + ", mPerH=" + mPerH
				+ ", applicability=" + applicability + "]";
	}
}
