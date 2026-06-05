package ies.dam.inmobiliaria.model;

public class Vivienda {

    private int id;
    private String tipoOperacion;
    private String direccion;
    private String ciudad;
    private int habitaciones;
    private double metros;
    private double precio;
    private boolean disponible;

    public Vivienda(int id, String tipoOperacion, String direccion, String ciudad,
                    int habitaciones, double metros, double precio, boolean disponible) {
        this.id = id;
        this.tipoOperacion = tipoOperacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
        this.metros = metros;
        this.precio = precio;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public double getMetros() {
        return metros;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getDisponibilidadTexto() {
        return disponible ? "Disponible" : "No disponible";
    }

    public String getPrecioTexto() {
        if ("ALQUILER".equalsIgnoreCase(tipoOperacion)) {
            return String.format("%.2f €/mes", precio);
        }
        return String.format("%.2f €", precio);
    }
}
