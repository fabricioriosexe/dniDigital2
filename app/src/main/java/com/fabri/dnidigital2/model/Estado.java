package com.fabri.dnidigital2.model;

import java.io.Serializable;

public class Estado implements Serializable {
    private int id;
    private String dsc;

    public int getId() {
        return id;
    }

    public String getDsc() {
        return dsc;
    }
}
