package vista;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private JDesktopPane escritorio;

    public VentanaPrincipal() {
        setTitle("Información Académica");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializar();
    }

    private void inicializar() {
        escritorio = new JDesktopPane();
        setContentPane(escritorio);

        JMenuBar barra = new JMenuBar();

        JMenu menuMatricula = new JMenu("Ingresar Matrícula");
        JMenu menuNotas = new JMenu("Ingreso Notas");
        JMenu menuListados = new JMenu("Listados");
        JMenu menuHelp = new JMenu("Help");

        // ----------------- Formularios -----------------
        JMenuItem itemEstudiante = new JMenuItem("Estudiantes");
        JMenuItem itemDocente = new JMenuItem("Docentes");
        JMenuItem itemCurso = new JMenuItem("Cursos");
        JMenuItem itemMatricula = new JMenuItem("Matrícula");
        JMenuItem itemNotas = new JMenuItem("Notas");

        // Eventos para mostrar formularios
        itemEstudiante.addActionListener(e -> abrirFormulario(new FormEstudiante()));
        itemDocente.addActionListener(e -> abrirFormulario(new FormDocente()));
        itemCurso.addActionListener(e -> abrirFormulario(new FormCurso()));
        itemMatricula.addActionListener(e -> abrirFormulario(new FormMatricula()));
        itemNotas.addActionListener(e -> abrirFormulario(new FormNotas()));

        // Agregar items al menú
        menuMatricula.add(itemEstudiante);
        menuMatricula.add(itemDocente);
        menuMatricula.add(itemCurso);
        menuMatricula.add(itemMatricula);
        menuNotas.add(itemNotas);

        barra.add(menuMatricula);
        barra.add(menuNotas);
        barra.add(menuListados);
        barra.add(menuHelp);

        setJMenuBar(barra);
    }

    private void abrirFormulario(JInternalFrame frame) {
        // Cerrar si ya está abierto
        for (JInternalFrame f : escritorio.getAllFrames()) {
            if (f.getClass().equals(frame.getClass())) {
                try {
                    f.setSelected(true);
                    f.toFront();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }
        escritorio.add(frame);
        frame.setVisible(true);
        frame.setLocation((escritorio.getWidth() - frame.getWidth()) / 2,
                          (escritorio.getHeight() - frame.getHeight()) / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}