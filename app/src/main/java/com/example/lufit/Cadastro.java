package com.example.lufit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cadastro extends AppCompatActivity {

    Button btn_cadastro;
    EditText edt_usuario, edt_senha, edt_email, edt_altura, edt_peso, edt_projeto;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        edt_usuario = findViewById(R.id.edt_usuario);
        edt_email = findViewById(R.id.edt_email_cadastro);
        edt_senha = findViewById(R.id.edt_senha_cadastro);
        edt_altura = findViewById(R.id.edt_altura);
        edt_peso = findViewById(R.id.edt_peso);
        edt_projeto = findViewById(R.id.edt_plano);
        btn_cadastro = findViewById(R.id.btn_cadastro);
        DB = new DBHelper(this);
        Bundle b = getIntent().getExtras();

        String nome = b.getString("nome");
        String altura = b.getString("altura");
        String peso = b.getString("peso");
        String projeto = b.getString("projeto");

        edt_email.setText(projeto + altura + peso);

        edt_usuario.setText(nome);
        edt_altura.setText(altura);
        edt_peso.setText(peso);
        edt_projeto.setText(projeto);
        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = edt_usuario.getText().toString();
                String email = edt_email.getText().toString();
                String senha = edt_senha.getText().toString();
                String altura = edt_altura.getText().toString();
                String peso = edt_peso.getText().toString();
                String projeto = edt_projeto.getText().toString();


                if (usuario.equals("")||email.equals("")||senha.equals("")){
                    Toast.makeText(Cadastro.this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemail = DB.checkEmail(email);
                    if (checkemail == false){
                        Boolean insert = DB.insertData(email, senha, usuario, altura, peso, projeto);
                        if (insert == true){
                            Toast.makeText(Cadastro.this, "O cadastro foi bem sucedido", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Cadastro.this, Conta.class);
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





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}