package br.com.gabrielrunescape.eventoapp.Controlller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 08/10/2016.
 *
 *      Classe que controla o fluxo de informação através do protocólo HTTP e HTTPS onde se encontra
 * o `webservice` da aplicação online.
 *      O método de envio é descrito inicialmente através de cada função para que possa ser enviado
 * através do protocólo HTTP/HTTPS. Sempre se deve receber os valores em JSON do WebService ou de
 * qualquer um outro externo.
 */

public class Connection {
    public static String postDados(String link, String paramentros) {
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Content-Lenght", Integer.toString(paramentros.getBytes().length));
            connection.setRequestProperty("Content-Lenguage", "pt-BR");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

            dataOutputStream.writeBytes(paramentros);
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();

            return resposta.toString();
        } catch (Exception ex) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
