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
    bt_alterar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String email, senha;

            email = edt_email.getText().toString();
            senha = edt_senha.getText().toString();

            Intent a = new Intent(ForgotPassword.this, EmailConfirm.class);



            if (email.contains("@") && email.contains(".") && senha.length() <= 8){
                b = new Bundle();
                b.putString("email", email);
                b.putString("senha", senha);
                a.putExtras(b);
                startActivity(a);
            }else if (senha.length()> 8){
                Toast.makeText(ForgotPassword.this, "A senha pode ter no máximo 8 caracteres", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ForgotPassword.this, "O e-mail ou senha estão incorretos", Toast.LENGTH_SHORT).show();
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