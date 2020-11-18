/**
 * Created by yuchen.wu on 2020-11-14
 */

public interface MethodEnhanceHandler {


    /**
     * 抛出异常时调用
     */
    void doThrowing(Exception e);

    /**
     * 目标方法执行前调用
     */
    void doBefore();

    /**
     * 目标方法执行后调用
     */
    void doAfter();
}
