package modelo;

public class Curso {
    private int codCurso;
    private String nomCurso;
    private int codDocente;

    public Curso(int codCurso, String nomCurso, int codDocente) {
        this.codCurso = codCurso;
        this.nomCurso = nomCurso;
        this.codDocente = codDocente;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public int getCodDocente() {
        return codDocente;
    }
}