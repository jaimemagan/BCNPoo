package org.example.pafpoo.Clases;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBaseDeDatos {

    //declaramos la url
    private static String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC";
    //declaramos username
    private  static String usaername = "root";
    //
    private  static String password = "madafakA23";

    //singleton para la base de datos
    private static Connection connection;

    //creamos la instancia que nos define una conexion unica
    public static Connection getInstace() {
        //comprobamos que si la conexion es null
        //si es null creamos la nueva conexion
        //pero si no es null retornamos la  conexion existente
        if (connection == null){
            try {
                //aqui se crea la conexion
                connection = DriverManager.getConnection(url,usaername,password);
            }catch (Exception e){
                //imprime el error
                e.printStackTrace();
            }

        }
        //retorna la nueva conexion
        return connection;

    }
}
