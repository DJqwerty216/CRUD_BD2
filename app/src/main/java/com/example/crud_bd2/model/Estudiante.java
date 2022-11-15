package com.example.crud_bd2.model;

public class Estudiante {
    private String Rut;
    private String Nombre;
    private String Apellido;
    private String uid;

    public Estudiante() {
    }

    public Estudiante(String rut, String nombre, String apellido, String uid) {
        Rut = rut;
        Nombre = nombre;
        Apellido = apellido;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String rut) {
        Rut = rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    @Override
    public String toString() {
        return ("R.U.T: "+Rut+"" +
                " Nombre: "+Nombre+"" +
                " Apellido: "+Apellido);
    }
}
