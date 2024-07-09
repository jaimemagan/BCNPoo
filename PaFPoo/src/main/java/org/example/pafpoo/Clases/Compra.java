package org.example.pafpoo.Clases;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Compra {
    private int idCompra;
    private LocalDateTime fechaCompra;
    private BigDecimal montoGastado;
    private String descripcion;
    private int idTarjeta;

    public Compra() {
    }

    public Compra(int idCompra, LocalDateTime fechaCompra, BigDecimal montoGastado, String descripcion, int idTarjeta) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.montoGastado = montoGastado;
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

    public BigDecimal getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(BigDecimal montoGastado) {
        this.montoGastado = montoGastado;
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
