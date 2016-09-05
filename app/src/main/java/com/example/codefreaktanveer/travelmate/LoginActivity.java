package com.example.codefreaktanveer.travelmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.DataMiddleware;
import model.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private DataMiddleware db;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DataMiddleware(this);
        sharedPreferences=getBaseContext().getSharedPreferences("tourMate",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if(sharedPreferences.getString("userName",null)!=null){
            Intent intent=new Intent(LoginActivity.this,EventActivity.class);
            startActivity(intent);
        }

        txtEmail= (EditText) findViewById(R.id.txtEmailUser);
        txtPassword= (EditText) findViewById(R.id.txtloginPassword);
        btnLogin= (Button) findViewById(R.id.btnLogin);

    }

    public void navigateToRegistration(View view) {
        Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        login();
    }
    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        progressDialog.dismiss();
                        // onLoginFailed();

                        if(db.checkLogin(email,password)){
                            editor.putString("userName",email);
                            editor.commit();
                            Intent intent=new Intent(LoginActivity.this,EventActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Wrong uername or Passeord", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 3000);
    }

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("enter a valid email address");
            valid = false;
        } else {
            txtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            txtPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            txtPassword.setError(null);
        }

        return valid;
    }

}
