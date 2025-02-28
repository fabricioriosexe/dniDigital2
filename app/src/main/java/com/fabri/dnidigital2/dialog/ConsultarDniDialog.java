// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.dialog;

// Importamos las clases necesarias para manipular vistas y diálogos en Android
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fabri.dnidigital2.R;
import com.fabri.dnidigital2.repository.*; // Importamos el repositorio que maneja la consulta de socios

// Clase que extiende AlertDialog y representa un cuadro de diálogo para consultar un DNI
public class ConsultarDniDialog extends AlertDialog {

    // Referencia a la actividad desde la cual se invoca el diálogo
    private AppCompatActivity activity;
    private boolean clicked; // Variable para evitar múltiples clics en el botón

    // Constructor que recibe la actividad y la almacena
    public ConsultarDniDialog(AppCompatActivity activity) {
        super(activity);
        this.activity = activity;
    }

    // Método para mostrar el diálogo
    public void show() {
        // Se obtiene la raíz del layout actual de la actividad
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);

        // Se infla el layout personalizado del diálogo
        View root = LayoutInflater.from(activity).inflate(R.layout.dialog_consultar_dni, viewGroup, false);

        // Se crea un constructor de diálogo
        Builder builder = new Builder(activity);
        builder.setView(root); // Se asocia la vista personalizada al diálogo

        // Se crea el AlertDialog con la configuración definida en el builder
        AlertDialog alertDialog = builder.create();

        // Si la ventana del diálogo existe, se establece un fondo transparente
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Se obtienen referencias a los elementos del layout
        EditText editDni = root.findViewById(R.id.editDni); // Campo de entrada para el DNI
        ImageButton btnConsultar = root.findViewById(R.id.btnConsultar); // Botón de consulta

        // Se configura el evento de clic en el botón de consulta
        btnConsultar.setOnClickListener(v -> {
            if (clicked) return; // Si ya se ha hecho clic, no hacer nada
            if (editDni.getText() == null) return; // Si el campo es nulo, no hacer nada
            if (editDni.getText().toString().isEmpty()) return; // Si el campo está vacío, no hacer nada

            clicked = true; // Se marca como clickeado para evitar múltiples solicitudes

            // Se consulta el socio en el repositorio pasando el DNI ingresado
            SocioRepository.getInstance().getSocio(activity, editDni.getText().toString()).observe(activity, socio -> {
                if (socio == null) {
                    // Si no se encuentra el socio, se muestra un diálogo de DNI inexistente
                    new DniInexistenteDialog(activity).show(DniInexistenteDialog.Tipo.INEXISTENTE);
                } else {
                    // Si el socio existe, se verifica si pertenece a algún gremio
                    if (socio.getGremio().getId() == 0) {
                        new DniSinOSDialog(activity).show(); // Si no pertenece a un gremio, mostrar diálogo de error
                    } else {
                        // Si el socio es activo, mostrar el diálogo de socio activo
                        if (socio.isActivo()) {
                            new DniActivoDialog(activity).show(socio);
                        } else {
                            // Si el socio no está activo, mostrar diálogo de inactivo
                            new DniInexistenteDialog(activity).show(DniInexistenteDialog.Tipo.INACTIVO);
                        }
                    }
                }

                clicked = false; // Se habilita nuevamente el botón para futuras consultas
            });
        });

        // Se muestra el diálogo en pantalla
        alertDialog.show();
    }
}
