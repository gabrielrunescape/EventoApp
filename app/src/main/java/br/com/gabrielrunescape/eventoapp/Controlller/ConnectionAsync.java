package br.com.gabrielrunescape.eventoapp.Controlller;

import java.io.IOException;
import java.io.InputStream;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import android.os.NetworkOnMainThreadException;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 14/10/16.
 *
 *      Classe de conexão assincrona, pois o desempenho de rede através do protóloco HTTP e HTTP
 * devem ser através de uma tarefa assincrona.
 *      O método de envio é descrito inicialmente através de cada função para que possa ser enviado
 * através do protocólo HTTP/HTTPS. Sempre se deve receber os valores em JSON do WebService ou de
 * qualquer um outro externo.
 *      O principal método da classe é o `doInBackground` onde tudo contido ali dentro será executada
 * pela classe, sendo assim, substituindo os tradicionais métodos de construção. Neste caso, ele é
 * usado como detentor do envio da requisição de acesso ao webservice e recebimento dos pacotes.
 */

public class ConnectionAsync extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {
            String url = "http://192.168.180.135:3000/api/Usuarios";

            HttpClient client = new DefaultHttpClient();
            HttpResponse resp = client.execute(new HttpGet(url));

            InputStream input = resp.getEntity().getContent();

            if (input != null) {
                result = convertInputStreamToString(input);
            } else {
                result = "Não funciona!";
            }

            input.close();
        } catch (IOException | NetworkOnMainThreadException ex) {
            ex.printStackTrace();
            result = ex.getMessage();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println(result);
    }

    // Trecho copiado de `http://hmkcode.com/android-internet-connection-using-http-get-httpclient/`
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();

        return result;

    }
}
