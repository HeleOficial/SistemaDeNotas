/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Conexion;
import modelo.Docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DocenteDAO {

    public void insertarDocente(Docente docente) {
        String sql = "INSERT INTO docentes (cod_docente, nom_docente) VALUES (?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, docente.getCodDocente());
            ps.setString(2, docente.getNomDocente());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> obtenerDocentesMap() {
        HashMap<String, Integer> map = new HashMap<>();
        String sql = "SELECT cod_docente, nom_docente FROM docentes";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nom_docente") + " (ID " + rs.getInt("cod_docente") + ")";
                map.put(nombre, rs.getInt("cod_docente"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}



