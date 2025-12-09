package br.edu.fatecjahu.figurageometricaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoActivity extends AppCompatActivity {
    TextView tvLado3, tvLado1Resultado, tvLado2Resultado, tvLado3Resultado, tvResultadoDaFigura, tvResultadoTriangulo;
    ImageView ivResultadoDaFigura;

    private String descobrirFigura(int lado1, int lado2, int lado3) {
        if (lado3 > 0) {
            ivResultadoDaFigura.setImageResource(R.drawable.triangulo);
            return "Triângulo";
        }
        if ((lado1 > 0 && lado2 == 0 && lado3 == 0) ||
                (lado2 > 0 && lado1 == 0 && lado3 == 0)) {
            ivResultadoDaFigura.setImageResource(R.drawable.quadrado);
            return "Quadrado";
        }
        if (lado1 == lado2) {
            ivResultadoDaFigura.setImageResource(R.drawable.quadrado);
            return "Quadrado";
        } else {
            ivResultadoDaFigura.setImageResource(R.drawable.retangulo);
            return "Retângulo";
        }
    }

    private String descobrirTipoTriangulo(int lado1, int lado2, int lado3) {
        if (lado1 == lado2 && lado2 == lado3) {
            return "Triângulo Equilátero";
        } else if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
            return "Triângulo Isósceles";
        } else {
            return "Triângulo Escaleno";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvLado1Resultado = findViewById(R.id.tvLado1Resultado);
        tvLado2Resultado = findViewById(R.id.tvLado2Resultado);
        tvLado3Resultado = findViewById(R.id.tvLado3Resultado);
        tvLado3 = findViewById(R.id.tvLado3);
        tvResultadoDaFigura = findViewById(R.id.tvResultadoDaFigura);
        tvResultadoTriangulo = findViewById(R.id.tvResultadoTriangulo);
        ivResultadoDaFigura = findViewById(R.id.ivResultadoDaFigura);

        int lado1 = getIntent().getIntExtra("lado1", 0);
        int lado2 = getIntent().getIntExtra("lado2", 0);
        int lado3 = getIntent().getIntExtra("lado3", 0);

        tvLado1Resultado.setText(String.valueOf(lado1));
        tvLado2Resultado.setText(String.valueOf(lado2));

        String figura = descobrirFigura(lado1, lado2, lado3);
        tvResultadoDaFigura.setText(figura);

        if (figura.equals("Triângulo")) {
            tvLado3.setVisibility(View.VISIBLE);
            tvLado3Resultado.setVisibility(View.VISIBLE);
            tvLado3Resultado.setText(String.valueOf(lado3));

            String tipo = descobrirTipoTriangulo(lado1, lado2, lado3);
            tvResultadoTriangulo.setText(tipo);
        } else {
            tvLado3Resultado.setVisibility(View.GONE);
            tvLado3.setVisibility(View.GONE);
        }

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}