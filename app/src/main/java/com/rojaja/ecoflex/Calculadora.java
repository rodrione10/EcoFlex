package com.rojaja.ecoflex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {
    EditText inputKmAuto, inputElectricidad, inputCarneR, inputConsumo, inputCarneB, inputLacteos, inputBasura ;
    Spinner spinnerTipoAuto;
    Button btnCalcular;
    TextView textResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        inputKmAuto = findViewById(R.id.inputKmAuto);
        inputConsumo = findViewById(R.id.inputConsumoCoche);
        spinnerTipoAuto = findViewById(R.id.spinnerTipoAuto);
        inputElectricidad = findViewById(R.id.inputElectricidad);
        inputCarneR = findViewById(R.id.inputCarneRoja);
        inputCarneB = findViewById(R.id.inputCarneBlanca);
        inputLacteos = findViewById(R.id.inputLacteos);
        inputBasura = findViewById(R.id.inputBasura);
        btnCalcular = findViewById(R.id.btnCalcular);
        textResultado = findViewById(R.id.textResultado);

        // Llenar el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Gasolina", "Diésel", "Eléctrico", "Híbrido"});
        spinnerTipoAuto.setAdapter(adapter);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularHuella();
            }
        });
    }
    private void calcularHuella() {
        try {
            double kmAuto = Double.parseDouble(inputKmAuto.getText().toString());
            double consumo = Double.parseDouble(inputConsumo.getText().toString());
            double electricidad = Double.parseDouble(inputElectricidad.getText().toString());
            double carneR = Double.parseDouble(inputCarneR.getText().toString());
            double carneB = Double.parseDouble(inputCarneB.getText().toString());
            double lacteos = Double.parseDouble(inputLacteos.getText().toString());
            double basura = Double.parseDouble(inputBasura.getText().toString());

            String tipoAuto = spinnerTipoAuto.getSelectedItem().toString();

            // Emisiones por km (estimado)
            double factorAuto = 0;
            switch (tipoAuto) {
                case "Gasolina": factorAuto = 0.192; break;
                case "Diésel": factorAuto = 0.215; break;
                case "Híbrido": factorAuto = 0.100; break;
                case "Eléctrico": factorAuto = 0.05; break;
            }

            // Cálculos
            double litrosPorSemana = (kmAuto * consumo) / 100.0;
            double emisionesAuto = litrosPorSemana * 52 * factorAuto;
            double emisionesElectricidad = electricidad * 12 * 0.5;
            double emisionesCarneR = carneR * 52 * 1.0 * 27;  // promedio estimado 5kg CO2 por porción
            double emisionesCarneB = carneB * 52 * 1.0 * 7;  // promedio estimado 5kg CO2 por porción
            double emisionesLacteos = lacteos * 52 * 1.0 * 3;  // promedio estimado 5kg CO2 por porción
            double emisionesBasura = basura * 52 * 1.8;

            double total = emisionesAuto + emisionesElectricidad + emisionesCarneR+ emisionesCarneB + emisionesLacteos + emisionesBasura ;

            textResultado.setText("Tu huella estimada es: " + String.format("%.2f", total) + " kg CO₂/año");

        } catch (Exception e) {
            textResultado.setText("Por favor, completa todos los campos correctamente.");
        }
    }
}