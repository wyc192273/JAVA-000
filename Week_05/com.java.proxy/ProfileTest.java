
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.Config;
import com.Config2;

/**
 * Created by yuchen.wu on 2020-11-17
 */

public class ProfileTest {

    public static void main(String[] args)  {
        xmlTest();
        annotationTest();
    }

    private static void xmlTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("context4.xml");
        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        configurableEnvironment.setActiveProfiles("stag");
        applicationContext.refresh();
        Config config = applicationContext.getBean("config", Config.class);
        System.out.println(config.getHello());
    }

    private static void annotationTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("context5.xml");
        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        configurableEnvironment.setActiveProfiles("test");
        applicationContext.refresh();
        Config2 config = applicationContext.getBean("config2", Config2.class);
        System.out.println(config.getHello());
    }

}
