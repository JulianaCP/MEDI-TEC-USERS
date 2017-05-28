package com.example.joha.medi_tec_users;

import java.util.List;

/**
 * Created by Bryan on 28/5/2017.
 */

public class ListaSintomas {
    private List<Sintomas> sintomas;

    public ListaSintomas(List<Sintomas> sintomas) {
        this.sintomas = sintomas;
    }

    public List<Sintomas> getSintomas() {
        return sintomas;
    }
}
