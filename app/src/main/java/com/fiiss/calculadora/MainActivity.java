package com.fiiss.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editNumeroUno;
    private EditText editNumeroDos;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        Button btnLimpiar = findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(this);

        editNumeroUno = findViewById(R.id.editNumeroUno);
        editNumeroDos = findViewById(R.id.editNumeroDos);
        txtResultado = findViewById(R.id.txtResultado);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalcular:
                if (editNumeroUno.getText().toString().trim().isEmpty() || editNumeroDos.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Campos requeridos", Toast.LENGTH_LONG).show();
                } else {
                    int resultado = Integer.parseInt(editNumeroUno.getText().toString().trim()) + Integer.parseInt(editNumeroDos.getText().toString().trim());
                    txtResultado.setText(String.format("Resultado: %s", resultado));
                }
                break;
            case R.id.btnLimpiar:
                editNumeroUno.setText("");
                editNumeroDos.setText("");
                txtResultado.setText("");
                editNumeroUno.requestFocus();
                break;
        }
    }
}
