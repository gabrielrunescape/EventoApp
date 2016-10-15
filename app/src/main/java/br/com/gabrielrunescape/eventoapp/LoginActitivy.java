package br.com.gabrielrunescape.eventoapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONObject;

import br.com.gabrielrunescape.eventoapp.Controlller.ConnectionAsync;
import br.com.gabrielrunescape.eventoapp.Controlller.JSONClass;

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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    new ConnectionAsync().execute("http://192.168.180.135:3000/api/Usuarios");
                }
            }
        });
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        Toast toast;

        if (networkInfo != null && networkInfo.isConnected()) {
            toast =  Toast.makeText(this, "Você está conectado!", Toast.LENGTH_LONG);;
            toast.show();

            return true;
        } else {
            toast =  Toast.makeText(this, "Você não está conectado!", Toast.LENGTH_LONG);;
            toast.show();

            return false;
        }
    }
}
