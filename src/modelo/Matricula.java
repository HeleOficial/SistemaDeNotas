package modelo;

public class Matricula {
    private int codEstudiante;
    private int codCurso;
    private float notaCurso;

    public Matricula(int codEstudiante, int codCurso, float notaCurso) {
        this.codEstudiante = codEstudiante;
        this.codCurso = codCurso;
        this.notaCurso = notaCurso;
    }

    public int getCodEstudiante() {
        return codEstudiante;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public float getNotaCurso() {
        return notaCurso;
    }
}

