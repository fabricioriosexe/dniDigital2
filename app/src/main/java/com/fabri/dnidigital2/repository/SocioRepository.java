// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.repository;

// Importamos las clases necesarias para LiveData y MutableLiveData
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// Importamos el modelo de datos Socio
import com.fabri.dnidigital2.database.DatabaseHelper;
import com.fabri.dnidigital2.model.Socio;

// Importamos la interfaz que define el servicio web para Socio
import com.fabri.dnidigital2.util.PasswordGenerator;
import com.fabri.dnidigital2.ws.SocioWS;
import com.fabri.dnidigital2.ws.WebServiceManager;

// Importamos Retrofit para hacer llamadas a la API
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Clase que actúa como un repositorio para obtener datos de Socio
public class SocioRepository {

    // Instancia única de la clase (Patrón Singleton)
    private static SocioRepository instance;

    // Referencia a la interfaz del servicio web (API REST)
    private final SocioWS ws;

    // Constructor privado para evitar instanciación directa (Singleton)
    private SocioRepository() {
        // Se inicializa la interfaz SocioWS utilizando el WebServiceManager
        ws = WebServiceManager.createService(SocioWS.class);
    }

    // Metodo para obtener la instancia única del repositorio (Singleton)
    public static SocioRepository getInstance() {
        if (instance == null) instance = new SocioRepository(); // Si no hay instancia, se crea
        return instance;
    }

    // Metodo para obtener un Socio desde la API usando LiveData
    public LiveData<Socio> getSocio(Context context, String dni) {
        MutableLiveData<Socio> data = new MutableLiveData<>();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        ws.getSocio(dni).enqueue(new Callback<Socio>() {
            @Override
            public void onResponse(Call<Socio> call, Response<Socio> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Socio socio = response.body();

                    // Generar clave aleatoria
                    String clave = PasswordGenerator.generarClave();
                    socio.setClave(clave);

                    // Guardar en SQLite
                    dbHelper.insertarSocio(socio);

                    // Enviar datos al LiveData
                    data.setValue(socio);
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Socio> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
