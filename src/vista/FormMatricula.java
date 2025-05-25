package vista;

import controlador.CursoDAO;
import controlador.EstudianteDAO;
import controlador.MatriculaDAO;
import modelo.Matricula;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class FormMatricula extends JInternalFrame {
    private JComboBox<String> comboEstudiante;
    private JLabel lblNombre;
    private JList<String> listaCursos;
    private JButton btnRegistrar, btnLimpiar;
    private HashMap<String, Integer> estudianteMap;
    private HashMap<String, Integer> cursoMap;

    public FormMatricula() {
        setTitle("Registro de Matrículas");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(null);
        setSize(450, 350);

        JLabel lblEst = new JLabel("Código Estudiante:");
        lblEst.setBounds(30, 20, 150, 25);
        add(lblEst);

        comboEstudiante = new JComboBox<>();
        comboEstudiante.setBounds(170, 20, 220, 25);
        add(comboEstudiante);

        JLabel lblNom = new JLabel("Nombre Estudiante:");
        lblNom.setBounds(30, 60, 150, 25);
        add(lblNom);

        lblNombre = new JLabel("...");
        lblNombre.setBounds(170, 60, 220, 25);
        add(lblNombre);

        JLabel lblCursos = new JLabel("Cursos a Matricular:");
        lblCursos.setBounds(30, 100, 150, 25);
        add(lblCursos);

        listaCursos = new JList<>();
        JScrollPane scroll = new JScrollPane(listaCursos);
        scroll.setBounds(170, 100, 220, 100);
        add(scroll);

        btnRegistrar = new JButton("Registrar Matrícula");
        btnRegistrar.setBounds(70, 230, 140, 30);
        add(btnRegistrar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(230, 230, 100, 30);
        add(btnLimpiar);

        cargarEstudiantes();
        cargarCursos();

        comboEstudiante.addActionListener(e -> actualizarNombre());

        btnRegistrar.addActionListener((ActionEvent e) -> registrarMatricula());

        btnLimpiar.addActionListener(e -> {
            comboEstudiante.setSelectedIndex(0);
            listaCursos.clearSelection();
            lblNombre.setText("...");
        });
    }

    private void cargarEstudiantes() {
        estudianteMap = new EstudianteDAO().obtenerEstudiantesMap();
        comboEstudiante.removeAllItems();
        for (String nombre : estudianteMap.keySet()) {
            comboEstudiante.addItem(nombre);
        }
        actualizarNombre();
    }

    private void cargarCursos() {
        cursoMap = new CursoDAO().obtenerCursosMap();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String nombre : cursoMap.keySet()) {
            modelo.addElement(nombre);
        }
        listaCursos.setModel(modelo);
    }

    private void actualizarNombre() {
        if (comboEstudiante.getSelectedItem() != null) {
            String texto = comboEstudiante.getSelectedItem().toString();
            lblNombre.setText(texto.split(" \\(ID")[0]);
        }
    }

    private void registrarMatricula() {
        if (comboEstudiante.getSelectedItem() == null || listaCursos.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar estudiante y al menos un curso.");
            return;
        }

        int idEst = estudianteMap.get(comboEstudiante.getSelectedItem().toString());
        MatriculaDAO dao = new MatriculaDAO();

        for (String cursoTexto : listaCursos.getSelectedValuesList()) {
            int idCurso = cursoMap.get(cursoTexto);
            Matricula m = new Matricula(idEst, idCurso, 0.0f);
            dao.insertarMatricula(m);
        }

        JOptionPane.showMessageDialog(null, "Matrícula(s) registrada(s) exitosamente.");
    }
}