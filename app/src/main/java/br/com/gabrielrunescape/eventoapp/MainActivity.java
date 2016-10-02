package br.com.gabrielrunescape.eventoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] array = getResources().getStringArray(R.array.array);

        LinearLayout lista = (LinearLayout) findViewById(R.id.LinearLayout);

        for (String vetor : array) {
            TextView textView = new TextView(this);

            textView.setText(vetor);
            lista.addView(textView);
        }
    }
}
