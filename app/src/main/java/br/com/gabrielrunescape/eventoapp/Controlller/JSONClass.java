package br.com.gabrielrunescape.eventoapp.Controlller;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import org.json.JSONException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by gabriel on 12/10/16.
 */

public class JSONClass {
    InputStream input = null;
    JSONObject jObect = null;
    String json = "";

    //Recebe sua url
    public JSONObject getJSONFromUrl(String url) {

        //HTTP request
        try {
            // default HttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            input = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            input.close();
            json = sb.toString();

            Log.i("JRF", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // Transforma a String de resposta em um JSonObject
        try {
            jObect = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // retorna o objeto
        return jObect;

    }
}
