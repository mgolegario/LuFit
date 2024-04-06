package com.example.lufit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;


public class Conversa extends AppCompatActivity {
    ImageView bt_voltar;
    EditText input_usuario;
    LinearLayout lv_mensagens;
    ImageButton btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversa);


        lv_mensagens = findViewById(R.id.lv_mensagens);
        //input_usuario = findViewById(R.id.edt_input);
        btn_enviar = findViewById(R.id.btn_enviarMsg);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_usuario = findViewById(R.id.edt_input);
                String input_usuario_string = "Olá tudo bem";
                TextView tv_usuario;
                tv_usuario = new TextView(Conversa.this);
               // tv_usuario.setTextAppearance(R.style.MsgTextStyle);
                tv_usuario.setText(input_usuario_string);
                lv_mensagens.addView(tv_usuario);
            }
        });






        bt_voltar = findViewById(R.id.bt_voltar);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent i = new Intent(Conversa.this, Home.class);
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