package br.com.gabrielrunescape.eventoapp.Controlller;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.NetworkOnMainThreadException;
import android.widget.Toast;
import android.content.Context;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by gabriel on 12/10/16.
 */

public class JSONClass  {
    InputStream input = null;
    JSONObject jObect = null;
    String json = "";

    // check network connection
    public boolean isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        Toast toast;

        if (networkInfo != null && networkInfo.isConnected()) {
            toast =  Toast.makeText(context, "Você está conectado!", Toast.LENGTH_LONG);;
            toast.show();

            return true;
        } else {
            toast =  Toast.makeText(context, "Você não está conectado!", Toast.LENGTH_LONG);;
            toast.show();

            return false;
        }
    }

    //Recebe sua url
    public JSONObject getJSONFromUrl(Context context) {
        try {
            if (isConnected(context)) {
                String url = "http://192.168.180.135:3000/api/Usuarios";

                HttpClient client = new DefaultHttpClient();
                HttpResponse resp = client.execute(new HttpGet(url));

                input = resp.getEntity().getContent();

                if (input != null) {
                    System.out.println(input.toString());
                }

                input.close();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NetworkOnMainThreadException ex) {
            ex.printStackTrace();
        }


        /*try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);

            HttpResponse res = client.execute(get);
            HttpEntity entity = res.getEntity();

            input = entity.getContent();
            System.out.println(input);
        } catch (Exception ex) {
            Toast toast = Toast.makeText(context, ex.getStackTrace().toString(), Toast.LENGTH_LONG);
            ex.printStackTrace();

            toast.show();
        }*/

        /*try {
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
        }*/

        // retorna o objeto
        return jObect;

    }
}
