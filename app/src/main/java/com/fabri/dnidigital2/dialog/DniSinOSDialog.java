package com.fabri.dnidigital2.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;

import com.fabri.dnidigital2.R;

// Clase para mostrar un diálogo cuando el socio no tiene obra social
public class DniSinOSDialog extends AlertDialog {

    private Activity activity;

    public DniSinOSDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void show() {
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View root = LayoutInflater.from(activity).inflate(R.layout.dialog_dni_sin_os, viewGroup, false);

        Builder builder = new Builder(activity);
        builder.setView(root);

        AlertDialog alertDialog = builder.create();

        // Hacer que el fondo del diálogo sea transparente
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Botón para cerrar el diálogo
        root.findViewById(R.id.btnOk).setOnClickListener(v -> alertDialog.dismiss());

        alertDialog.show();
    }
}
