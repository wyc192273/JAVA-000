import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by yuchen.wu on 2020-11-14
 */

public class CglibDynamicProxyTest {

    public static void main(String[] args) {
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
        Bird bird = (Bird) CglibDynamicProxy.createProxy(Bird.class, methodEnhanceHandler);
        bird.call();
        bird.fly();
    }

    public static class CglibDynamicProxy implements MethodInterceptor{

        private MethodEnhanceHandler methodEnhanceHandler;

        public CglibDynamicProxy(MethodEnhanceHandler methodEnhanceHandler) {
            this.methodEnhanceHandler = methodEnhanceHandler;
        }

        public static Object createProxy(final Class target, MethodEnhanceHandler methodEnhanceHandler) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target);
            CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy(methodEnhanceHandler);
            enhancer.setCallback(cglibDynamicProxy);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Object retVal = null;
            try {
                methodEnhanceHandler.doBefore();
                // o 是 Bird$$EnhancerByCGLIB$$a77452a7@4dfa3a9d
                retVal = methodProxy.invokeSuper(o, objects);
            } catch (Exception e) {
                methodEnhanceHandler.doThrowing(e);
            } finally {
                methodEnhanceHandler.doAfter();
            }
            return retVal;
        }
    }

}
