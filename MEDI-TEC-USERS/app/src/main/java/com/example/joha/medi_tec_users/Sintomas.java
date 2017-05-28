package com.example.joha.medi_tec_users;

/**
 * Created by Joha on 18/5/2017.
 */
public class Sintomas {
    int idSintoma;
    String nombre;

    public Sintomas(int idSintoma, String nombre) {
        this.idSintoma = idSintoma;
        this.nombre = nombre;
    }

    public int getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
