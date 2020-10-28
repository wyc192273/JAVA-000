import java.io.IOException;

/**
 * Created by yuchen.wu on 2020-10-28
 */

public class Work2 {

    public static void main(String[] args) throws IOException {
        System.out.println(HttpClientUtil.get("http://localhost:8801"));
    }

}
