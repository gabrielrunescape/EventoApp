package br.com.gabrielrunescape.eventoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] array = getResources().getStringArray(R.array.array);

        LinearLayout lista = (LinearLayout) findViewById(R.id.LinearLayout);

        for (final String vetor : array) {
            TextView textView = new TextView(this);
            textView.setText(vetor);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("Aula", vetor);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            lista.addView(textView);
        }
    }
}
