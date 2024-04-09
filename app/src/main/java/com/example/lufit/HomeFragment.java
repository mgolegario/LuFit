package com.example.lufit;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

public class HomeFragment extends Fragment {

    TextView tv_nome;
    TextView tv_peso;
    TextView tv_exercicio;
    Button btn_exercicio;
    TextView tv_dieta;
    Button btn_dieta;
    FragmentManager fragmentManager;
    FrameLayout fl_btnPress;
    LinearLayout ll_home;
    FrameLayout fl_home;
    TextView tv_titulo;
    TextView tv_conteudo;
    ImageView btn_voltar;
    Float peso;
    Float altura;
    String projeto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle b = this.getArguments();
        tv_nome = view.findViewById(R.id.tv_nome);
        tv_peso = view.findViewById(R.id.tv_peso);
        tv_exercicio = view.findViewById(R.id.tv_exercicios);
        btn_exercicio = view.findViewById(R.id.btn_exercicios);
        tv_dieta = view.findViewById(R.id.tv_dieta);
        btn_dieta = view.findViewById(R.id.btn_dieta);

        fl_btnPress = view.findViewById(R.id.fl_btnPress);
        tv_titulo = view.findViewById(R.id.tv_titulo);
        tv_conteudo = view.findViewById(R.id.tv_conteudo);
        fl_home = view.findViewById(R.id.fl_home);
        ll_home = view.findViewById(R.id.ll_home);

        if (b.getString("nome") != null || b.getString("peso") != null || b.getString("altura") != null|| b.getString("projeto") != null) {
            String nome = b.getString("nome");
            tv_nome.setText("Olá, " + nome);
            peso = b.getFloat("peso");
            altura = b.getFloat("altura");
            projeto = b.getString("projeto");
        }else{
            tv_nome.setText("Olá, Tudo bem?" );
            peso = 60F;
            altura = 1.9F;
            projeto = "Verão";
        }


        tv_peso.setText("Você está com " + peso +" kg e mede "+altura+" metros, siga em frente com foco e determinação nos seus objetivos!");
        tv_exercicio.setText("Você está no Projeto " + projeto +". Confira seus exercícios abaixo.");
        tv_dieta.setText("Sua dieta corresponde ao Projeto " + projeto + ". Apertando o botão você consegue acessar ela.");


        Integer larguraProjeto = projeto.length();
        SpannableString spannableString = new SpannableString(tv_exercicio.getText());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.rgb(82, 113, 255));
        spannableString.setSpan(colorSpan, 21, 21+larguraProjeto, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_exercicio.setText(spannableString);

        SpannableString spannableString2 = new SpannableString(tv_dieta.getText());
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.rgb(82, 113, 255));
        spannableString2.setSpan(colorSpan2, 33, 33+larguraProjeto, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_dieta.setText(spannableString2);

        switch (projeto){
            case "Ganho de Peso":

                btn_exercicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title1);
                        tv_conteudo.setText(R.string.conteudoExer1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }

                });
                btn_dieta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title5);
                        tv_conteudo.setText(R.string.conteudoDieta1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }
                });
                break;
            case "Verão":
                btn_exercicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title2);
                        tv_conteudo.setText(R.string.conteudoExer1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }

                });
                btn_dieta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title6);
                        tv_conteudo.setText(R.string.conteudoDieta1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }
                });

                break;
            case "Perda de Peso":

                btn_exercicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title3);
                        tv_conteudo.setText(R.string.conteudoExer1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }

                });
                btn_dieta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title7);
                        tv_conteudo.setText(R.string.conteudoDieta1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }
                });

                break;
            case "Saúde":

                btn_exercicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title4);
                        tv_conteudo.setText(R.string.conteudoExer2);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }

                });
                btn_dieta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title8);
                        tv_conteudo.setText(R.string.conteudoDieta2);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }
                });
                break;
                default:
                btn_exercicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_titulo.setText(R.string.title2);
                    tv_conteudo.setText(R.string.conteudoExer1);

                    fl_home.setVisibility(View.INVISIBLE);
                    ll_home.setVisibility(View.INVISIBLE);
                    fl_btnPress.setVisibility(View.VISIBLE);
                }

            });
                btn_dieta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_titulo.setText(R.string.title6);
                        tv_conteudo.setText(R.string.conteudoDieta1);

                        fl_home.setVisibility(View.INVISIBLE);
                        ll_home.setVisibility(View.INVISIBLE);
                        fl_btnPress.setVisibility(View.VISIBLE);
                    }
                });

                break;
        }

        btn_voltar = view.findViewById(R.id.bt_voltar);
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_home.setVisibility(View.VISIBLE);
                ll_home.setVisibility(View.VISIBLE);
                fl_btnPress.setVisibility(View.INVISIBLE);
            }
        });

        return view;

    }

}