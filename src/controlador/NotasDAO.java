/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NotasDAO {

    public DefaultTableModel obtenerNotas() {
        String[] columnas = {"Estudiante", "Curso", "Docente", "Nota"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        String sql = "SELECT * FROM vista_notas_detalle_simple";

      try (Connection con = Conexion.conectar();
     PreparedStatement ps = con.prepareStatement(sql);
     ResultSet rs = ps.executeQuery()) {

    int contador = 0;
    while (rs.next()) {
        contador++;
        String est = rs.getString("nombre_estudiante");
        String curso = rs.getString("nombre_curso");
        String docente = rs.getString("nombre_docente_curso");
        float nota = rs.getFloat("nota_curso");

        modelo.addRow(new Object[]{est, curso, docente, nota});
    }
    System.out.println("Filas cargadas en tabla: " + contador);

} catch (Exception e) {
    e.printStackTrace();
}


        return modelo;
    }

    public void actualizarNota(String estudiante, String curso, float nuevaNota) {
        String sql = """
            UPDATE matricula m
            JOIN estudiantes e ON m.cod_estudiante = e.cod_estudiante
            JOIN cursos c ON m.cod_curso = c.cod_curso
            SET m.nota_curso = ?
            WHERE e.nom_estudiante = ? AND c.nom_curso = ?""";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setFloat(1, nuevaNota);
            ps.setString(2, estudiante);
            ps.setString(3, curso);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
