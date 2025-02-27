// Definimos el paquete donde se encuentra la interfaz
package com.fabri.dnidigital2.ws;

// Importamos las clases necesarias
import com.fabri.dnidigital2.model.Socio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interfaz que define las llamadas a la API
public interface SocioWS {
    // Metodo para obtener un socio a través de una petición GET
    @GET("socio/getSocio") // Ruta relativa que se añadirá a la URL base
    Call<Socio> getSocio(
            @Query("dni") String dni // Parámetro de consulta "dni" que se enviará en la URL
    );
}
