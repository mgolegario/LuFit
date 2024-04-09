package com.example.lufit;

import android.content.Intent;
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

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    TextView redirect_forgPass;
    ImageView bt_voltar;
    Button btn_entrar;
    EditText edt_email;
    EditText edt_senha;
    DBHelper DB;
    ArrayList<String> infos;
    String usuario;
    String altura;
    String peso ;
    String projeto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();



        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        btn_entrar = findViewById(R.id.bt_entrar);
        DB = new DBHelper(this);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

                  String email, senha;
                  email = edt_email.getText().toString();
                  senha = edt_senha.getText().toString();

                  if (email.equals("")||senha.equals("")){
                      Toast.makeText(Login.this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
                  }else{
                      Boolean checkuser = DB.checkEmailPassword(email, senha);
                      if (checkuser == true){
                          Toast.makeText(Login.this, "O Login foi bem sucedido", Toast.LENGTH_SHORT).show();

                          ArrayList<Model> arrInfos = DB.fetchData(email);


                                  usuario = arrInfos.get(0).usuario;
                                  altura = arrInfos.get(0).altura;
                                  peso = arrInfos.get(0).peso;
                                 projeto = arrInfos.get(0).projeto;


                          Intent a = new Intent(Login.this, Home.class);
                            redirect_forgPass.setText(usuario + projeto + altura + peso);

                          Bundle b = new Bundle();
                          b.putString("nome", email);
                          b.putString("altura", altura);
                          b.putString("peso", peso);
                          b.putString("projeto", projeto);
                          a.putExtras(b);

                          startActivity(a);
                      }else{
                          Toast.makeText(Login.this, "O Login falhou", Toast.LENGTH_SHORT).show();
                      }
                  }

                  }

      });

        redirect_forgPass = findViewById(R.id.tv_forgPassw);
        SpannableString conteudo= new SpannableString("Esqueci minha Senha");
        conteudo.setSpan(new UnderlineSpan(), 0, conteudo.length(), 0);
        redirect_forgPass.setText(conteudo);
        redirect_forgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgotPassword.class);
                startActivity(i);
            }
        });


  bt_voltar = findViewById(R.id.bt_voltar);
  bt_voltar.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
       Intent i = new Intent(Login.this, ComecarConversa.class);
       startActivity(i);
       }
  });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}