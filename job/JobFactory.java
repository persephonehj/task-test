package job;

import enums.JobType;

import java.util.Random;

/**
 * hujun
 * 任务工厂类
 * 2019/8/20
 */
public class JobFactory {

    public Job newJob(){

        Random random = new Random();
        Job job = new Job();
        job.setJobId(Math.random()+"");
        job.setJobType(JobType.values()[random.nextInt(2)]);
        job.setCostTime(random.nextInt(20)*1000);
        return job;
    }
}
