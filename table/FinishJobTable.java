package table;

import java.util.HashMap;
import java.util.Map;

/**
 * hujun
 * 模拟持久化结束的任务
 * 2019/8/20
 */
public class FinishJobTable {
    private static final Map finishJobTable=new HashMap();
    private FinishJobTable(){}
    public static Map getInstance()
    {
        return finishJobTable;
    }
}
