import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDk动态代理实现
 * Created by yuchen.wu on 2020-11-14
 */

public class JdkProxyTest {

    public static void main(String[] args) {
        Bird bird = new Bird();
        MethodEnhanceHandler methodEnhanceHandler = new MethodEnhanceHandler() {

            @Override
            public void doThrowing(Exception e) {
                System.out.println("抛出异常");
                e.printStackTrace();
            }

            @Override
            public void doBefore() {
                System.out.println("方法执行前");
            }

            @Override
            public void doAfter() {
                System.out.println("方法执行后");
            }
        };
        Flyable proxy = (Flyable) JDKDynamicProxy.createProxy(bird, methodEnhanceHandler);
        Voiceable voiceable = (Voiceable) JDKDynamicProxy.createProxy(bird, methodEnhanceHandler);
        proxy.fly();
        voiceable.call();
    }

    public static class JDKDynamicProxy{

        public static Object createProxy(Object target, MethodEnhanceHandler methodEnhanceHandler) {
            return Proxy.newProxyInstance(JDKDynamicProxy.class.getClassLoader(), target.getClass().getInterfaces(),
                    new InternalInvocationHandler(target, methodEnhanceHandler));
        }

        private static class InternalInvocationHandler implements InvocationHandler{

            private MethodEnhanceHandler methodEnhanceHandler;
            private Object target;

            public InternalInvocationHandler(Object target, MethodEnhanceHandler methodEnhanceHandler) {
                this.methodEnhanceHandler = methodEnhanceHandler;
                this.target = target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object retVal = null;
                try {
                    methodEnhanceHandler.doBefore();
                    //这里不能写proxy，否则会一直嵌套调用代理类的方法，导致最后栈溢出
                    retVal = method.invoke(target, args);
                } catch (Exception e) {
                    methodEnhanceHandler.doThrowing(e);
                } finally {
                    methodEnhanceHandler.doAfter();
                }
                return retVal;
            }
        }
    }


}
