package modelo;

public class Docente {
    private int codDocente;
    private String nomDocente;

    public Docente(int codDocente, String nomDocente) {
        this.codDocente = codDocente;
        this.nomDocente = nomDocente;
    }

    public int getCodDocente() {
        return codDocente;
    }

    public String getNomDocente() {
        return nomDocente;
    }
}
