package com.example.joha.medi_tec_users;

/**
 * Created by Joha on 18/5/2017.
 */
public class Medicamentos {
    int idMedicamento;
    String nombre;
    String descripcion;

    public Medicamentos(int idMedicamento, String nombre, String descripcion) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
