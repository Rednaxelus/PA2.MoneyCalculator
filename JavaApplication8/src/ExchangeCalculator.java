
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ExchangeCalculator {

    public double getResult(String from, String to) throws MalformedURLException, IOException {
        // Setting URL
        String url_str = "https://api.exchangerate-api.com/v4/latest/" + from;

        // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        String str;
        try ( // read from the URL
                Scanner scan = new Scanner(url.openStream())) {
            str = new String();
            while (scan.hasNext()) {
                str += scan.nextLine();
            }
        }

        // build a JSON object
        JSONObject obj = new JSONObject(str);

        // get the result
        double res = obj.getJSONObject("rates").getDouble(to);
        return res;
    }
}
