package com.example.lufit;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    TextView redirect_forgPass;
    ImageView bt_voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


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