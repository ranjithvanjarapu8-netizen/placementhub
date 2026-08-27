package com.placementhub.dto;

public class StudentDashboardDto {

	private long openJobs;

    private long appliedJobs;

    private long yetToApplyJobs;

    public long getOpenJobs() {
        return openJobs;
    }

    public void setOpenJobs(long openJobs) {
        this.openJobs = openJobs;
    }

    public long getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(long appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public long getYetToApplyJobs() {
        return yetToApplyJobs;
    }

    public void setYetToApplyJobs(long yetToApplyJobs) {
        this.yetToApplyJobs = yetToApplyJobs;
    }
}