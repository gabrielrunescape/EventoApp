package br.com.gabrielrunescape.eventoapp.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import br.com.gabrielrunescape.eventoapp.DetalheActivity;
import br.com.gabrielrunescape.eventoapp.ItemVideo;
import br.com.gabrielrunescape.eventoapp.MainActivity;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 15/10/2016.
 *
 *      Objeto controlador de um usuario no sistema.
 */

public class Usuario extends AsyncTask<String, Void, String> {
    private int ID;
    private String login;
    private String senha;
    private String Email;
    private char Sexo;
    private long CPF;
    private ArrayList<Usuario> array;

    public Usuario() {

    }

    /**
     * Metódo construtor do objeto, permitindo inicializar o objeto através de seu login e senha
     *
     * @param login nome de usuário
     * @param senha senha do usuário
     */
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    /**
     * Metódo construtor do objeto, permitindo inicializar o objeto de usuário.
     *
     * @param ID código identificador do usuário
     * @param login nome de usuário
     * @param senha senha do usuário
     * @param email do usuário
     * @param CPF Cadastro de Pessoa Fisíca
     * @param sexo do usuário
     */
    public Usuario(int ID, String login,  String senha, String email, long CPF, char sexo) {
        this.ID = ID;
        this.senha = senha;
        this.login = login;
        this.Email = email;
        this.CPF = CPF;
        this.Sexo = sexo;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = "http://192.168.180.135:3000/users/";

            HttpClient client = new DefaultHttpClient();
            HttpResponse resp = client.execute(new HttpGet(url));

            InputStream input = resp.getEntity().getContent();

            if (input != null) {
                this.array = this.getJSON(input);
            }

            input.close();
        } catch (IOException | NetworkOnMainThreadException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public char getSexo() {
        return Sexo;
    }

    public void setSexo(char sexo) {
        Sexo = sexo;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public ArrayList<Usuario> getArray() {
        return array;
    }

    public void setArray(ArrayList<Usuario> array) {
        this.array = array;
    }


    /**
     * Converte um objeto recebido em InputStream para uma String
     *
     * @param inputStream valor a ser convertido
     * @return String a ser retornada após a conversão
     * @throws IOException Imprime mensagem de erro durante o procedimento;
     */
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();

        return result;

    }

    /**
     *      Tenta converter um recebimento em InputStream em um ArrayList com os usuários.
     *      Chama o metódo `convertInputStreamToString` para realizar a conversão das informações
     * para armazenar as mesmas em um ArrayList.
     *
     * @param inputStream Entrada das informações a serem convertidas
     * @return um array com as informações do usuário caso não dê erro
     */
    public ArrayList<Usuario> getJSON(InputStream inputStream) {
        ArrayList<Usuario> usuario = new ArrayList<>();

        try {
            String string = this.convertInputStreamToString(inputStream);

            JSONArray json = new JSONArray(string);
            JSONObject objeto;

            for (int i = 0; i < json.length(); i++) {
                Usuario usr = new Usuario();
                objeto = new JSONObject(json.getString(i));

                usr.setID(objeto.getInt("ID"));
                usr.setLogin(objeto.getString("Login"));
                usr.setSenha(objeto.getString("Senha"));
                usr.setEmail(objeto.getString("Email"));
                usr.setCPF(objeto.getLong("CPF"));
                usr.setSexo(objeto.getString("Sexo").charAt(0));

                usuario.add(usr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    /**
     *      Realiza a verificação do login e senha através do `Array` com as informações passadas
     * por paramêtro com a lista de todos os usuários. Caso retorne verdadeiro, as informações estão
     * válidas, senão, continua verificando.
     *
     * @return se o usuário está valido ou não
     */
    public boolean matchLogin(String login, String senha) {
        boolean session = false;

        for (Usuario u: array) {
            if (login.equals(u.getLogin())) {
                if (senha.equals(u.getSenha())) {
                    session = true;
                } else {
                    session = false;
                }
            } else {
                if (session != true) {
                    session = false;
                }
            }
        }

        return session;
    }
}
