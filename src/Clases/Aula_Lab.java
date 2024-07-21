package Clases;

public class Aula_Lab {
    String codigoAula;
    String nombreAula;
    int capacidadAula;
    Boolean reservarAula;
    String codigoLab;
    String nombreLab;
    int capacidadLab;
    Boolean reservarLab;

    public Aula_Lab() {
    }

    public Aula_Lab(String codigoAula, String nombreAula, int capacidadAula, Boolean reservarAula, String codigoLab, String nombreLab, int capacidadLab, Boolean reservarLab) {
        this.codigoAula = codigoAula;
        this.nombreAula = nombreAula;
        this.capacidadAula = capacidadAula;
        this.reservarAula = reservarAula;
        this.codigoLab = codigoLab;
        this.nombreLab = nombreLab;
        this.capacidadLab = capacidadLab;
        this.reservarLab = reservarLab;
    }

    public String getCodigoAula() {
        return codigoAula;
    }

    public void setCodigoAula(String codigoAula) {
        this.codigoAula = codigoAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public int getCapacidadAula() {
        return capacidadAula;
    }

    public void setCapacidadAula(int capacidadAula) {
        this.capacidadAula = capacidadAula;
    }

    public Boolean getReservarAula() {
        return reservarAula;
    }

    public void setReservarAula(Boolean reservarAula) {
        this.reservarAula = reservarAula;
    }

    public String getCodigoLab() {
        return codigoLab;
    }

    public void setCodigoLab(String codigoLab) {
        this.codigoLab = codigoLab;
    }

    public String getNombreLab() {
        return nombreLab;
    }

    public void setNombreLab(String nombreLab) {
        this.nombreLab = nombreLab;
    }

    public int getCapacidadLab() {
        return capacidadLab;
    }

    public void setCapacidadLab(int capacidadLab) {
        this.capacidadLab = capacidadLab;
    }

    public Boolean getReservarLab() {
        return reservarLab;
    }

    public void setReservarLab(Boolean reservarLab) {
        this.reservarLab = reservarLab;
    }
}