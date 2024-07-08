package org.example.pafpoo.Clases;

import java.time.LocalDate;

public class Tarjeta {
    private int idTarjeta;
    private String numeroTarjeta;
    private LocalDate fechaExpiracion;
    private int idTipoTarjeta; // 1 para crédito, 2 para débito (suponiendo esta lógica)
    private int idFacilitador;
    private int idCliente;

    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, String numeroTarjeta, LocalDate fechaExpiracion, int idTipoTarjeta, int idFacilitador, int idCliente) {
        this.idTarjeta = idTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.idTipoTarjeta = idTipoTarjeta;
        this.idFacilitador = idFacilitador;
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

    public int getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(int idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    public int getIdFacilitador() {
        return idFacilitador;
    }

    public void setIdFacilitador(int idFacilitador) {
        this.idFacilitador = idFacilitador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipo() {
        if (idTipoTarjeta == 1) {
            return "credito";
        } else if (idTipoTarjeta == 2) {
            return "debito";
        }
        return "desconocido";
    }

    public String getNumero() {
        return numeroTarjeta;
    }

    @Override
    public String toString() {
        return idTarjeta + " - " + numeroTarjeta;
    }
}

