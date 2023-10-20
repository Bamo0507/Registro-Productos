//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 16/10/2023
//Última modificación: 16/10/2023

public abstract class Producto{
    //Atributos compartidos por todas las categorías
    protected int id;
    protected String nombre;
    protected int cantDisponible;
    protected int cantVendidos;
    protected String estado;
    protected float precio;
    protected String categoria;

    //Constructor común para todos los tipos de productos
    public Producto(String categoria, int id, String nombre, int cantDisponible, int cantVendidos, float precio){
        this.categoria = categoria;
        this.id = id;
        this.nombre = nombre;
        this.cantDisponible = cantDisponible;
        this.cantVendidos = cantVendidos;
        this.precio = precio;
        if(cantDisponible > 0){
            this.estado = "DISPONIBLE";
        } else {
            this.estado = "NO DISPONIBLE";
        }
    }
    
    //Getters de los atributos
    public int getId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public int getCantVendidos() {
        return cantVendidos;
    }

    public float getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    //To String de todos los productos en existencia
    public String toString() {
        return "=== " + id + ": " + nombre + " ===";
    }
}