package br.edu.fatecjahu.figurageometricaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EntradaActivity extends AppCompatActivity {

    EditText edtLado1, edtLado2, edtLado3;
    Button btnAnalisar;
    TextView tvSemFigura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entrada);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtLado1 = findViewById(R.id.edtLado1);
        edtLado2 = findViewById(R.id.edtLado2);
        edtLado3 = findViewById(R.id.edtLado3);
        btnAnalisar = findViewById(R.id.btnAnalisar);
        tvSemFigura = findViewById(R.id.tvSemFigura);

        tvSemFigura.setVisibility(View.GONE);

        btnAnalisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtLado1.getText().toString().isEmpty()){

                    tvSemFigura.setVisibility(View.VISIBLE);
                    tvSemFigura.setText("Não é Figura Geométrica!!!");
                    return;
                }

                int lado1 = Integer.parseInt(edtLado1.getText().toString());

                int lado2 = 0;
                if (!edtLado2.getText().toString().isEmpty()) {
                    lado2 = Integer.parseInt(edtLado2.getText().toString());
                }

                int lado3 = 0;
                if (!edtLado3.getText().toString().isEmpty()) {
                    lado3 = Integer.parseInt(edtLado3.getText().toString());
                }

                if (lado1 == 0 && lado2 == 0 && lado3 == 0) {
                    tvSemFigura.setVisibility(View.VISIBLE);
                    tvSemFigura.setText("Não é Figura Geométrica!!!");
                    return;
                }

                tvSemFigura.setVisibility(View.GONE);

                Intent intent = new Intent(EntradaActivity.this, ResultadoActivity.class);

                intent.putExtra("lado1", lado1);
                intent.putExtra("lado2", lado2);
                intent.putExtra("lado3", lado3);

                startActivity(intent);
            }
        });
    }
}