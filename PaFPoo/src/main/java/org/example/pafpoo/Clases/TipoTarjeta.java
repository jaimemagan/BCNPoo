package org.example.parcialfinalpoo.Clases;

public class TipoTarjeta {
    private int idTipoTarjeta;
    private String tipo;

    public TipoTarjeta(int idTipoTarjeta, String tipo) {
        this.idTipoTarjeta = idTipoTarjeta;
        this.tipo = tipo;
    }

    public int getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(int idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

