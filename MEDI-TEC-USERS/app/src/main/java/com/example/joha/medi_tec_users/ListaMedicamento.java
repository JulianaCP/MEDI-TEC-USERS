package com.example.joha.medi_tec_users;

import java.util.List;

/**
 * Created by Bryan on 28/5/2017.
 */

public class ListaMedicamento {
    private List<Medicamentos> medicamentos;

    public ListaMedicamento(List<Medicamentos> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<Medicamentos> getMedicamentos() {
        return medicamentos;
    }
}
