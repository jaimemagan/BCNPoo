package org.example.pafpoo.Clases;

import java.time.LocalDateTime;

public class Compra {
    private int idCompra;
    private LocalDateTime fechaCompra;
    private float montoTotal;
    private String descripcion;
    private int idTarjeta;

    public Compra(int idCompra, LocalDateTime fechaCompra, float montoTotal, String descripcion, int idTarjeta) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.descripcion = descripcion;
        this.idTarjeta = idTarjeta;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }
}
