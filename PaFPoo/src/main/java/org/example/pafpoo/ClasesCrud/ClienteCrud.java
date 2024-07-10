package org.example.pafpoo.ClasesCrud; // 00077320 Define el paquete donde se encuentra esta clase.

import java.sql.*; // 00077320 Importa todas las clases necesarias para trabajar con SQL.
import java.util.ArrayList; // 00077320 Importa la clase ArrayList.
import java.util.List; // 00077320 Importa la interfaz List.
import org.example.pafpoo.Clases.Cliente; // 00077320 Importa la clase Cliente del paquete org.example.pafpoo.Clases.

public class ClienteCrud { // 00077320 Define la clase ClienteCrud.
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // 00077320 Declara y asigna la URL de conexión a la base de datos.
    private final String user = "root"; // 00077320 Declara y asigna el usuario de la base de datos.
    private final String password = "Poo.012024"; // 00077320 Declara y asigna la contraseña de la base de datos.

    public List<Cliente> obtenerTodosLosClientes() throws SQLException { // 00077320 Define un método que retorna una lista de todos los clientes y puede lanzar una SQLException.
        List<Cliente> clientes = new ArrayList<>(); // 00077320 Crea una lista vacía para almacenar los clientes.
        String sql = "SELECT * FROM Cliente"; // 00077320 Declara la consulta SQL para obtener todos los registros de la tabla Cliente.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             Statement stmt = conn.createStatement(); // 00077320 Crea un objeto Statement para ejecutar la consulta.
             ResultSet rs = stmt.executeQuery(sql)) { // 00077320 Ejecuta la consulta SQL y almacena el resultado en un ResultSet.
            while (rs.next()) { // 00077320 Itera sobre cada fila del ResultSet.
                Cliente cliente = new Cliente(); // 00077320 Crea un nuevo objeto Cliente.
                cliente.setIdCliente(rs.getLong("ID_CLIENTE")); // 00077320 Establece el ID del cliente con el valor de la columna "ID_CLIENTE".
                cliente.setNombreCompleto(rs.getString("NOMBRE_COMPLETO")); // 00077320 Establece el nombre completo del cliente con el valor de la columna "NOMBRE_COMPLETO".
                cliente.setDireccion(rs.getString("DIRECCION")); // 00077320 Establece la dirección del cliente con el valor de la columna "DIRECCION".
                cliente.setTelefono(rs.getString("TELEFONO")); // 00077320 Establece el teléfono del cliente con el valor de la columna "TELEFONO".
                clientes.add(cliente); // 00077320 Añade el objeto Cliente a la lista de clientes.
            }
        }
        return clientes; // 00077320 Retorna la lista de clientes.
    }
}
