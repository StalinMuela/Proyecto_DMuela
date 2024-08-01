package Clases;

/**
 * La clase {@code Aula_Lab} representa un aula y un laboratorio con sus atributos
 */
public class Aula_Lab {

    String codigoAula;
    String nombreAula;
    int capacidadAula;
    Boolean reservarAula;
    String codigoLab;
    String nombreLab;
    int capacidadLab;
    /**
     * Estado de reserva del laboratorio.
     * {@code true} si el laboratorio está reservado, {@code false} si no está reservado.
     */
    Boolean reservarLab;
    /**
     * Crea una nueva instancia de {@code Aula_Lab} con valores por defecto.
     */
    public Aula_Lab() {
    }
    /**
     * Crea una nueva instancia de {@code Aula_Lab} con los valores especificados.
     *
     * @param codigoAula el código del aula
     * @param nombreAula el nombre del aula
     * @param capacidadAula la capacidad del aula
     * @param reservarAula el estado de reserva del aula
     * @param codigoLab el código del laboratorio
     * @param nombreLab el nombre del laboratorio
     * @param capacidadLab la capacidad del laboratorio
     * @param reservarLab el estado de reserva del laboratorio
     */
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

    /**
     * Obtiene el código del aula.
     * @return el código del aula
     */
    public String getCodigoAula() {
        return codigoAula;
    }

    /**
     * Establece el código del aula.
     * @param codigoAula el nuevo código del aula
     */
    public void setCodigoAula(String codigoAula) {
        this.codigoAula = codigoAula;
    }

    /**
     * Obtiene el nombre del aula.
     * @return el nombre del aula
     */
    public String getNombreAula() {
        return nombreAula;
    }
    /**
     * Establece el nombre del aula.
     * @param nombreAula el nuevo nombre del aula
     */
    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }
    /**
     * Obtiene la capacidad del aula.
     * @return la capacidad del aula
     */
    public int getCapacidadAula() {
        return capacidadAula;
    }
    /**
     * Establece la capacidad del aula.
     * @param capacidadAula la nueva capacidad del aula
     */
    public void setCapacidadAula(int capacidadAula) {
        this.capacidadAula = capacidadAula;
    }
    /**
     * Obtiene el estado de reserva del aula.
     * @return {@code true} si el aula está reservada, {@code false} si no está reservada
     */
    public Boolean getReservarAula() {
        return reservarAula;
    }
    /**
     * Establece el estado de reserva del aula.
     * @param reservarAula el nuevo estado de reserva del aula
     */
    public void setReservarAula(Boolean reservarAula) {
        this.reservarAula = reservarAula;
    }
    /**
     * Obtiene el código del laboratorio.
     * @return el código del laboratorio
     */
    public String getCodigoLab() {
        return codigoLab;
    }

    /**
     * Establece el código del laboratorio.
     * @param codigoLab el nuevo código del laboratorio
     */
    public void setCodigoLab(String codigoLab) {
        this.codigoLab = codigoLab;
    }
    /**
     * Obtiene el nombre del laboratorio.
     * @return el nombre del laboratorio
     */
    public String getNombreLab() {
        return nombreLab;
    }
    /**
     * Establece el nombre del laboratorio.
     * @param nombreLab el nuevo nombre del laboratorio
     */
    public void setNombreLab(String nombreLab) {
        this.nombreLab = nombreLab;
    }
    /**
     * Obtiene la capacidad del laboratorio.
     * @return la capacidad del laboratorio
     */
    public int getCapacidadLab() {
        return capacidadLab;
    }
    /**
     * Establece la capacidad del laboratorio.
     * @param capacidadLab la nueva capacidad del laboratorio
     */
    public void setCapacidadLab(int capacidadLab) {
        this.capacidadLab = capacidadLab;
    }
    /**
     * Obtiene el estado de reserva del laboratorio.
     * @return {@code true} si el laboratorio está reservado, {@code false} si no está reservado
     */
    public Boolean getReservarLab() {
        return reservarLab;
    }
    /**
     * Establece el estado de reserva del laboratorio.
     * @param reservarLab el nuevo estado de reserva del laboratorio
     */
    public void setReservarLab(Boolean reservarLab) {
        this.reservarLab = reservarLab;
    }
}
