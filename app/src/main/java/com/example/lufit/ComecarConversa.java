package com.example.lufit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ComecarConversa extends AppCompatActivity {

    TextView txt;
    TextView redirect_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comecar_conversa);

        txt = findViewById(R.id.textView2);

        String texto = "Aqui você é assistido pelo nosso\nespecialista, o AquariRafa";
        SpannableString spannableString = new SpannableString(texto);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.rgb(82, 113, 255));
        spannableString.setSpan(colorSpan, 49, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt.setText(spannableString);

        redirect_login = findViewById(R.id.textView3);
        SpannableString conteudo= new SpannableString("Já tenho uma conta");
        conteudo.setSpan(new UnderlineSpan(), 0, conteudo.length(), 0);
        redirect_login.setText(conteudo);

        redirect_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComecarConversa.this, Login.class);
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