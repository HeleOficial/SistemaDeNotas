/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Conexion;
import modelo.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class CursoDAO {

    public void insertarCurso(Curso curso) {
        String sql = "INSERT INTO cursos (cod_curso, nom_curso, cod_docente) VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, curso.getCodCurso());
            ps.setString(2, curso.getNomCurso());
            ps.setInt(3, curso.getCodDocente());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> obtenerCursosMap() {
        HashMap<String, Integer> map = new HashMap<>();
        String sql = "SELECT cod_curso, nom_curso FROM cursos";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nom_curso") + " (ID " + rs.getInt("cod_curso") + ")";
                map.put(nombre, rs.getInt("cod_curso"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
