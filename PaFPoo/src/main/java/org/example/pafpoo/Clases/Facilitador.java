package org.example.parcialfinalpoo.Clases;

public class Facilitador {
    private  int idFacilitador;
    private String nombre;


    public Facilitador(int idFacilitador, String nombre) {
        this.idFacilitador = idFacilitador;
        this.nombre = nombre;
    }

    public int getIdFacilitador() {
        return idFacilitador;
    }

    public void setIdFacilitador(int idFacilitador) {
        this.idFacilitador = idFacilitador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
