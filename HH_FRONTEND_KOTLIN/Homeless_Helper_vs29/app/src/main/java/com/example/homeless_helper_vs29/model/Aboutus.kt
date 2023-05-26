package com.example.homeless_helper_vs29.model

data class Aboutus
    (
        var id: String,
        var nombre: String,
        var apellido1: String,
        var apellido2: String,
        var descripcion: String,
        var imagen: String
        )


// CODIGO DE CUANDO ERA .JAVA
/*

public class Aboutus   {

    private int id;

    private String apellido1;

    private String apellido2;

    private String descripcion;

    private byte[] imagen;

    private String nombre;

    public Aboutus() {
    }
    public Aboutus(String nombre) {

        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return this.imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

 */