# 程序说明文档
## 1、	类图
![Image text](https://raw.githubusercontent.com/persephonehj/task-test/master/images/01.png)
## 2、	流程图
![Image text](https://raw.githubusercontent.com/persephonehj/task-test/master/images/02.png)
## 3、	文件结构说明
![Image text](https://raw.githubusercontent.com/persephonehj/task-test/master/images/03.png)
## 4、	方法说明
### Channel类
insertJob	调度器调用，将任务放入Channel
getJob	Terminal调用，获取通道里任务。有synchronized修饰。将列表顶端的Job返回给终端，并移除。
### Terminal类
run	从Channel中获取Job，并运行。运行结束后对Job持久化如果Channel中没有Job，则开始等待，直到Channel唤醒
finishJob	持久化方法，将任务移入历史，需要事务。
### Scheduler类
run	获取当前存储Job数最少的Channel，将Job发送到Channel。如果Channel都满了（Channel中存储Job数等于通道设置的最大Job数）则阻塞1分钟
insertJob	入口程序调用，添加任务，并做任务持久化处理
getMinJobChannel	获取存留任务最小的Channel
## 5、	算法说明
最少任务数法，获取当前存储Job数最少的Channel，把Job发送给它。
优点：是算法相对简单，保证每个Channel中待运行的Job数保持近似。在每个Channel下的Terminal数量差不多的时候任务运行很均衡。
缺点：某些Job消耗很多的时间或者Channel下的Terminal数量相差很多，尽管Job数平衡了，但是处理量可能差别很大，无法反映出真实的应用负载。
