package vista;

import controlador.CursoDAO;
import controlador.DocenteDAO;
import modelo.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FormCurso extends JInternalFrame {
    private JTextField txtCodigo, txtNombre;
    private JComboBox<String> comboDocente;
    private JButton btnIngresar, btnLimpiar;
    private HashMap<String, Integer> docenteMap;

    public FormCurso() {
        setTitle("Registro de Cursos");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(null);
        setSize(400, 250);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 20, 100, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(140, 20, 200, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 200, 25);
        add(txtNombre);

        JLabel lblDocente = new JLabel("Docente:");
        lblDocente.setBounds(30, 100, 100, 25);
        add(lblDocente);

        comboDocente = new JComboBox<>();
        comboDocente.setBounds(140, 100, 200, 25);
        add(comboDocente);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(80, 150, 100, 30);
        add(btnIngresar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(200, 150, 100, 30);
        add(btnLimpiar);

        cargarDocentes();

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codCurso = Integer.parseInt(txtCodigo.getText());
                    String nombre = txtNombre.getText();
                    int codDocente = docenteMap.get(comboDocente.getSelectedItem().toString());

                    Curso curso = new Curso(codCurso, nombre, codDocente);
                    new CursoDAO().insertarCurso(curso);
                    JOptionPane.showMessageDialog(null, "Curso registrado correctamente.");
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void cargarDocentes() {
        docenteMap = new DocenteDAO().obtenerDocentesMap();
        comboDocente.removeAllItems();
        for (String nombre : docenteMap.keySet()) {
            comboDocente.addItem(nombre);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        comboDocente.setSelectedIndex(0);
    }
}