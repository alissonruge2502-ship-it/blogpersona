
package conexion;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3307/blogpersonal";

    private static final String USUARIO = "blog_app";

    // Escribe aquí tu contraseña de MySQL
    private static final String PASSWORD = "NuevaClave2026!";

    public static Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    URL, USUARIO, PASSWORD
            );

            System.out.println("¡Conexión exitosa con MySQL!");

        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL: "
                    + e.getMessage());
        }

        return conexion;
    }
}

