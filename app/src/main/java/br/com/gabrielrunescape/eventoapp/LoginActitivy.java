package br.com.gabrielrunescape.eventoapp;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import br.com.gabrielrunescape.eventoapp.Controlller.ConnectionAsync;
import br.com.gabrielrunescape.eventoapp.Model.Usuario;

public class LoginActitivy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_actitivy);

        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActitivy.this, SigninActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    String url = "http://192.168.180.135:3000/users/";
                    String params = null;

                    String login = etLogin.getText().toString();
                    String senha = etPassword.getText().toString();

                    if (login.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio!", Toast.LENGTH_LONG).show();
                    } else {
                        params = login + "/" + senha;

                        new ConnectionAsync(params).execute(url);
                    }



                    /*Usuario usuario = new Usuario(lgn, snh);
                    usuario.execute("");

                    Toast.makeText(LoginActitivy.this, "Value: " + usuario.matchLogin(lgn, snh), Toast.LENGTH_LONG).show();

                    if (usuario.matchLogin(lgn, snh)) {
                        Intent intent = new Intent(LoginActitivy.this, MainActivity.class);

                        startActivity(intent);
                    }*/
                }
            }
        });
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast toast =  Toast.makeText(this, "Você não está conectado!", Toast.LENGTH_LONG);
            toast.show();

            return false;
        }
    }
}
