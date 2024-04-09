package com.example.lufit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotPassword extends AppCompatActivity {

    ImageView bt_voltar;
    Button bt_alterar;
    EditText edt_email;
    EditText edt_senha;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);

        bt_alterar = findViewById(R.id.bt_alterar);
        edt_email = findViewById(R.id.edt_email2);
        edt_senha = findViewById(R.id.edt_senha2);
        DBHelper DB = new DBHelper(this);
    bt_alterar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String email, senha;

            email = edt_email.getText().toString();
            senha = edt_senha.getText().toString();

            Intent a = new Intent(ForgotPassword.this, EmailConfirm.class);

            if (email.equals("")||senha.equals("")){
                Toast.makeText(ForgotPassword.this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
            }else{
                Boolean checkemail = DB.checkEmail(email);
                if (checkemail == true){
                    Boolean update = DB.updateDataSenha(email, senha);
                    if (update == true){
                        Toast.makeText(ForgotPassword.this, "A senha foi alterada com sucesso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ForgotPassword.this, "Não foi possível alterar a senha", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ForgotPassword.this, "O usuário não existe", Toast.LENGTH_SHORT).show();
                }
            }

        }

    });



        bt_voltar = findViewById(R.id.bt_voltar);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}