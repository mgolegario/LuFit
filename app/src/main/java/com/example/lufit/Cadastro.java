package com.example.lufit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cadastro extends AppCompatActivity {

    Button btn_cadastro;
    EditText edt_usuario, edt_senha, edt_email;
    ImageView bt_voltar;
    TextView redirect_login;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        edt_usuario = findViewById(R.id.edt_usuario);
        edt_email = findViewById(R.id.edt_email_cadastro);
        edt_senha = findViewById(R.id.edt_senha_cadastro);
        btn_cadastro = findViewById(R.id.btn_cadastro);
        DB = new DBHelper(this);
        Bundle b = getIntent().getExtras();

        String nome = b.getString("nome");
        Float altura = b.getFloat("altura");
        Float peso = b.getFloat("peso");
        String projeto = b.getString("projeto");

        edt_usuario.setText(nome);

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = edt_usuario.getText().toString();
                String email = edt_email.getText().toString();
                String senha = edt_senha.getText().toString();
                b.putString("email", email);
                b.putString("senha", senha);

                if (usuario.equals("")||email.equals("")||senha.equals("")){
                    Toast.makeText(Cadastro.this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemail = DB.checkEmail(email);
                    if (checkemail == false){
                        Boolean insert = DB.insertData(email, senha, usuario, altura, peso, projeto);
                        if (insert == true){
                            Toast.makeText(Cadastro.this, "O cadastro foi bem sucedido", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Cadastro.this, Conta.class);
                            i.putExtras(b);
                            startActivity(i);
                        }else{
                            Toast.makeText(Cadastro.this, "O cadastro falhou", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Cadastro.this, "O usuário já existe", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        redirect_login = findViewById(R.id.tv_toLogin);
        SpannableString conteudo= new SpannableString("Já tem uma conta? Login");
        conteudo.setSpan(new UnderlineSpan(), 0, conteudo.length(), 0);
        redirect_login.setText(conteudo);
        redirect_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cadastro.this, Login.class);
                startActivity(i);
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