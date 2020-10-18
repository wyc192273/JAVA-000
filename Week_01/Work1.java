import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by yuchen.wu on 2020-10-17
 */

public class Work1 {

    private int a = 1;
    private final static int B = 2;
    public int c = 3;
    public final static int D = 4;
    public final int e = 5;

    public final static int f;

    public final int g;
    private static int h;

    static {
        f = 6;
    }

    {
        g = 7;
    }

    public static void main(String[] args) throws IOException, NoSuchFieldException {
        System.out.println(B + D);
        Work1 work1 = new Work1();
        int r1 = work1.e * D;
        int r2 = work1.a / D;
        int r3 = work1.c - work1.e;
        r3 = work1.e + work1.g;
        int r4 = h++;
        int r5 = h += 1;
        int r6 = 7;
        int r7 = r6  + 1;
        System.out.println(r6);
        if (r4 == 3) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        int[] array = new int[r1];
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        for (int i : array) {
            System.out.println(i);
        }
        File file = new File("");
        try (InputStream in = new FileInputStream(file)) {
            System.out.println(in.read());
        }
        Field field = File.class.getDeclaredField("path");
        System.out.println(field.getName());
    }

}
