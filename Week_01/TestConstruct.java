/**
 * Created by yuchen.wu on 2020-10-18
 */

public class TestConstruct {

    private int a = 1;

    public TestConstruct() {
        super();
        int b = a;
    }

    public static void main(String[] args) {
        TestConstruct a = new TestConstruct();
    }
}
