package view;
import model.Integrante;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * VISTA: contiene unicamente los componentes Swing, su distribucion visual
 * y metodos para leer/escribir los valores del formulario.
 * No valida datos ni guarda registros: eso lo hace el controlador.
 */
public class FormularioIntegrante extends JFrame {
    private static final String[] PROYECTOS = {
        "Ingeniería de Sistemas", "Ingeniería Electrónica",
        "Ingeniería Industrial", "Ingeniería Civil"
    };
    private static final String[] COLUMNAS = {
        "Nombres", "Apellidos", "Repositorio", "Tipo", "Proyecto educativo"
    };
    private final JTextField txtNombres = new JTextField(25);
    private final JTextField txtApellidos = new JTextField(25);
    private final JTextField txtRepositorio = new JTextField(25);
    private final JRadioButton rbNuevo = new JRadioButton("Nuevo", true);
    private final JRadioButton rbAntiguo = new JRadioButton("Antiguo");
    private final JComboBox<String> cmbProyecto = new JComboBox<>(PROYECTOS);
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JButton btnConsultar = new JButton("Consultar registros");
    private final DefaultTableModel modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    
    public FormularioIntegrante() {
        super("Registro de Integrantes - Proyectos Educativos");
        construirInterfaz();
    }
    private void construirInterfaz() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Agrupa los radio buttons para permitir una sola seleccion
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(rbNuevo);
        grupoTipo.add(rbAntiguo);

        // ----- Panel del formulario -----
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del integrante"));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;

        agregarFila(panelForm, g, 0, "Nombres:", txtNombres);
        agregarFila(panelForm, g, 1, "Apellidos:", txtApellidos);
        agregarFila(panelForm, g, 2, "Repositorio (URL GitHub):", txtRepositorio);

        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTipo.add(rbNuevo);
        panelTipo.add(rbAntiguo);
        agregarFila(panelForm, g, 3, "Tipo de estudiante:", panelTipo);
        agregarFila(panelForm, g, 4, "Proyecto educativo:", cmbProyecto);

        // ----- Panel de botones -----
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnConsultar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelForm, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // ----- Tabla de registros -----
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(280);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Integrantes registrados"));
        scroll.setPreferredSize(new Dimension(720, 200));

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pack();
        setLocationRelativeTo(null);
    }
    private void agregarFila(JPanel panel, GridBagConstraints g, int fila, String texto, java.awt.Component campo) {
        g.gridx = 0;
        g.gridy = fila;
        g.fill = GridBagConstraints.NONE;
        g.weightx = 0;
        panel.add(new JLabel(texto), g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;
        panel.add(campo, g);
    }
    // ---------- Lectura de valores del formulario ----------
    public String getNombres() {
        return txtNombres.getText().trim();
    }
    public String getApellidos() {
        return txtApellidos.getText().trim();
    }
    public String getRepositorio() {
        return txtRepositorio.getText().trim();
    }
    public String getTipoEstudiante() {
        return rbNuevo.isSelected() ? "Nuevo" : "Antiguo";
    }
    public String getProyectoEducativo() {
        return (String) cmbProyecto.getSelectedItem();
    }
    // ---------- Acciones visuales ----------
    public void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtRepositorio.setText("");
        rbNuevo.setSelected(true);
        cmbProyecto.setSelectedIndex(0);
        txtNombres.requestFocusInWindow();
    }
    public void mostrarRegistros(List<Integrante> integrantes) {
        modeloTabla.setRowCount(0);
        for (Integrante i : integrantes) {
            modeloTabla.addRow(new Object[]{
                i.getNombres(), i.getApellidos(), i.getRepositorio(),
                i.getTipoEstudiante(), i.getProyectoEducativo()
            });
        }
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
    }
    // ---------- Conexion de eventos (el controlador los atiende) ----------
    public void addRegistrarListener(ActionListener l) {
        btnRegistrar.addActionListener(l);
    }
    public void addLimpiarListener(ActionListener l) {
        btnLimpiar.addActionListener(l);
    }
    public void addConsultarListener(ActionListener l) {
        btnConsultar.addActionListener(l);
    }
}