import job.JobFactory;

/**
 * hujun
 * 创建任务
 * 2019/8/20
 */
public class CreateJob extends Thread {

    private Scheduler scheduler;

    public CreateJob(Scheduler scheduler){
        this.scheduler = scheduler;
    }
    public void run() {
        int i = 0;
        JobFactory jobFactory = new JobFactory();
        while(i<3) {
            try {
                for(int j = 0;j<30;j++){
                    scheduler.insertJob(jobFactory.newJob());
                }
                System.out.println("创建了30个任务");
                Thread.sleep(30000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}