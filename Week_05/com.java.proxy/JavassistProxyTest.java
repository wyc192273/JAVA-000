import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * Created by yuchen.wu on 2020-11-14
 */

public class JavassistProxyTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
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
        Bird proxy = (Bird) JavassistDynamicProxy.createProxy(Bird.class, methodEnhanceHandler);
        proxy.fly();
        proxy.call();
    }

    public static class JavassistDynamicProxy {

        public static Object createProxy(Class<?> target, MethodEnhanceHandler methodEnhanceHandler)
                throws IllegalAccessException, InstantiationException {
            ProxyFactory factory = new ProxyFactory();
            factory.setSuperclass(target);
            factory.setFilter(new MethodFilter() {

                @Override
                public boolean isHandled(Method m) {
                    if (m.getName().equals("fly")) {
                        return true;
                    }
                    return false;
                }
            });
            Class proxyClass = factory.createClass();
            Object proxy = proxyClass.newInstance();
            ProxyObject proxyObject = (ProxyObject) proxy;
            proxyObject.setHandler(new InternalMethodHandler(methodEnhanceHandler));
            return proxy;
        }
    }

    private static class InternalMethodHandler implements MethodHandler{

        private MethodEnhanceHandler methodEnhanceHandler;

        public InternalMethodHandler(MethodEnhanceHandler methodEnhanceHandler) {
            this.methodEnhanceHandler = methodEnhanceHandler;
        }

        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            Object retVal = null;
            try {
                methodEnhanceHandler.doBefore();
                // o 是 Bird$$EnhancerByCGLIB$$a77452a7@4dfa3a9d
                retVal = proceed.invoke(self, args);
            } catch (Exception e) {
                methodEnhanceHandler.doThrowing(e);
            } finally {
                methodEnhanceHandler.doAfter();
            }
            return retVal;
        }
    }

}
