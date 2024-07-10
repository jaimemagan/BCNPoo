package org.example.pafpoo.Clases;

public class Cliente { //00218721 Clase que representa un cliente
    private Long idCliente; //00218721 variable que representa el id de un cliente
    private String nombreCompleto; // 00218721 variable que representa el nombre completo de un cliente
    private String direccion; // 00218721 Variable que representa la direccion del cliente
    private String telefono; //00218721 variable que representa el número de teléfono del cliente

    public Cliente() {
    }

    public Cliente(Long idCliente, String nombreCompleto, String direccion, String telefono) { //00218721 Constructor de la clase cliente, crea instancias de esta.
        this.idCliente = idCliente; // 00218721 asigna el id del cliente
        this.nombreCompleto = nombreCompleto; // asigna el nombre completo del cliente
        this.direccion = direccion; // 00218721 asigna la dirrecion del cliente
        this.telefono = telefono; // 00218721 asigna el numero del teléfono del cliente
    }

    public Long getIdCliente() {
        return idCliente;
    } //00218721 Método para obtener el id del cliente

    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; } // 00218721 Método que asigna el id del cliente

    public String getNombreCompleto() { return nombreCompleto;} // 00218721 Método que obtiene el nombre completo del cliente

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }// Método que asigna el nombre completo del cliente

    public String getDireccion() { return direccion;} //00218721 Método que obtiene la direccion del cliente

    public void setDireccion(String direccion) { this.direccion = direccion; } // 00218721 Método que asigna la direccion

    public String getTelefono() { return telefono;} // 00218721 Método que obtiene el número de Teléfono

    public void setTelefono(String telefono) { this.telefono = telefono;} // 00218721 Método que asigna el numero de teléfono

    @Override
    public String toString() { return idCliente + " - " + nombreCompleto; } //00218721 Método que devuelve una representacion textual
}
