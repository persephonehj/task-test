package channel;

import enums.JobStatus;
import job.Job;
import table.FinishJobTable;
import table.RunJobTable;

/**
 * hujun
 * 终端类
 * 2019/8/20
 */
public class Terminal extends Thread{
    /**
     * 所属终端
     */
    private String terminalId;
    private Channel channel;

    private String lock="";
    public Terminal(Channel channel){
        terminalId = Math.random()+"";
        this.channel = channel;
        System.out.println("在管道"+channel.getChannelId()+"下创建终端"+terminalId);
    }

    public void run(){
        while(true) {
            try {
                    //从所属通道中获取任务
                    Job job = channel.getJob();
                    if (null == job) {
                        /**
                         * 如果通道中没有可执行的任务则阻塞 等待通道加入任务时唤醒
                         */
                        try {
                            System.out.println("终端线程阻塞");
                            synchronized (lock) {
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //执行任务
                        System.out.println("终端"+terminalId+"执行job:" + job.getJobId()+" 耗时："+job.getCostTime());
                        Thread.sleep(job.getCostTime());
                        finishJob(job);
                    }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 任务执行成功，将任务移入历史
     * TODO 做事务处理
     */
    public void finishJob(Job job){
        job.setJobStatus(JobStatus.FINISH);
        FinishJobTable.getInstance().put(job.getJobId(),job);
        RunJobTable.getInstance().remove(job.getJobId());
    }
    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
