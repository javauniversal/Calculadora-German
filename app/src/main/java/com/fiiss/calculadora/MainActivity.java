package com.fiiss.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editNumeroUno;
    private EditText editNumeroDos;
    private TextView txtResultado;
    private int resultado = 0;
    private Spinner spOperaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        Button btnLimpiar = findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(this);

        spOperaciones = findViewById(R.id.spOperaciones);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operadores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOperaciones.setAdapter(adapter);
        spOperaciones.setOnItemSelectedListener(this);


        editNumeroUno = findViewById(R.id.editNumeroUno);
        editNumeroDos = findViewById(R.id.editNumeroDos);
        txtResultado = findViewById(R.id.txtResultado);

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalcular:

                if(validarCampos()) {
                    if (resultado == 0) {
                        Toast.makeText(this, getString(R.string.select_operador), Toast.LENGTH_LONG).show();
                    } else {
                        double resultadoTotal = 0.0;
                        switch (resultado) {
                            case 1:
                                resultadoTotal = Double.parseDouble(editNumeroUno.getText().toString().trim()) + Double.parseDouble(editNumeroDos.getText().toString().trim());
                                break;
                            case 2:
                                resultadoTotal = Double.parseDouble(editNumeroUno.getText().toString().trim()) - Double.parseDouble(editNumeroDos.getText().toString().trim());
                                break;
                            case 3:
                                resultadoTotal = Double.parseDouble(editNumeroUno.getText().toString().trim()) * Double.parseDouble(editNumeroDos.getText().toString().trim());

                                break;
                            case 4:
                                resultadoTotal = Double.parseDouble(editNumeroUno.getText().toString().trim()) / Double.parseDouble(editNumeroDos.getText().toString().trim());

                                break;

                        }

                        txtResultado.setText(String.format("%1s %.1f", getString(R.string.resultadoforma), resultadoTotal));
                    }
                }

                break;
            case R.id.btnLimpiar:
                editNumeroUno.setText("");
                editNumeroDos.setText("");
                txtResultado.setText("");
                editNumeroUno.requestFocus();
                spOperaciones.setSelection(0);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                resultado = 0;
                break;
            case 1:
                if(validarCampos()) {
                    resultado = 1;
                } else {
                    spOperaciones.setSelection(0);
                }
                break;
            case 2:
                if(validarCampos()) {
                    resultado = 2;
                } else {
                    spOperaciones.setSelection(0);
                }
                break;

            case 3:
                if(validarCampos()) {
                    resultado = 3;
                } else {
                    spOperaciones.setSelection(0);
                }
                break;
            case 4:
                if(validarCampos()) {
                    resultado = 4;
                } else {
                    spOperaciones.setSelection(0);
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean validarCampos() {
        if (editNumeroUno.getText().toString().trim().isEmpty() || editNumeroDos.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, getString(R.string.requerido), Toast.LENGTH_LONG).show();
            txtResultado.setText("");
            return false;
        } else if ((editNumeroDos.getText().toString().trim().equals("0") && resultado == 4)) {
            Toast.makeText(this, getString(R.string.error_divicion), Toast.LENGTH_LONG).show();
            txtResultado.setText("");
            return false;
        }
        return true;
    }

}
