import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen.wu on 2020-11-14
 */
@Configuration
public class Bird implements Flyable, Voiceable {

    @Override
    public void fly() {
        System.out.println("i can fly");
    }

    @Override
    public void call() {
        System.out.println("i can call");
    }
}
