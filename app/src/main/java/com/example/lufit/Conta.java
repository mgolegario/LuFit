package com.example.lufit;

import android.content.Intent;
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

public class Conta extends AppCompatActivity {

    EditText edt_usuario, edt_altura, edt_peso, edt_email, edt_senha;
    Button btn_editar, btn_ok;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conta);

        edt_usuario = findViewById(R.id.edt_nomeEdt);
        edt_altura = findViewById(R.id.edt_alturaEdt);
        edt_peso = findViewById(R.id.edt_pesoEdt);
        edt_email = findViewById(R.id.edt_emailEdt);
        edt_senha = findViewById(R.id.edt_senhaEdt);
        btn_editar = findViewById(R.id.btn_editar);
        btn_ok = findViewById(R.id.btn_ok);
        DB = new DBHelper(this);
        Bundle b = getIntent().getExtras();

        if (b.getString("nome") != null || b.getString("peso") != null || b.getString("altura") != null|| b.getString("projeto") != null) {
            String nome = b.getString("nome");
            edt_usuario.setText(nome);
            Float peso = b.getFloat("peso");
            edt_peso.setText(peso.toString());
            Float altura = b.getFloat("altura");
            edt_altura.setText(altura.toString());
            String email = b.getString("email");
            edt_email.setText(email);
            String senha = b.getString("senha");
            edt_senha.setText(senha);
        }


btn_editar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        edt_usuario.setEnabled(true);
        edt_altura.setEnabled(true);
        edt_peso.setEnabled(true);
        edt_senha.setEnabled(true);
        btn_ok.setVisibility(View.VISIBLE);
        btn_editar.setVisibility(View.INVISIBLE);
    }
});



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = edt_usuario.getText().toString();
                Float altura = Float.valueOf(edt_altura.getText().toString());
                Float peso = Float.valueOf(edt_peso.getText().toString());
                String email = edt_email.getText().toString();
                String senha = edt_senha.getText().toString();

                        if (email.equals("")||senha.equals("")){
                        Toast.makeText(Conta.this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
                        }else{
                        String projeto = IMC(altura, peso);
                        Boolean update = DB.updateData(email, senha, usuario, altura, peso, projeto);
                        if (update == true){
                            Toast.makeText(Conta.this, "Informações alteradas com sucesso", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Conta.this, Home.class);
                            b.putString("nome", usuario);
                            b.putFloat("altura", altura);
                            b.putFloat("peso", peso);
                            b.putString("projeto", projeto);
                            b.putString("email", email);
                            i.putExtras(b);
                            startActivity(i);
                        }else{
                            Toast.makeText(Conta.this, "Não foi possível alterar as informações", Toast.LENGTH_SHORT).show();
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
   public String IMC(Float altura, Float peso){
        float IMC;
        IMC = (float) (peso / Math.pow(altura,2));
        String projeto;
        if (IMC < 18.5){
            projeto = "Ganho de Peso";
        } else if (IMC <= 24.9){
            projeto = "Verão";
        } else if (IMC <= 30) {
            projeto = "Perda de Peso";
        } else {
            projeto = "Saúde";
        }
        return  projeto;
    }
}