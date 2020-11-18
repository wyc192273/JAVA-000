/**
 * 代码写死 静态代理类
 * Created by yuchen.wu on 2020-11-14
 */

public class CodeStaticProxyTest {

    public static void main(String[] args) {
        //普通执行代码
        ActionClass actionClass = new ActionClass();
        actionClass.hello("world");
        //静态代理
        ProxyActionClass proxyActionClass = new ProxyActionClass(actionClass);
        proxyActionClass.hello("world");
    }

    static class ActionClass {

        public void hello(String word) {
            System.out.println("hello, " + word);
        }

    }

    static class ProxyActionClass {

        private ActionClass actionClass;

        public ProxyActionClass(ActionClass actionClass) {
            this.actionClass = actionClass;
        }

        private void preMethod() {
            System.out.println("pre method");
        }

        private void afterMethod() {
            System.out.println("after method");
        }

        public void hello(String word) {
            preMethod();
            actionClass.hello(word);
            afterMethod();
        }
    }
}
