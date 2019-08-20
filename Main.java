import channel.ChannelFactory;

public class Main {


    public static void main(String[] args) {

        Scheduler scheduler = init();
        /**
         * 创建任务创建线程
         */
        CreateJob createJob =new CreateJob(scheduler);
        createJob.start();
        scheduler.start();
    }

    /**
     * 初始化任务调度
     */
    public static Scheduler init(){

        Scheduler scheduler = new Scheduler();
        ChannelFactory channelFactory = new ChannelFactory();
        /**
         * 构造一个3个通道 每个通道中可存3个任务 每个通道下2个终端的系统
         */
        for(int i = 0;i<3;i++){
            scheduler.setChannel(channelFactory.newChannel(2,3));
        }
        return scheduler;
    }
}
