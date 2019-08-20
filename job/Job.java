package job;

import enums.JobStatus;
import enums.JobType;

/**
 * hujun
 * 任务类
 * 2019/8/20
 */
public class Job {
    /**
     * 任务id
     */
    private String jobId;
    /**
     * 任务类型
     */
    private JobType jobType;
    /**
     * 任务总耗时数
     */
    private int costTime;

    /**
     * 任务执行状态
     */
    private JobStatus jobStatus = JobStatus.CREATE;
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public int getCostTime() {
        return costTime;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }
}
