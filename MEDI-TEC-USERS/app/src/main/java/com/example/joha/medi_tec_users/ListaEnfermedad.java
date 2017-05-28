package com.example.joha.medi_tec_users;

import java.util.List;

/**
 * Created by Bryan on 28/5/2017.
 */

public class ListaEnfermedad {
    private List<Enfermedades> enfermedades;

    public ListaEnfermedad(List<Enfermedades> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public List<Enfermedades> getEnfermedades() {
        return enfermedades;
    }
}
