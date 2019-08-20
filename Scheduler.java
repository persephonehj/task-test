import channel.Channel;
import enums.JobType;
import job.Job;
import table.RunJobTable;

import java.util.ArrayList;
import java.util.List;

/**
 * hujun
 * 任务调度中台
 * 2019/8/20
 */
public class Scheduler extends Thread {

    /**
     * 存储的任务列表
     */
    private List<Job> jobList = new ArrayList<>();
    /**
     * 通道列表
     */
    private List<Channel> channelList = new ArrayList<>();

    public void run(){

        while(true) {
            if (0 != jobList.size()) {
                /**
                 * 使用最小任务数算法 获取当前存储任务数最少的通道
                 */
                synchronized (jobList) {
                    Job job = jobList.get(0);
                    Channel channel = getMinJobChannel(job.getJobType());
                    System.out.println("通道中任务数最少的是" + channel.getChannelId());
                    /**
                     * 如果通道都满了则阻塞1分钟
                     */
                    if (channel.getJobList().size() == channel.getMaxJobs()) {
                        System.out.println("通道满了，阻塞1分钟");
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                        }
                    } else {
                        channel.insertJob(jobList.remove(0));
                    }
                }
            }
        }
    }

    /**
     * 添加任务，并做任务持久化处理
     */
    public void insertJob(Job job){
        synchronized (jobList){
            jobList.add(job);
            RunJobTable.getInstance().put(job.getJobId(),job);
        }

    }

    /**
     * 获取存留任务最小的通道
     * @return
     * @param jobType
     */
    public Channel getMinJobChannel(JobType jobType){
        Channel minJobChannel = null;
        int minNum = 0;
        for(Channel channel:channelList){
            if(!channel.getChannelType().equals(jobType)){
                continue;
            }
            if(null==minJobChannel){
                minJobChannel = channel;
                minNum = channel.getJobList().size();
            }else{
                if(channel.getJobList().size()<minNum){
                    minJobChannel = channel;
                    minNum = channel.getJobList().size();
                }
            }
        }


        return minJobChannel;
    }

    public void setChannel(Channel channel){
        System.out.println("添加通道");
        channelList.add(channel);
    }
}
