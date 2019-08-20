package channel;

import enums.JobType;

import java.util.Random;

/**
 * hujun
 * 通道工厂类
 * 2019/8/20
 */
public class ChannelFactory {

    public Channel newChannel(int terminalNums,int maxJobs){
        Random random = new Random();
        Channel channel = new Channel();
        channel.setChannelId(Math.random()+"");
        channel.setMaxJobs(maxJobs);
        channel.setChannelType(JobType.values()[random.nextInt(2)]);
        System.out.println("通道类型："+channel.getChannelType());
        for(int i=0;i<terminalNums;i++){
            Terminal terminal = new Terminal(channel);
            channel.getTerminalList().add(terminal);
            terminal.start();
        }
        System.out.println("创建通道"+channel.getChannelId());
        return channel;
    }
}
