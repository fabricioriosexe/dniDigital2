package com.fabri.dnidigital2.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.fabri.dnidigital2.R;

// Clase para mostrar un diálogo cuando el DNI no existe o está inactivo
public class DniInexistenteDialog extends AlertDialog {

    private Activity activity;

    public DniInexistenteDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void show(Tipo tipo) {
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View root = LayoutInflater.from(activity).inflate(R.layout.dialog_dni_inexistente, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(root);

        AlertDialog alertDialog = builder.create();

        // Hacer que el fondo del diálogo sea transparente
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Botón para cerrar el diálogo
        root.findViewById(R.id.btnOk).setOnClickListener(v -> alertDialog.dismiss());

        // Mostrar el mensaje correspondiente dependiendo del tipo
        if (tipo == Tipo.INACTIVO) {
            ((TextView) root.findViewById(R.id.txtTitulo)).setText(R.string.titular_inactivo);
        } else if (tipo == Tipo.INEXISTENTE) {
            ((TextView) root.findViewById(R.id.txtTitulo)).setText(R.string.titular_inexistente);
        }

        alertDialog.show();
    }

    // Enum para los distintos tipos de error
    enum Tipo {
        INACTIVO, // El socio no está activo
        INEXISTENTE // El DNI no está registrado en la base de datos
    }
}
