package cn.test.java2;

import java.util.concurrent.RecursiveTask;

/**
 * Created by SJ217110601 on 2018/3/9.
 */
public class AccumulatorRecursiveTask  extends RecursiveTask<Integer>{
    private final  int start;

    private final  int end;

    private final  int []date;
    private final  int LIMIT=3;

    public AccumulatorRecursiveTask(int start, int end, int[] date) {
        this.start = start;
        this.end = end;
        this.date = date;
    }

    @Override
    protected Integer compute() {
        return null;
    }
}
