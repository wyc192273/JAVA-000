import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yuchen.wu on 2020-10-28
 */

public class HttpClientUtil {

    public static String get(String getUrl) throws IOException {
        URL url = new URL(getUrl);
        URLConnection urlConnection = url.openConnection();
        doGet(urlConnection);
        return responseBody(urlConnection);
    }

    private static void doGet(URLConnection urlConnection) throws IOException {
        urlConnection.setDoOutput(true);
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(("Host: " + urlConnection.getURL().getPath()).getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static String responseBody(URLConnection urlConnection) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = urlConnection.getInputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(get("http://localhost:8808/test"));
    }

}
