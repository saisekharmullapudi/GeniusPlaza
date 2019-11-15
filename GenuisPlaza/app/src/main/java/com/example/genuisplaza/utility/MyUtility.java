package com.example.genuisplaza.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;

public class MyUtility {

    // Download JSON data (string) using HTTP Get Request
    public static String downloadJSONusingHTTPGetRequest(String urlString) {
        String jsonString = null;

        try {
            URL url = new URL(urlString);
            HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();
            if (httpsConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream stream = httpsConnection.getInputStream();
                jsonString = getStringfromStream(stream);
            }
            httpsConnection.disconnect();
        } catch (UnknownHostException e1) {
            e1.getMessage();
        } catch (Exception ex) {
             ex.getMessage();
        }
        return jsonString;
    }

    // Get a string from an input stream
    private static String getStringfromStream(InputStream stream) {
        String line, jsonString = null;
        if (stream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder out = new StringBuilder();
            try {
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
                reader.close();
                jsonString = out.toString();
            } catch (IOException ex) {
                ex.getMessage();
            } catch (Exception e) {
                 e.getMessage();
            }
        }
        return jsonString;
    }
}
