/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Conexion;
import modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class EstudianteDAO {

    public void insertarEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (cod_estudiante, nom_estudiante) VALUES (?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, estudiante.getCodEstudiante());
            ps.setString(2, estudiante.getNomEstudiante());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> obtenerEstudiantesMap() {
        HashMap<String, Integer> map = new HashMap<>();
        String sql = "SELECT cod_estudiante, nom_estudiante FROM estudiantes";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nom_estudiante") + " (ID " + rs.getInt("cod_estudiante") + ")";
                map.put(nombre, rs.getInt("cod_estudiante"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}

