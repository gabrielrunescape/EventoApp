package br.com.gabrielrunescape.eventoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import br.com.gabrielrunescape.eventoapp.controllers.LoginController;

public class LoginActitivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_actitivy);

        EditText etLogin = (EditText) findViewById(R.id.etLogin);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActitivy.this, SigninActivity.class));
            }
        });
        //LoginController login = new LoginController(etLogin, etPassword, btnLogin, btnSignIn, this);
    }
}
