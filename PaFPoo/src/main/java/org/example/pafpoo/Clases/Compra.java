package org.example.pafpoo.Clases;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Compra { //00218721 clase que representa un compra
    private int idCompra; //00218721 variable que representa el id de la compra
    private LocalDateTime fechaCompra; //00218721 variable que representa la fecha de la compra
    private BigDecimal montoGastado; //00218721 variable que representa el monto gastado en la compra
    private String descripcion; //00218721 variable que representa la descripción de la compra
    private int idTarjeta; //00218721 variable que representa el id de la tarjeta

    public Compra() {
    }

    public Compra(int idCompra, LocalDateTime fechaCompra, BigDecimal montoGastado, String descripcion, int idTarjeta) { // constructor de un objeto tarjeta
        this.idCompra = idCompra; // 00218721 asigna el id de la compra
        this.fechaCompra = fechaCompra; //00218721 asigna la fecha de la compra
        this.montoGastado = montoGastado; //00218721 asigna el monto gastado en la compra
        this.descripcion = descripcion; //00218721 asigna la descripción  de la compra
        this.idTarjeta = idTarjeta; //00218721 asigna el id de la tarjeta que realizo la compra
    }

    public int getIdCompra() {
        return idCompra;
    } //00218721 Método que obtiene el id de la compra

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    } //00218721 Método que asigna el id de al compra

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    } //00218721 Método que obtiene la fecha de la compra

    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; } //00218721  Método que asigna la fecha de la compra

    public BigDecimal getMontoGastado() { return montoGastado; } //0218721 Método que obtiene el monto gastado en la compra

    public void setMontoGastado(BigDecimal montoGastado) { this.montoGastado = montoGastado; } //00218721 Método que asigna el monto gastado de la compra

    public String getDescripcion() {
        return descripcion;
    }//0218721 Método que obtiene la descripción de la compra

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; } //00218721 Método que asigna la descripcion de la compra

    public int getIdTarjeta() { return idTarjeta; } //0218721 Método que obtiene el id de la tarjeta que realizo la compra

    public void setIdTarjeta(int idTarjeta) { this.idTarjeta = idTarjeta; } //00218721 Método que asigna el id de la tarjeta
}
