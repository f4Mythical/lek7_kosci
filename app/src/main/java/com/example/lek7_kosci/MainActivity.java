package com.example.lek7_kosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageJeden;
    private ImageView imageDwa;
    private ImageView imageTrzy;
    private ImageView imageCztery;
    private ImageView imagePiaty;
    private Zdjecie[] listaZdjec;
    private Button buttonLosuj;
    private Button buttonZeruj;
    private Button buttonZapisz1;
    private Button buttonZapisz2;
    private Button buttonZapisz3;
    private Button buttonZapisz4;
    private Button buttonZapisz5;
    private Button buttonZapisz6;
    private Button buttonZapisz7;
    private Button buttonZapisz8;
    private TextView Para;
    private TextView DwiePary;
    private TextView Trojka;
    private TextView Kareta;
    private TextView Poker;
    private TextView Full;
    private TextView MalyStrit;
    private TextView DuzyStrit;
    private TextView textSum;
    private final int[] aktualne = new int[5];
    private final Random randomGenerator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                Insets systemBarsInsets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
                view.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom);
                return windowInsetsCompat;
            }
        });
        listaZdjec = new Zdjecie[] {
                new Zdjecie(R.drawable.obraz1, 1),
                new Zdjecie(R.drawable.obraz2, 2),
                new Zdjecie(R.drawable.obraz3, 3),
                new Zdjecie(R.drawable.obraz4, 4),
                new Zdjecie(R.drawable.obraz5, 5),
                new Zdjecie(R.drawable.obraz6, 6)
        };
        imageJeden = findViewById(R.id.imageView1);
        imageDwa = findViewById(R.id.imageView2);
        imageTrzy = findViewById(R.id.imageView3);
        imageCztery = findViewById(R.id.imageView4);
        imagePiaty = findViewById(R.id.imageView5);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonZeruj = findViewById(R.id.buttonZero);
        buttonZapisz1 = findViewById(R.id.buttonSave1);
        buttonZapisz2 = findViewById(R.id.buttonSave2);
        buttonZapisz3 = findViewById(R.id.buttonSave3);
        buttonZapisz4 = findViewById(R.id.buttonSave4);
        buttonZapisz5 = findViewById(R.id.buttonSave5);
        buttonZapisz6 = findViewById(R.id.buttonSave6);
        buttonZapisz7 = findViewById(R.id.buttonSave7);
        buttonZapisz8 = findViewById(R.id.buttonSave8);
        textSum = findViewById(R.id.textSum);
        reset();
        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                losuj();
                int suma = obliczSumePar();
                textSum.setText("Suma: " + suma);
            }
        });
        buttonZeruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                textSum.setText("Suma");
                Toast.makeText(MainActivity.this, "Wyzerowano", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void losuj() {
        ImageView[] imageViews = new ImageView[] { imageJeden, imageDwa, imageTrzy, imageCztery, imagePiaty };
        for (int p = 0; p < imageViews.length; p++) {
            int randomIndex = randomGenerator.nextInt(listaZdjec.length);
            Zdjecie wybraneZdjecie = listaZdjec[randomIndex];
            imageViews[p].setImageResource(wybraneZdjecie.getZdjecieId());
            aktualne[p] = wybraneZdjecie.getWartosc();
        }
    }

    private void reset() {
        Zdjecie domyslneZdjecie = listaZdjec[0];
        imageJeden.setImageResource(domyslneZdjecie.getZdjecieId());
        imageDwa.setImageResource(domyslneZdjecie.getZdjecieId());
        imageTrzy.setImageResource(domyslneZdjecie.getZdjecieId());
        imageCztery.setImageResource(domyslneZdjecie.getZdjecieId());
        imagePiaty.setImageResource(domyslneZdjecie.getZdjecieId());
        for (int i = 0; i < aktualne.length; i++) {
            aktualne[i] = 0;
        }
    }

    private int obliczSumePar() {
        int suma = 0;
        for (int wartosc = 1; wartosc <= 6; wartosc++) {
            int ileWystapien = 0;
            for (int i = 0; i < aktualne.length; i++) {
                if (aktualne[i] == wartosc) {
                    ileWystapien++;
                }
            }
            int pary = ileWystapien / 2;
            suma += pary * 2 * wartosc;
        }
        return suma;
    }

}