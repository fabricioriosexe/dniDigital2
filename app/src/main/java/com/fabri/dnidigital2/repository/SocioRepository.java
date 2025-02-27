// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.repository;

// Importamos las clases necesarias para LiveData y MutableLiveData
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// Importamos el modelo de datos Socio
import com.fabri.dnidigital2.model.Socio;

// Importamos la interfaz que define el servicio web para Socio
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
    public LiveData<Socio> getSocio(String dni) {
        MutableLiveData<Socio> data = new MutableLiveData<>(); // Objeto LiveData mutable para almacenar la respuesta

        // Llamada a la API para obtener los datos del socio con el DNI
        ws.getSocio(dni).enqueue(new Callback<Socio>() {
            @Override
            public void onResponse(Call<Socio> call, Response<Socio> response) {
                // Si la respuesta es exitosa y contiene datos, se actualiza el LiveData
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null); // Si la respuesta es errónea o vacía, se asigna null
                }
            }

            @Override
            public void onFailure(Call<Socio> call, Throwable t) {
                // Si la llamada falla, se asigna null al LiveData
                data.setValue(null);
            }
        });

        return data; // Retorna el LiveData con el resultado de la petición
    }
}
