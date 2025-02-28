package com.fabri.dnidigital2.model;

import java.io.Serializable;
import java.util.Date;

public class Socio implements Serializable {
    private int id;
    private String dni;
    private String apellidoNombre;
    private Estado estado;
    private boolean activo;
    private Gremio gremio;
    private Date fechaVigenciaGremial;
    private String clave;


    public Socio(int id, String dni, String apellidoNombre, Estado estado, boolean activo, Gremio gremio, Date fechaVigenciaGremial) {
        this.id = id;
        this.dni = dni;
        this.apellidoNombre = apellidoNombre;
        this.estado = estado;
        this.activo = activo;
        this.gremio = gremio;
        this.fechaVigenciaGremial = fechaVigenciaGremial;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getApellidoNombre() {
        return apellidoNombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public boolean isActivo() {
        return activo;
    }

    public Gremio getGremio() {
        return gremio;
    }

    public Date getFechaVigenciaGremial() {
        return fechaVigenciaGremial;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
