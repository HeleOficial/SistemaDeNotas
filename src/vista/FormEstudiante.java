package vista;

import controlador.EstudianteDAO;
import modelo.Estudiante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormEstudiante extends JInternalFrame {
    private JTextField txtCodigo, txtNombre;
    private JButton btnIngresar, btnLimpiar;

    public FormEstudiante() {
        setTitle("Registro de Estudiantes");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(null);
        setSize(350, 200);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 20, 180, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 180, 25);
        add(txtNombre);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(60, 110, 100, 30);
        add(btnIngresar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 110, 100, 30);
        add(btnLimpiar);

        EstudianteDAO dao = new EstudianteDAO();

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    String nombre = txtNombre.getText();
                    dao.insertarEstudiante(new Estudiante(codigo, nombre));
                    JOptionPane.showMessageDialog(null, "Estudiante registrado correctamente.");
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
    }
}