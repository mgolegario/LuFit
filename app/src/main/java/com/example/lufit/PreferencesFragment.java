package com.example.lufit;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class PreferencesFragment extends Fragment {
    RadioGroup radio_group;
    RadioButton claro;
    RadioButton escuro;
    FrameLayout gotoConta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preferences, container, false);


        claro = view.findViewById(R.id.rb_claro);
        escuro = view.findViewById(R.id.rb_escuro);
        gotoConta = view.findViewById(R.id.frame_conta);

        gotoConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCadastro();
            }
        });

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                escuro.setChecked(true);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                claro.setChecked(true);
                break;
        }

        claro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }
        });

        escuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        return view;

    }
    public void goToCadastro()
    {
        Bundle b = this.getArguments();
        if (b.getString("email") != null){
            String email = b.getString("email");
            b.putString("email", email);
            Intent intent = new Intent(getActivity(), Conta.class);
            intent.putExtras(b);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getActivity(), Cadastro.class);
            intent.putExtras(b);
            startActivity(intent);
        }

    }

}