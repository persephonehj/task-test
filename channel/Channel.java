package channel;

import enums.JobType;
import job.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * hujun
 * 通道类
 * 2019/8/20
 */
public class Channel {
    /**
     * 通道标识
     */
    private String channelId;
    /**
     * 存储最大任务数
     */
    private int maxJobs;
    /**
     * 通道类型
     */
    private JobType channelType;
    /**
     * 通道存储任务列表
     */
    private Vector<Job> jobList=new Vector<>();
    /**
     * 通道所属终端列表
     */
    private List<Terminal> terminalList = new ArrayList<>();
    /**
     * 调度器调用，将任务放入通道
     */
    public void insertJob(Job job){
        System.out.println("任务"+job.getJobId()+"进入管道"+channelId+"任务类型："+job.getJobType()+"通道类型："+channelType);
        jobList.add(job);

        for (Terminal terminal : terminalList) {
            synchronized (terminal.getLock()) {
                terminal.getLock().notify();
            }
        }
    }

    /**
     * 终端调用，获取通道里任务
     */
    public synchronized Job getJob(){
        if(jobList.size()==0){
            return null;
        }
        return jobList.remove(0);
    }

    public JobType getChannelType() {
        return channelType;
    }

    public void setChannelType(JobType channelType) {
        this.channelType = channelType;
    }

    public int getMaxJobs() {
        return maxJobs;
    }

    public void setMaxJobs(int maxJobs) {
        this.maxJobs = maxJobs;
    }

    public Vector<Job> getJobList() {
        return jobList;
    }

    public void setJobList(Vector<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Terminal> getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List<Terminal> terminalList) {
        this.terminalList = terminalList;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
