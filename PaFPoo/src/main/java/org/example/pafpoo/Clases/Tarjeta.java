package org.example.pafpoo.Clases;

import java.time.LocalDate;

public class Tarjeta { // 00218721 Clase que representa una Tarjeta
    private int idTarjeta; //00218721 variable que representa el id de una tarjeta
    private String numeroTarjeta;//00218721 variable que representa el numero de la tarjeta
    private LocalDate fechaExpiracion; //00218721 variable que representa la fecha de expiración de una tarjeta
    private int idTipoTarjeta; // 00218721 variable que representa el id del tipo de tarjeta (1 para crédito, 2 para débito suponiendo esta lógica)
    private int idFacilitador; //00218721 variable que representa el id del facilitador de la tarjeta
    private int idCliente; // 00218721 variable que representa el id del cliente

    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, String numeroTarjeta, LocalDate fechaExpiracion, int idTipoTarjeta, int idFacilitador, int idCliente) { //00218721 Contructor del objeto tarjeta
        this.idTarjeta = idTarjeta; // 00218721 Asigna el id de la tarjeta
        this.numeroTarjeta = numeroTarjeta; // 00218721 Asigna el numero de la tarjeta
        this.fechaExpiracion = fechaExpiracion; // 00218721 Asigna la fecha de expiración de la tarjeta
        this.idTipoTarjeta = idTipoTarjeta; //00218721 Asigna el id del tipo de tarjeta
        this.idFacilitador = idFacilitador;//00218721 Asigna el id del facilitador de la tarjeta
        this.idCliente = idCliente; //00218721 asigna el id del cliente de esta tarjeta
    }

    public int getIdTarjeta() {
        return idTarjeta;
    } //00218721 Método que obtiene el id de la tarjeta

    public void setIdTarjeta(int idTarjeta) { this.idTarjeta = idTarjeta; } //00218721 Método que asigna el id del tarjeta

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }//00218721 Método que obtiene el numero de la tarjeta

    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; } //00218721 Método que asigna el numero de la tarjeta

    public LocalDate getFechaExpiracion() { return fechaExpiracion; }//00218721 Método que obtiene la fecha de expiración de la tarjeta

    public void setFechaExpiracion(LocalDate fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }//00218721 Método que asigna la fecha de expiración de la tarjeta

    public int getIdTipoTarjeta() {
        return idTipoTarjeta;
    } //00218721 Método que obtiene el id del tipo de tarjeta

    public void setIdTipoTarjeta(int idTipoTarjeta) { this.idTipoTarjeta = idTipoTarjeta;} //00218721 Método que asigna el id del tipo de tarjeta

    public int getIdFacilitador() { return idFacilitador; } //00218721 Método que obtiene el id del facilitador de la tarjeta

    public void setIdFacilitador(int idFacilitador) { this.idFacilitador = idFacilitador; } //00218721 Método que asigna el id de la facilitador

    public int getIdCliente() {
        return idCliente;
    } //00218721 Método que obtiene el id del cliente de la tarjeta

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; } //00218721 Método que asigna el id del cliente

    public String getTipo() { //00218721 Método que retorna el tipo de tarjeta
        if (idTipoTarjeta == 1) { //00218721 condición que verifica el id de tipo tarjeta
            return "credito"; // 00218721 retorna una tarjeta de crédito si la opción es 1
        } else if (idTipoTarjeta == 2) { //00218721 verifica si la opción es 2
            return "debito";// 00218721 retorna que es una tarjeta de débito
        }
        return "desconocido"; // retorna desconocido si no se cumple ninguna de las condiciones
    }

    public String getNumero() {
        return numeroTarjeta;
    } //00218721 Método que obtiene el número de la tarjeta

    @Override
    public String toString() { return idTarjeta + " - " + numeroTarjeta;} //00218721 Método que devuelve una representación textual
}

