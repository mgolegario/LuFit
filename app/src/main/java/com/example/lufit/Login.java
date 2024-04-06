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
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    TextView redirect_forgPass;
    ImageView bt_voltar;
    Button btn_entrar;
    EditText edt_email;
    EditText edt_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();



        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        btn_entrar = findViewById(R.id.bt_entrar);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

                  String email, senha;

                  email = edt_email.getText().toString();
                  senha = edt_senha.getText().toString();


                  Intent a = new Intent(Login.this, Home.class);

              if (i.hasExtra("email") && i.hasExtra("senha")) {
                  Bundle b = getIntent().getExtras();
                  String emailB = b.getString("email");
                  String senhaB = b.getString("senha");

                  if (email.contains("@") && email.contains(".") && senha.length() <= 8 && !email.equals(emailB) || email.equals(emailB) && senha.equals(senhaB)){
                      a.putExtras(b);
                      startActivity(a);
                  }else if (senha.length()> 8){
                      Toast.makeText(Login.this, "A senha pode ter no máximo 8 caracteres", Toast.LENGTH_SHORT).show();
                  }else {
                      Toast.makeText(Login.this, "O e-mail ou senha estão incorretos", Toast.LENGTH_SHORT).show();
                  }
              }else {

                  if (email.contains("@") && email.contains(".") && senha.length() <= 8) {
                      Bundle b2 = new Bundle();
                      b2.putString("email", email);
                      b2.putString("senha", senha);
                      a.putExtras(b2);
                      startActivity(a);
                  } else if (senha.length() > 8) {
                      Toast.makeText(Login.this, "A senha pode ter no máximo 8 caracteres", Toast.LENGTH_SHORT).show();
                  } else {
                      Toast.makeText(Login.this, "O e-mail ou senha estão incorretos", Toast.LENGTH_SHORT).show();
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