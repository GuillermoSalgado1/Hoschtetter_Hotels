package org.example.hoschtetter_hotels;

public class ReservationData {

    private final String fechaInicio;
    private final String fechaTermino;
    private final int precioPorNoche;
    private final String nombreHabitacion;
    private final String habitacionId;
    private final long dias;

    public ReservationData(String fechaInicio, String fechaTermino, int precioPorNoche, String nombreHabitacion, String habitacionId, long dias) {
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.precioPorNoche = precioPorNoche;
        this.nombreHabitacion = nombreHabitacion;
        this.habitacionId = habitacionId;
        this.dias = dias;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public int getPrecioPorNoche() {
        return precioPorNoche;
    }

    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    public String getHabitacionId() {
        return habitacionId;
    }

    public long getDias() {
        return dias;
    }
}