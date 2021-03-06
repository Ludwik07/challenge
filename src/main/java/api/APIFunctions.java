package api;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFunctions {

    public String get (String urlTarget, int assertCode){
        String response;
        try {
            URL url = new URL(urlTarget);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != assertCode) {
                throw new Exception("Error : Response code : "+ connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            response = br.readLine();
            connection.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
