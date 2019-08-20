package table;

import java.util.HashMap;
import java.util.Map;

/**
 * hujun
 * 模拟持久化执行中的任务
 * 2019/8/20
 */
public class RunJobTable {
    private static final Map runJobTable=new HashMap();
    private RunJobTable(){}
    public static Map getInstance()
    {
        return runJobTable;
    }
}
