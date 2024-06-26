package com.example.lufit;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Conversa extends AppCompatActivity {
    ImageView bt_voltar;
    EditText input_usuario;
    ImageButton btn_enviar;
    RecyclerView recyclerView;
    RvClass rvClass;
    Adapter adapter;
    ArrayList<RvClass> list;
    String nome;
    Float altura;
    Float peso;
    Integer contador_msg;
    Boolean podeIrHome;
    Bundle b;
    String projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversa);

        recyclerView = findViewById(R.id.rv_parent);
        list = new ArrayList<>();
        input_usuario = findViewById(R.id.edt_input);
        contador_msg = 0;
        podeIrHome = false;

        Bundle bundleLogou = getIntent().getExtras();
        if (bundleLogou != null) {
            list.add(new RvClass(RvClass.LAYOUT_DOIS, "Parece que você já falou comigo antes, então não vai precisar da minha ajuda. Vou voltar a nadar, qualquer coisa da um grito!"));
            input_usuario.setEnabled(false);
            btn_enviar = findViewById(R.id.btn_enviarMsg);
            btn_enviar.setEnabled(false);
        }else {

            list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.introducao)));
            list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.pergunta1)));


            btn_enviar = findViewById(R.id.btn_enviarMsg);

            btn_enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textoUsuario = input_usuario.getText().toString();


                    if (!input_usuario.getText().toString().equals("")) {
                        list.add(new RvClass(RvClass.LAYOUT_UM, textoUsuario));
                        contador_msg++;


                        input_usuario.setText("");

                        switch (contador_msg) {

                            case 1:
                                nome = textoUsuario;
                                input_usuario.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                break;
                            case 2:
                                altura = Float.parseFloat(textoUsuario);
                                if (altura > 2.5F) {

                                    list.add(new RvClass(RvClass.LAYOUT_DOIS, "Parece que você colocou a altura errada! Tente novamente"));
                                    contador_msg--;
                                }
                                break;
                            case 3:
                                peso = Float.parseFloat(textoUsuario);
                                if (peso < 10F) {
                                    list.add(new RvClass(RvClass.LAYOUT_DOIS, "Parece que o peso que você colocou está errado! Tente novamente"));
                                    contador_msg--;
                                }
                                break;
                        }

                        switch (contador_msg) {

                            case 1:
                                list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.pergunta2)));
                                break;
                            case 2:
                                list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.pergunta3)));
                                break;
                            case 3:
                                float IMC;
                                IMC = (float) (peso / Math.pow(altura, 2));

                                if (IMC < 18.5) {
                                    list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.IMCamarelo)));
                                    projeto = "Ganho de Peso";
                                } else if (IMC <= 24.9) {
                                    list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.IMCverde)));
                                    projeto = "Verão";
                                } else if (IMC <= 30) {
                                    list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.IMClaranja)));
                                    projeto = "Perda de Peso";
                                } else {
                                    list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.IMCvermelho)));
                                    projeto = "Saúde";
                                }
                                list.add(new RvClass(RvClass.LAYOUT_DOIS, getString(R.string.despedida)));
                                podeIrHome = true;
                                break;
                        }
                    }

                    if (podeIrHome) {
                        input_usuario.setEnabled(false);
                        btn_enviar.setEnabled(false);
                        bt_voltar = findViewById(R.id.bt_voltar);
                        bt_voltar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(Conversa.this, Home.class);
                                b = new Bundle();
                                b.putString("nome", nome);
                                b.putFloat("altura", altura);
                                b.putFloat("peso", peso);
                                b.putString("projeto", projeto);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtras(b);
                                startActivity(i);
                            }
                        });
                    }

                    adapter = new Adapter(list, Conversa.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Conversa.this));

                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            });

            }
            if (!podeIrHome) {
                bt_voltar = findViewById(R.id.bt_voltar);
                bt_voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            bt_voltar.setColorFilter(Color.argb(255, 202, 196, 208));
        }
            adapter = new Adapter(list, Conversa.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

    }
}

