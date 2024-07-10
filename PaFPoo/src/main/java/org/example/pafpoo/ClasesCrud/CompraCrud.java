package org.example.pafpoo.ClasesCrud; // 00077320 Define el paquete donde se encuentra esta clase.

import java.sql.*; // 00077320 Importa todas las clases necesarias para trabajar con SQL.
import java.time.LocalDateTime; // 00077320 Importa la clase LocalDateTime.
import java.util.ArrayList; // 00077320 Importa la clase ArrayList.
import java.util.List; // 00077320 Importa la interfaz List.
import org.example.pafpoo.Clases.Compra; // 00077320 Importa la clase Compra del paquete org.example.pafpoo.Clases.
import java.math.BigDecimal; // 00077320 Importa la clase BigDecimal.

public class CompraCrud { // 00077320 Define la clase CompraCrud.
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // 00077320 Declara y asigna la URL de conexión a la base de datos.
    private final String user = "root"; // 00077320 Declara y asigna el usuario de la base de datos.
    private final String password = "madafakA23"; // 00077320 Declara y asigna la contraseña de la base de datos.

    public void crearCompra(Compra compra) throws SQLException { // 00077320 Define un método que recibe un objeto Compra y puede lanzar una SQLException.
        String sql = "INSERT INTO compra (fecha_compra, monto_gastado, descripcion, id_TarjetaC) VALUES (?, ?, ?, ?)"; // 00077320 Declara la consulta SQL para insertar una compra.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setTimestamp(1, Timestamp.valueOf(compra.getFechaCompra())); // 00077320 Establece el valor del primer parámetro de la consulta (fecha de la compra).
            stmt.setBigDecimal(2, compra.getMontoGastado()); // 00077320 Establece el valor del segundo parámetro de la consulta (monto gastado).
            stmt.setString(3, compra.getDescripcion()); // 00077320 Establece el valor del tercer parámetro de la consulta (descripción).
            stmt.setInt(4, compra.getIdTarjeta()); // 00077320 Establece el valor del cuarto parámetro de la consulta (ID de la tarjeta).
            stmt.executeUpdate(); // 00077320 Ejecuta la consulta de inserción.
        }
    }

    public List<Compra> obtenerComprasPorClienteYPeriodo(int idCliente, Date fechaInicio, Date fechaFin) throws SQLException { // 00077320 Define un método que retorna una lista de compras y puede lanzar una SQLException.
        List<Compra> compras = new ArrayList<>(); // 00077320 Crea una lista vacía para almacenar las compras.
        String sql = "SELECT c.* FROM compra c INNER JOIN tarjeta t ON c.id_TarjetaC = t.id_TarjetaC WHERE t.idCliente = ? AND c.fecha_compra BETWEEN ? AND ?"; // 00077320 Declara la consulta SQL para obtener las compras de un cliente en un período específico.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setInt(1, idCliente); // 00077320 Establece el valor del primer parámetro de la consulta (ID del cliente).
            stmt.setDate(2, fechaInicio); // 00077320 Establece el valor del segundo parámetro de la consulta (fecha de inicio).
            stmt.setDate(3, fechaFin); // 00077320 Establece el valor del tercer parámetro de la consulta (fecha de fin).
            try (ResultSet rs = stmt.executeQuery()) { // 00077320 Ejecuta la consulta y almacena el resultado en un ResultSet.
                while (rs.next()) { // 00077320 Itera sobre cada fila del ResultSet.
                    Compra compra = new Compra(); // 00077320 Crea un nuevo objeto Compra.
                    compra.setIdCompra(rs.getInt("idcompra")); // 00077320 Establece el ID de la compra con el valor de la columna "idcompra".
                    compra.setFechaCompra(rs.getTimestamp("fecha_compra").toLocalDateTime()); // 00077320 Establece la fecha de la compra con el valor de la columna "fecha_compra" convertido a LocalDateTime.
                    compra.setMontoGastado(rs.getBigDecimal("monto_gastado")); // 00077320 Establece el monto gastado con el valor de la columna "monto_gastado".
                    compra.setDescripcion(rs.getString("descripcion")); // 00077320 Establece la descripción con el valor de la columna "descripcion".
                    compra.setIdTarjeta(rs.getInt("id_TarjetaC")); // 00077320 Establece el ID de la tarjeta con el valor de la columna "id_TarjetaC".
                    compras.add(compra); // 00077320 Añade el objeto Compra a la lista de compras.
                }
            }
        }
        return compras; // 00077320 Retorna la lista de compras.
    }

    public BigDecimal obtenerTotalGastadoPorClienteYMes(int idCliente, int mes, int año) throws SQLException { // 00077320 Define un método que retorna el total gastado por un cliente en un mes específico y puede lanzar una SQLException.
        String sql = "SELECT SUM(c.monto_gastado) AS total FROM compra c INNER JOIN tarjeta t ON c.id_TarjetaC = t.id_TarjetaC WHERE t.idCliente = ? AND MONTH(c.fecha_compra) = ? AND YEAR(c.fecha_compra) = ?"; // 00077320 Declara la consulta SQL para obtener el total gastado por un cliente en un mes y año específicos.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setInt(1, idCliente); // 00077320 Establece el valor del primer parámetro de la consulta (ID del cliente).
            stmt.setInt(2, mes); // 00077320 Establece el valor del segundo parámetro de la consulta (mes).
            stmt.setInt(3, año); // 00077320 Establece el valor del tercer parámetro de la consulta (año).
            try (ResultSet rs = stmt.executeQuery()) { // 00077320 Ejecuta la consulta y almacena el resultado en un ResultSet.
                if (rs.next()) { // 00077320 Si hay una fila en el resultado.
                    return rs.getBigDecimal("total"); // 00077320 Retorna el valor de la columna "total".
                }
            }
        }
        return BigDecimal.ZERO; // 00077320 Retorna BigDecimal.ZERO si no hay resultado.
    }

    public void eliminarCompra(int idCompra) throws SQLException { // 00077320 Define un método que recibe el ID de la compra y puede lanzar una SQLException.
        String sql = "DELETE FROM compra WHERE idcompra = ?"; // 00077320 Declara la consulta SQL para eliminar una compra.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setInt(1, idCompra); // 00077320 Establece el valor del primer parámetro de la consulta (ID de la compra).
            stmt.executeUpdate(); // 00077320 Ejecuta la consulta de eliminación.
        }
    }

    public void actualizarCompra(Compra compra) throws SQLException { // 00077320 Define un método que recibe un objeto Compra y puede lanzar una SQLException.
        String sql = "UPDATE compra SET fecha_compra = ?, monto_gastado = ?, descripcion = ?, id_TarjetaC = ? WHERE idcompra = ?"; // 00077320 Declara la consulta SQL para actualizar una compra.
        try (Connection conn = DriverManager.getConnection(url, user, password); // 00077320 Establece una conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // 00077320 Crea un objeto PreparedStatement para ejecutar la consulta.
            stmt.setTimestamp(1, Timestamp.valueOf(compra.getFechaCompra())); // 00077320 Establece el valor del primer parámetro de la consulta (fecha de la compra).
            stmt.setBigDecimal(2, compra.getMontoGastado()); // 00077320 Establece el valor del segundo parámetro de la consulta (monto gastado).
            stmt.setString(3, compra.getDescripcion()); // 00077320 Establece el valor del tercer parámetro de la consulta (descripción).
            stmt.setInt(4, compra.getIdTarjeta()); // 00077320 Establece el valor del cuarto parámetro de la consulta (ID de la tarjeta).
            stmt.setInt(5, compra.getIdCompra()); // 00077320 Establece el valor del quinto parámetro de la consulta (ID de la compra).
            stmt.executeUpdate(); // 00077320 Ejecuta la consulta de actualización.
        }
    }
}
