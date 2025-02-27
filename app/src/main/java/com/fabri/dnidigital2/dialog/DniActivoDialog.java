package com.fabri.dnidigital2.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.fabri.dnidigital2.util.FechaConverter;
import com.fabri.dnidigital2.model.Socio;
import com.fabri.dnidigital2.R;

// Clase para mostrar información de un socio con DNI activo
public class DniActivoDialog extends AlertDialog {

    private Activity activity; // Actividad desde la que se invoca el diálogo

    public DniActivoDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    // Método para mostrar el diálogo con los datos del socio
    public void show(Socio socio) {
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View root = LayoutInflater.from(activity).inflate(R.layout.dialog_dni_activo, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(root);

        AlertDialog alertDialog = builder.create();

        // Hacer que el fondo del diálogo sea transparente
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Asignación de referencias a elementos del layout
        ImageView imgObraSocial = root.findViewById(R.id.imgObraSocial);
        TextView tvGremio = root.findViewById(R.id.tvGremio);
        TextView tvEstado = root.findViewById(R.id.tvEstado);
        TextView txtSubtitulo = root.findViewById(R.id.txtSubtitulo);
        TextView tvDni = root.findViewById(R.id.tvDni);
        TextView tvApellidoNombre = root.findViewById(R.id.tvApellidoNombre);

        // Botón para cerrar el diálogo
        root.findViewById(R.id.btnOk).setOnClickListener(v -> alertDialog.dismiss());

        // Mostrar la fecha de vigencia del gremio
        txtSubtitulo.setText(String.format("Vigente hasta el %s", FechaConverter.toString(socio.getFechaVigenciaGremial(), "dd/MM/yyyy")));

        // Mostrar información del socio en los TextViews
        tvDni.setText(String.format("DNI %s", socio.getDni()));
        tvApellidoNombre.setText(socio.getApellidoNombre());
        tvGremio.setText(socio.getGremio().getAbr());
        tvEstado.setText(socio.getEstado().getDsc());

        // Mostrar el diálogo
        alertDialog.show();
    }
}
