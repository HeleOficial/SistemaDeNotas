package vista;

    import controlador.NotasDAO;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;

    public class FormNotas extends JInternalFrame {
        private JTable tabla;
        private DefaultTableModel modelo;
        private NotasDAO dao = new NotasDAO();

        public FormNotas() {
            setTitle("Registro de Notas");
            setClosable(true);
            setIconifiable(true);
            setResizable(false);
            setLayout(null);
            setSize(600, 400);

            modelo = dao.obtenerNotas();
            tabla = new JTable(modelo);
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setBounds(20, 20, 550, 300);
            add(scroll);

            JButton btnGuardar = new JButton("Guardar Cambios");
            btnGuardar.setBounds(220, 330, 150, 30);
            add(btnGuardar);

            btnGuardar.addActionListener(e -> guardarCambios());

            tabla.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int col = tabla.getSelectedColumn();
                    if (col == 3) {
                        tabla.editCellAt(tabla.getSelectedRow(), col);
                    }
                }
            });
        }

        private void guardarCambios() {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String estudiante = modelo.getValueAt(i, 0).toString();
                String curso = modelo.getValueAt(i, 1).toString();
                float nota;

                try {
                    nota = Float.parseFloat(modelo.getValueAt(i, 3).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Nota inválida en fila " + (i + 1));
                    return;
                }

                dao.actualizarNota(estudiante, curso, nota);
            }

            JOptionPane.showMessageDialog(null, "Notas actualizadas correctamente.");
        }
    }