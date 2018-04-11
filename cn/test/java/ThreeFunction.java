package cn.test.java;

/**
 * Created by SJ217110601 on 2018/1/19.
 */
@FunctionalInterface
public interface ThreeFunction <T,U,K,R>{
    R appley(T t,U u,K k);
}
