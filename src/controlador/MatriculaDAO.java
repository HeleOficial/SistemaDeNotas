/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Conexion;
import modelo.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MatriculaDAO {

    public void insertarMatricula(Matricula m) {
        String sql = "INSERT INTO matricula (cod_estudiante, cod_curso, nota_curso) VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getCodEstudiante());
            ps.setInt(2, m.getCodCurso());
            ps.setFloat(3, m.getNotaCurso());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

