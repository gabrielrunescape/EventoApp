package br.com.gabrielrunescape.eventoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LoginActitivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_actitivy);
    }
}
