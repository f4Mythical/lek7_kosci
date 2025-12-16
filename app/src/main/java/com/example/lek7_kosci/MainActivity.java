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
    private final boolean[] zablokowane = new boolean[8];
    private final int[] zapisaneWartosci = new int[8];

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
        Para = findViewById(R.id.textPar);
        DwiePary = findViewById(R.id.text2Par);
        Trojka = findViewById(R.id.textThree);
        Kareta = findViewById(R.id.textKareta);
        Poker = findViewById(R.id.textPoker);
        Full = findViewById(R.id.textFull);
        MalyStrit = findViewById(R.id.textSmallStrict);
        DuzyStrit = findViewById(R.id.textBigStrict);
        textSum = findViewById(R.id.textSum);
        reset();
        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                losuj();
                aktualizujWyniki();
                int suma = obliczSumePar();
                textSum.setText("Suma: " + suma);
            }
        });
        buttonZeruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                for (int i = 0; i < zablokowane.length; i++) {
                    zablokowane[i] = false;
                    zapisaneWartosci[i] = 0;
                }
                buttonZapisz1.setEnabled(true);
                buttonZapisz2.setEnabled(true);
                buttonZapisz3.setEnabled(true);
                buttonZapisz4.setEnabled(true);
                buttonZapisz5.setEnabled(true);
                buttonZapisz6.setEnabled(true);
                buttonZapisz7.setEnabled(true);
                buttonZapisz8.setEnabled(true);
                Para.setText("Para:");
                DwiePary.setText("2 Pary:");
                Trojka.setText("Trójka:");
                Kareta.setText("Kareta:");
                Poker.setText("Poker:");
                Full.setText("Full:");
                MalyStrit.setText("Mały Strit:");
                DuzyStrit.setText("Duży Strit:");
                textSum.setText("Suma");
                Toast.makeText(MainActivity.this, "Wyzerowano", Toast.LENGTH_SHORT).show();
            }
        });
        buttonZapisz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(0);
            }
        });
        buttonZapisz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(1);
            }
        });
        buttonZapisz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(2);
            }
        });
        buttonZapisz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(3);
            }
        });
        buttonZapisz5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(4);
            }
        });
        buttonZapisz6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(5);
            }
        });
        buttonZapisz7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(6);
            }
        });
        buttonZapisz8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapiszKategorie(7);
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

    private void aktualizujWyniki() {
        int wPara = obliczPara();
        int wDwiePary = obliczDwiePary();
        int wTrojka = obliczTrojka();
        int wKareta = obliczKareta();
        int wPoker = obliczPoker();
        int wFull = obliczFull();
        int wMalyStrit = obliczMalyStrit();
        int wDuzyStrit = obliczDuzyStrit();

        if (zablokowane[0]) {
            Para.setText("Para: Zapisano: " + zapisaneWartosci[0]);
        } else {
            Para.setText("Para: " + wPara);
        }
        if (zablokowane[1]) {
            DwiePary.setText("2 Pary: Zapisano: " + zapisaneWartosci[1]);
        } else {
            DwiePary.setText("2 Pary: " + wDwiePary);
        }
        if (zablokowane[2]) {
            Trojka.setText("Trójka: Zapisano: " + zapisaneWartosci[2]);
        } else {
            Trojka.setText("Trójka: " + wTrojka);
        }
        if (zablokowane[3]) {
            Kareta.setText("Kareta: Zapisano: " + zapisaneWartosci[3]);
        } else {
            Kareta.setText("Kareta: " + wKareta);
        }
        if (zablokowane[4]) {
            Poker.setText("Poker: Zapisano: " + zapisaneWartosci[4]);
        } else {
            Poker.setText("Poker: " + wPoker);
        }
        if (zablokowane[5]) {
            Full.setText("Full: Zapisano: " + zapisaneWartosci[5]);
        } else {
            Full.setText("Full: " + wFull);
        }
        if (zablokowane[6]) {
            MalyStrit.setText("Mały Strit: Zapisano: " + zapisaneWartosci[6]);
        } else {
            MalyStrit.setText("Mały Strit: " + wMalyStrit);
        }
        if (zablokowane[7]) {
            DuzyStrit.setText("Duży Strit: Zapisano: " + zapisaneWartosci[7]);
        } else {
            DuzyStrit.setText("Duży Strit: " + wDuzyStrit);
        }
    }

    private void zapiszKategorie(int index) {
        if (zablokowane[index]) {
            return;
        }
        int wartosc = 0;
        switch (index) {
            case 0:
                wartosc = obliczPara();
                buttonZapisz1.setEnabled(false);
                break;
            case 1:
                wartosc = obliczDwiePary();
                buttonZapisz2.setEnabled(false);
                break;
            case 2:
                wartosc = obliczTrojka();
                buttonZapisz3.setEnabled(false);
                break;
            case 3:
                wartosc = obliczKareta();
                buttonZapisz4.setEnabled(false);
                break;
            case 4:
                wartosc = obliczPoker();
                buttonZapisz5.setEnabled(false);
                break;
            case 5:
                wartosc = obliczFull();
                buttonZapisz6.setEnabled(false);
                break;
            case 6:
                wartosc = obliczMalyStrit();
                buttonZapisz7.setEnabled(false);
                break;
            case 7:
                wartosc = obliczDuzyStrit();
                buttonZapisz8.setEnabled(false);
                break;
        }
        zablokowane[index] = true;
        zapisaneWartosci[index] = wartosc;
        aktualizujWyniki();
        Toast.makeText(this, "Zapisano kategorię " + (index + 1) + ": " + wartosc, Toast.LENGTH_SHORT).show();
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

    private int[] policzIlosci() {
        int[] counts = new int[7];
        for (int i = 0; i < aktualne.length; i++) {
            int v = aktualne[i];
            if (v >= 1 && v <= 6) {
                counts[v]++;
            }
        }
        return counts;
    }

    private int obliczPara() {
        int[] counts = policzIlosci();
        for (int v = 6; v >= 1; v--) {
            if (counts[v] >= 2) {
                return v * 2;
            }
        }
        return 0;
    }

    private int obliczDwiePary() {
        int[] counts = policzIlosci();
        int pierwsza = 0;
        int druga = 0;
        for (int v = 6; v >= 1; v--) {
            if (counts[v] >= 2) {
                if (pierwsza == 0) {
                    pierwsza = v;
                } else if (druga == 0) {
                    druga = v;
                }
            }
        }
        if (pierwsza > 0 && druga > 0) {
            return pierwsza * 2 + druga * 2;
        }
        return 0;
    }

    private int obliczTrojka() {
        int[] counts = policzIlosci();
        for (int v = 6; v >= 1; v--) {
            if (counts[v] >= 3) {
                return v * 3;
            }
        }
        return 0;
    }

    private int obliczKareta() {
        int[] counts = policzIlosci();
        for (int v = 6; v >= 1; v--) {
            if (counts[v] >= 4) {
                return v * 4;
            }
        }
        return 0;
    }

    private int obliczPoker() {
        int[] counts = policzIlosci();
        for (int v = 6; v >= 1; v--) {
            if (counts[v] == 5) {
                return v * 5;
            }
        }
        return 0;
    }

    private int obliczFull() {
        int[] counts = policzIlosci();
        int trzy = 0;
        int dwa = 0;
        for (int v = 6; v >= 1; v--) {
            if (counts[v] >= 3 && trzy == 0) {
                trzy = v;
            }
        }
        for (int v = 6; v >= 1; v--) {
            if (counts[v] >= 2) {
                if (v != trzy) {
                    dwa = v;
                    break;
                }
            }
        }
        if (trzy > 0 && dwa > 0) {
            return trzy * 3 + dwa * 2;
        }
        return 0;
    }

    private int obliczMalyStrit() {
        boolean ma1 = false;
        boolean ma2 = false;
        boolean ma3 = false;
        boolean ma4 = false;
        boolean ma5 = false;
        for (int i = 0; i < aktualne.length; i++) {
            if (aktualne[i] == 1) ma1 = true;
            if (aktualne[i] == 2) ma2 = true;
            if (aktualne[i] == 3) ma3 = true;
            if (aktualne[i] == 4) ma4 = true;
            if (aktualne[i] == 5) ma5 = true;
        }
        if (ma1 && ma2 && ma3 && ma4 && ma5) {
            return 1 + 2 + 3 + 4 + 5;
        }
        return 0;
    }

    private int obliczDuzyStrit() {
        boolean ma2 = false;
        boolean ma3 = false;
        boolean ma4 = false;
        boolean ma5 = false;
        boolean ma6 = false;
        for (int i = 0; i < aktualne.length; i++) {
            if (aktualne[i] == 2) ma2 = true;
            if (aktualne[i] == 3) ma3 = true;
            if (aktualne[i] == 4) ma4 = true;
            if (aktualne[i] == 5) ma5 = true;
            if (aktualne[i] == 6) ma6 = true;
        }
        if (ma2 && ma3 && ma4 && ma5 && ma6) {
            return 2 + 3 + 4 + 5 + 6;
        }
        return 0;
    }
}
