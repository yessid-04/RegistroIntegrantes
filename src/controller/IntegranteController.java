package controller;
import model.Integrante;
import view.FormularioIntegrante;
import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * CONTROLADOR: atiende los eventos de la vista, valida los datos,
 * crea objetos Integrante y los guarda en memoria (ArrayList).
 */
public class IntegranteController {
    // Formato basico de URL: http(s)://dominio.ext[/ruta]
    private static final Pattern PATRON_URL =
            Pattern.compile("^https?://[A-Za-z0-9.-]+\\.[A-Za-z]{2,}(/\\S*)?$");
    private final FormularioIntegrante vista;
    private final ArrayList<Integrante> integrantes = new ArrayList<>();
    
    public IntegranteController(FormularioIntegrante vista) {
        this.vista = vista;
        conectarEventos();
    }
    private void conectarEventos() {
        vista.addRegistrarListener(e -> registrar());
        vista.addLimpiarListener(e -> vista.limpiarFormulario());
        vista.addConsultarListener(e -> consultar());
    }
    /** Valida los datos y, si son correctos, crea y almacena el integrante. */
    private void registrar() {
        String error = validar();
        if (error != null) {
            vista.mostrarError(error);
            return;
        }
        Integrante nuevo = new Integrante(
                vista.getNombres(),
                vista.getApellidos(),
                vista.getRepositorio(),
                vista.getTipoEstudiante(),
                vista.getProyectoEducativo());
        integrantes.add(nuevo);
        vista.mostrarMensaje("Registro exitoso: " + nuevo.getNombres() + " " + nuevo.getApellidos());
        vista.limpiarFormulario();
        consultar();
    }
    /** Envia a la vista la lista de integrantes almacenados. */
    private void consultar() {
        vista.mostrarRegistros(integrantes);
    }
    /** Devuelve un mensaje de error, o null si todo es valido. */
    private String validar() {
        if (vista.getNombres().isEmpty()) {
            return "El campo Nombres es obligatorio.";
        }
        if (vista.getApellidos().isEmpty()) {
            return "El campo Apellidos es obligatorio.";
        }
        String url = vista.getRepositorio();
        if (url.isEmpty()) {
            return "El campo Repositorio es obligatorio.";
        }
        if (!PATRON_URL.matcher(url).matches()) {
            return "La URL del repositorio no tiene un formato válido.\nEjemplo: https://github.com/usuario/proyecto";
        }
        return null;
    }
}


