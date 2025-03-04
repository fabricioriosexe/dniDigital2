// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.fabri.dnidigital2.database.DatabaseHelper;
import com.fabri.dnidigital2.model.Socio;
import com.fabri.dnidigital2.util.PasswordGenerator;
import com.fabri.dnidigital2.ws.SocioWS;
import com.fabri.dnidigital2.ws.WebServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Clase que actúa como un repositorio para obtener datos de Socio
public class SocioRepository {

    private static SocioRepository instance;
    private final SocioWS ws;

    private SocioRepository() {
        ws = WebServiceManager.createService(SocioWS.class);
    }

    public static SocioRepository getInstance() {
        if (instance == null) instance = new SocioRepository();
        return instance;
    }

    public LiveData<Socio> getSocio(Context context, String dni) {
        MediatorLiveData<Socio> liveData = new MediatorLiveData<>();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        ws.getSocio(dni).enqueue(new Callback<Socio>() {
            @Override
            public void onResponse(Call<Socio> call, Response<Socio> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Socio socio = response.body();
                    String clave = PasswordGenerator.generarClave();
                    socio.setClave(clave);
                    dbHelper.insertarSocio(socio);
                    liveData.postValue(socio);
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Socio> call, Throwable t) {
                liveData.postValue(null);
            }
        });

        return liveData;
    }
}
