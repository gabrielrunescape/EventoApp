package br.com.gabrielrunescape.eventoapp.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 15/10/2016.
 *
 *      Objeto controlador de um usuario no sistema.
 */

public class Usuario {
    private int ID;
    private String login;
    private String senha;
    private String Email;
    private char Sexo;
    private long CPF;
    private Date data_Cadastro;

    public Usuario() {

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
        Email = email;
        this.CPF = CPF;
        Sexo = sexo;
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
}
