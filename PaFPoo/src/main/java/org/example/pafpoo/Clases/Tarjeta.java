package org.example.parcialfinalpoo.Clases;

import java.time.LocalDate;

public class Tarjeta {
    private int idTarjeta;
    private String numeroTarjeta;
    private LocalDate fechaExpiracion;
    private TipoTarjeta tipoTarjeta;
    private Facilitador facilitador;
    private int idCliente;

    public Tarjeta(int idTarjeta, String numeroTarjeta, LocalDate fechaExpiracion, TipoTarjeta tipoTarjeta, Facilitador facilitador, int idCliente) {
        this.idTarjeta = idTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.tipoTarjeta = tipoTarjeta;
        this.facilitador = facilitador;
        this.idCliente = idCliente;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Facilitador getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(Facilitador facilitador) {
        this.facilitador = facilitador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
