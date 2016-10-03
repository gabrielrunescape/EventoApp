package br.com.gabrielrunescape.eventoapp;

import java.io.Serializable;

/**
 * Created by gabriel on 02/10/16.
 */

public class ItemVideo implements Serializable {
    private String Titulo;
    private String Data;
    private String url;

    public ItemVideo(String titulo, String data, String url) {
        Titulo = titulo;
        Data = data;
        this.url = url;
    }

    public String getTitulo() { return Titulo; }

    public void setTitulo(String titulo) { Titulo = titulo; }

    public String getData() { return Data; }

    public void setData(String data) { Data = data; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
