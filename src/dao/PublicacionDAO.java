
package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicacionDAO {

    public boolean insertar(String titulo, String contenido, String estado) {

        String sql = "INSERT INTO publicaciones (titulo, contenido, estado) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, titulo);
            consulta.setString(2, contenido);
            consulta.setString(3, estado);

            consulta.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar publicación: " + e.getMessage());
            return false;
        }
    }


public String consultar() {
    String sql = "SELECT * FROM publicaciones";
    StringBuilder publicaciones = new StringBuilder();

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement consulta = conexion.prepareStatement(sql)) {

        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            publicaciones.append("ID: ")
                .append(resultado.getInt("id"))
                .append("\nTítulo: ")
                .append(resultado.getString("titulo"))
                .append("\nContenido: ")
                .append(resultado.getString("contenido"))
                .append("\nEstado: ")
                .append(resultado.getString("estado"))
                .append("\n-------------------------\n");
        }

        if (publicaciones.length() == 0) {
            return "No hay publicaciones guardadas.";
        }

        return publicaciones.toString();

    } catch (SQLException e) {
        return "Error al consultar: " + e.getMessage();
    }
}
public boolean actualizar(int id, String titulo, String contenido, String estado) {
    String sql = "UPDATE publicaciones SET titulo = ?, contenido = ?, estado = ? WHERE id = ?";

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement consulta = conexion.prepareStatement(sql)) {

        consulta.setString(1, titulo);
        consulta.setString(2, contenido);
        consulta.setString(3, estado);
        consulta.setInt(4, id);

        consulta.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Error al actualizar: " + e.getMessage());
        return false;
    }
}
     public boolean eliminar(int id) {
    String sql = "DELETE FROM publicaciones WHERE id = ?";

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement consulta = conexion.prepareStatement(sql)) {

        consulta.setInt(1, id);
        consulta.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Error al eliminar: " + e.getMessage());
        return false;
    }
} 
}


