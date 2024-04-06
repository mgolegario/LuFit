package com.example.lufit;

import static android.content.Intent.getIntent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    TextView tv_nome;
    TextView tv_peso;
    TextView tv_exercicio;
    TextView tv_dieta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle b = this.getArguments();
        tv_nome = view.findViewById(R.id.tv_nome);
        tv_peso = view.findViewById(R.id.tv_peso);
        tv_exercicio = view.findViewById(R.id.tv_exercicios);
        tv_dieta = view.findViewById(R.id.tv_dieta);

        String nome = b.getString("nome");
        tv_nome.setText("Olá, "+ nome);

        Float peso = b.getFloat("peso");
        tv_peso.setText("Você está com " + peso +" kg. Sua meta é perder X kg.");

        String projeto = b.getString("projeto");
        tv_exercicio.setText("Você está no projeto " + projeto +". Seus exercícios são:");


        return view;
    }

}