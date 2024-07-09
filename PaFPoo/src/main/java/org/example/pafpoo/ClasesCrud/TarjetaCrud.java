package org.example.pafpoo.ClasesCrud; // 00077320 Define el paquete donde se encuentra esta clase.

import java.sql.*; // 00077320 Importa todas las clases necesarias para trabajar con SQL.
import java.util.ArrayList; // 00077320 Importa la clase ArrayList.
import java.util.List; // 00077320 Importa la interfaz List.

import org.example.pafpoo.Clases.Cliente; // 00077320 Importa la clase Cliente del paquete org.example.pafpoo.Clases.
import org.example.pafpoo.Clases.Tarjeta; // 00077320 Importa la clase Tarjeta del paquete org.example.pafpoo.Clases.

public class TarjetaCrud { // 00077320 Define la clase TarjetaCrud.
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // 00077320 Declara y asigna la URL de conexión a la base de datos.
    private final String user = "root"; // 00077320 Declara y asigna el usuario de la base de datos.
    private final String password = "Poo.012024"; // 00077320 Declara y asigna la contraseña de la base de datos.

    public void crearTarjeta(Tarjeta tarjeta) throws SQLException { // 00077320 Define un método que recibe un objeto Tarjeta y puede lanzar una SQLException.
        String sql = "INSERT INTO tarjeta (numero_tarjeta, fecha_expiracion, id_tipo_tarjeta, id_facilitador, id_cliente) VALUES (?, ?, ?, ?, ?)"; // 00077320 Declara la consulta SQL para insertar una tarjeta.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setString(1, tarjeta.getNumeroTarjeta()); // 00077320 Establece el valor del primer parámetro de la consulta (número de tarjeta).
            stmt.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion())); // 00077320 Establece el valor del segundo parámetro de la consulta (fecha de expiración).
            stmt.setInt(3, tarjeta.getIdTipoTarjeta()); // 00077320 Establece el valor del tercer parámetro de la consulta (ID del tipo de tarjeta).
            stmt.setInt(4, tarjeta.getIdFacilitador()); // 00077320 Establece el valor del cuarto parámetro de la consulta (ID del facilitador).
            stmt.setInt(5, tarjeta.getIdCliente()); // 00077320 Establece el valor del quinto parámetro de la consulta (ID del cliente).
            stmt.executeUpdate(); // 00077320 Ejecuta la consulta de inserción.
        }
    }

    public List<Tarjeta> obtenerTarjetasPorCliente(int idCliente) throws SQLException { // 00077320 Define un método que retorna una lista de tarjetas y puede lanzar una SQLException.
        List<Tarjeta> tarjetas = new ArrayList<>(); // 00077320 Crea una lista vacía para almacenar las tarjetas.
        String sql = "SELECT * FROM tarjeta WHERE id_cliente = ?"; // 00077320 Declara la consulta SQL para obtener las tarjetas de un cliente.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setInt(1, idCliente); // 00077320 Establece el valor del primer parámetro de la consulta (ID del cliente).
            try (ResultSet rs = stmt.executeQuery()) { // 00077320 Ejecuta la consulta y almacena el resultado en un ResultSet.
                while (rs.next()) { // 00077320 Itera sobre cada fila del ResultSet.
                    Tarjeta tarjeta = new Tarjeta(); // 00077320 Crea un nuevo objeto Tarjeta.
                    tarjeta.setIdTarjeta(rs.getInt("id_tarjeta")); // 00077320 Establece el ID de la tarjeta con el valor de la columna "id_tarjeta".
                    tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta")); // 00077320 Establece el número de la tarjeta con el valor de la columna "numero_tarjeta".
                    tarjeta.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate()); // 00077320 Establece la fecha de expiración con el valor de la columna "fecha_expiracion" convertido a LocalDate.
                    tarjeta.setIdTipoTarjeta(rs.getInt("id_tipo_tarjeta")); // 00077320 Establece el ID del tipo de tarjeta con el valor de la columna "id_tipo_tarjeta".
                    tarjeta.setIdFacilitador(rs.getInt("id_facilitador")); // 00077320 Establece el ID del facilitador con el valor de la columna "id_facilitador".
                    tarjeta.setIdCliente(rs.getInt("id_cliente")); // 00077320 Establece el ID del cliente con el valor de la columna "id_cliente".
                    tarjetas.add(tarjeta); // 00077320 Añade el objeto Tarjeta a la lista de tarjetas.
                }
            }
        }
        return tarjetas; // 00077320 Retorna la lista de tarjetas.
    }

    public List<Cliente> obtenerClientesPorFacilitador(int idFacilitador) throws SQLException { // 00077320 Define un método que retorna una lista de clientes y puede lanzar una SQLException.
        List<Cliente> clientes = new ArrayList<>(); // 00077320 Crea una lista vacía para almacenar los clientes.
        String sql = "SELECT DISTINCT c.* FROM cliente c JOIN tarjeta t ON c.id_cliente = t.id_cliente WHERE t.id_facilitador = ?"; // 00077320 Declara la consulta SQL para obtener los clientes por facilitador.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setInt(1, idFacilitador); // 00077320 Establece el valor del primer parámetro de la consulta (ID del facilitador).
            try (ResultSet rs = stmt.executeQuery()) { // 00077320 Ejecuta la consulta y almacena el resultado en un ResultSet.
                while (rs.next()) { // 00077320 Itera sobre cada fila del ResultSet.
                    Cliente cliente = new Cliente(); // 00077320 Crea un nuevo objeto Cliente.
                    cliente.setIdCliente(rs.getLong("id_cliente")); // 00077320 Establece el ID del cliente con el valor de la columna "id_cliente".
                    cliente.setNombreCompleto(rs.getString("nombre_completo")); // 00077320 Establece el nombre completo del cliente con el valor de la columna "nombre_completo".
                    cliente.setDireccion(rs.getString("direccion")); // 00077320 Establece la dirección del cliente con el valor de la columna "direccion".
                    cliente.setTelefono(rs.getString("telefono")); // 00077320 Establece el teléfono del cliente con el valor de la columna "telefono".
                    clientes.add(cliente); // 00077320 Añade el objeto Cliente a la lista de clientes.
                }
            }
        }
        return clientes; // 00077320 Retorna la lista de clientes.
    }
}
