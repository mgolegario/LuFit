package com.example.lufit;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView txtFormat;
    Button btn_comecar;
    ImageView lufit_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lufit_icon = findViewById(R.id.lufitEscuro);
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                lufit_icon.setImageDrawable(getDrawable(R.drawable.logo2));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                lufit_icon.setImageDrawable(getDrawable(R.drawable.logo1));
                break;
        }

        txtFormat = findViewById(R.id.textView1);
        String texto = "LuFit, o app ligado na sua saúde";

        SpannableString spannableString = new SpannableString(texto);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.rgb(82, 113, 255));
        spannableString.setSpan(colorSpan, 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        txtFormat.setText(spannableString);

        btn_comecar = findViewById(R.id.bt_comecar);
        btn_comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ComecarConversa.class);
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

