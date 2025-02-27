// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.ws;

// Importamos las librerías necesarias
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

// Clase encargada de manejar las configuraciones de Retrofit
public class WebServiceManager {
    // Definimos la URL base del servicio web
    private static final String URL = "http://23.29.121.35:3000/api/v1/";

    // Declaramos una variable estática de Retrofit para que sea reutilizable
    private static Retrofit retrofit;

    // Metodo privado para obtener una instancia de Retrofit
    private static Retrofit getRetrofit() {
        // Verificamos si Retrofit ya está inicializado
        if (retrofit == null) {
            // Configuramos Gson para convertir JSON en objetos Java
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ") // Formato de fecha
                    .create();

            // Construimos la instancia de Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL) // Configuramos la URL base
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Convertimos JSON a objetos Java
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // Habilitamos el soporte para RxJava
                    .build();
        }
        // Retornamos la instancia de Retrofit
        return retrofit;
    }

    // Metodo genérico para crear un servicio de API
    public static <T> T createService(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass); // Creamos una implementación de la interfaz API
    }
}
