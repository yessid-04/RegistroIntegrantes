package model;
/**
 * MODELO: representa a un integrante que participa en un proyecto educativo.
 * Solo contiene datos (atributos encapsulados) y su representacion en texto.
 * No conoce nada de Swing ni de las validaciones.
 */
public class Integrante {
    private String nombres;
    private String apellidos;
    private String repositorio;
    private String tipoEstudiante;
    private String proyectoEducativo;

    public Integrante(String nombres, String apellidos, String repositorio, String tipoEstudiante, String proyectoEducativo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.repositorio = repositorio;
        this.tipoEstudiante = tipoEstudiante;
        this.proyectoEducativo = proyectoEducativo;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getRepositorio() {
        return repositorio;
    }
    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }
    public String getTipoEstudiante() {
        return tipoEstudiante;
    }
    public void setTipoEstudiante(String tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }
    public String getProyectoEducativo() {
        return proyectoEducativo;
    }
    public void setProyectoEducativo(String proyectoEducativo) {
        this.proyectoEducativo = proyectoEducativo;
    }
    @Override
    public String toString() {
        return "Integrante{nombres='" + nombres + "', apellidos='" + apellidos
                + "', repositorio='" + repositorio + "', tipoEstudiante='" + tipoEstudiante
                + "', proyectoEducativo='" + proyectoEducativo + "'}";
    }
}
