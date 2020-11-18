/**
 * 使用方法增强 静态代理类
 * Created by yuchen.wu on 2020-11-14
 */

public class StaticProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(userService, new MethodEnhanceHandler() {

            @Override
            public void doThrowing(Exception e) {
                System.out.println("方法抛出异常");
            }

            @Override
            public void doBefore() {
                System.out.println("方法执行前");
            }

            @Override
            public void doAfter() {
                System.out.println("方法执行后");
            }
        });
        proxy.saveUser();
    }

    interface UserService {
        void saveUser();
    }

    static class UserServiceImpl implements UserService{

        @Override
        public void saveUser() {
            System.out.println("用户数据已保存");
        }
    }

    static class UserServiceProxy implements UserService{

        private final MethodEnhanceHandler methodEnhanceHandler;
        private final UserService target;

        public UserServiceProxy(UserService target, MethodEnhanceHandler methodEnhanceHandler) {
            this.methodEnhanceHandler = methodEnhanceHandler;
            this.target = target;
        }

        @Override
        public void saveUser() {
            try {
                methodEnhanceHandler.doBefore();
                target.saveUser();
            } catch (Exception e) {
                methodEnhanceHandler.doThrowing(e);
            } finally {
                methodEnhanceHandler.doAfter();
            }
        }
    }

}
