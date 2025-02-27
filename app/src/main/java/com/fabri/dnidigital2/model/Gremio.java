package com.fabri.dnidigital2.model;

import java.io.Serializable;

public class Gremio implements Serializable {
    private int id;
    private String abr;

    public Gremio(int id, String abr) {
        this.id = id;
        this.abr = abr;
    }

    public int getId() {
        return id;
    }

    public String getAbr() {
        return abr;
    }
}
