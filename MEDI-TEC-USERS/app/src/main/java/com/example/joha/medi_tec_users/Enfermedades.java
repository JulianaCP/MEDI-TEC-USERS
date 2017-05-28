package com.example.joha.medi_tec_users;

/**
 * Created by Joha on 17/5/2017.
 */
public class Enfermedades {
    int idEnfermedad;
    String nombre;
    String descripcion;

    public Enfermedades(int idEnfermedad, String nombre, String descripcion) {
        this.idEnfermedad = idEnfermedad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
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
