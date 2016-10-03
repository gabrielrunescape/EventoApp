package br.com.gabrielrunescape.eventoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent intent = getIntent();
        ItemVideo itemVideo = (ItemVideo) intent.getSerializableExtra("Aula");

        TextView titulo = (TextView) findViewById(R.id.Titulo);
        TextView data = (TextView) findViewById(R.id.Data);

        titulo.setText(itemVideo.getTitulo());
        data.setText(itemVideo.getData());
    }
}
