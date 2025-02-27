// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.dialog;

// Importamos las clases necesarias para manipular vistas y diálogos en Android
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;

import com.fabri.dnidigital2.R; // Importamos el recurso de layout para el diálogo

// Clase que extiende AlertDialog y representa un diálogo personalizado para el Carnet Digital
public class CarnetDigitalDialog extends AlertDialog {

    // Referencia a la actividad que invoca el diálogo
    private Activity activity;

    // Constructor que recibe la actividad y la almacena
    public CarnetDigitalDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    // Método para mostrar el diálogo
    public void show() {
        // Se obtiene la raíz del layout actual de la actividad
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);

        // Se infla (carga) el layout personalizado del diálogo
        View root = LayoutInflater.from(activity).inflate(R.layout.dialog_carnet_digital, viewGroup, false);

        // Se crea un constructor de diálogo
        Builder builder = new Builder(activity);
        builder.setView(root); // Se establece la vista del diálogo

        // Se crea el AlertDialog con la configuración definida en el builder
        AlertDialog alertDialog = builder.create();

        // Si la ventana del diálogo existe, se establece un fondo transparente
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Se muestra el diálogo en pantalla
        alertDialog.show();
    }
}
