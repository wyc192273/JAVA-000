import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.Service;

/**
 * Created by yuchen.wu on 2020-11-14
 */

public class SpringBeanInjectTest {

    public static void main(String[] args) {
        xmlInject();
        beanFactoryInject();
        constructInject();
        setterInject();
        annotationInject();
        configurationInject();
    }

    /**
     * xml方式手动注入到容器内
     */
    private static void xmlInject() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("Context1.xml");
        Bird bird = xmlApplicationContext.getBean("bird", Bird.class);
        bird.fly();
        bird.call();
    }

    /**
     * 自己定义beanDefinition注册到容器内
     */
    private static void beanFactoryInject() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new RootBeanDefinition(Bird.class);
        beanFactory.registerBeanDefinition("bird", beanDefinition);
        Bird bird = beanFactory.getBean(Bird.class);
        bird.fly();
        bird.call();
    }

    /**
     * 使用 构造器方式注入
     */
    private static void constructInject() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("Context2.xml");
        Service service = xmlApplicationContext.getBean("service", Service.class);
        service.query();
    }

    /**
     * 使用 setter方式注入
     */
    private static void setterInject() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("Context2.xml");
        Service service = xmlApplicationContext.getBean("service2", Service.class);
        service.query();
    }

    /**
     * 使用 annotation方式注入
     */
    private static void annotationInject() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("Context2.xml");
        Service service = xmlApplicationContext.getBean("service3", Service.class);
        service.query2();
    }

    /**
     * 使用 configuration方式注入
     */
    private static void configurationInject() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("Context2.xml");
        Service service = xmlApplicationContext.getBean("service3", Service.class);
        service.query4();
    }

}
