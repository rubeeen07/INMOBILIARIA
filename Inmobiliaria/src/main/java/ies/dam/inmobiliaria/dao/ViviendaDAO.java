package ies.dam.inmobiliaria.dao;

import ies.dam.inmobiliaria.model.Vivienda;
import ies.dam.inmobiliaria.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViviendaDAO {

    public List<Vivienda> buscarPorTipoOperacion(String tipoOperacion) {
        List<Vivienda> viviendas = new ArrayList<>();

        String sql = """
                SELECT id, tipo_operacion, direccion, ciudad, habitaciones, metros, precio, disponible
                FROM viviendas
                WHERE tipo_operacion = ?
                  AND disponible = TRUE
                ORDER BY precio ASC
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, tipoOperacion);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    viviendas.add(crearViviendaDesdeResultSet(resultado));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar viviendas por tipo de operación: " + e.getMessage());
        }

        return viviendas;
    }

    public List<Vivienda> listarTodas() {
        List<Vivienda> viviendas = new ArrayList<>();

        String sql = """
                SELECT id, tipo_operacion, direccion, ciudad, habitaciones, metros, precio, disponible
                FROM viviendas
                ORDER BY tipo_operacion, precio ASC
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                viviendas.add(crearViviendaDesdeResultSet(resultado));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar viviendas: " + e.getMessage());
        }

        return viviendas;
    }

    private Vivienda crearViviendaDesdeResultSet(ResultSet resultado) throws SQLException {
        return new Vivienda(
                resultado.getInt("id"),
                resultado.getString("tipo_operacion"),
                resultado.getString("direccion"),
                resultado.getString("ciudad"),
                resultado.getInt("habitaciones"),
                resultado.getDouble("metros"),
                resultado.getDouble("precio"),
                resultado.getBoolean("disponible")
        );
    }
}
