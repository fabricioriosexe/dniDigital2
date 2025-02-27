package com.fabri.dnidigital2.activity; // Paquete donde se encuentra la actividad

// Importaciones necesarias
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.fabri.dnidigital2.R;
import com.fabri.dnidigital2.dialog.CarnetDigitalDialog;
import com.fabri.dnidigital2.dialog.ConsultarDniDialog;
import com.google.android.material.card.MaterialCardView;

/**
 * MainActivity es la actividad principal de la aplicación.
 * Muestra la pantalla principal y maneja las interacciones del usuario con los botones.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Metodo que se ejecuta cuando se crea la actividad.
     * @param savedInstanceState Estado previo de la actividad (si existe)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama al método de la superclase para mantener el estado de la actividad
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad desde activity_main.xml

        // Se obtienen las referencias a los elementos de la interfaz gráfica (botones)
        MaterialCardView cardCarnet = findViewById(R.id.cardCarnet); // Botón para ver el carnet digital
        MaterialCardView cardDniManual = findViewById(R.id.cardDniManual); // Botón para consultar un DNI manualmente

        // Evento de clic para abrir el diálogo de Carnet Digital
        cardCarnet.setOnClickListener(v -> new CarnetDigitalDialog(this).show());

        // Evento de clic para abrir el diálogo de Consulta de DNI
        cardDniManual.setOnClickListener(v -> new ConsultarDniDialog(this).show());
    }
}
