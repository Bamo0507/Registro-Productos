//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 16/10/2023
//Última modificación: 16/10/2023

public class Postre extends Producto{
    //Atributos únicos de un postre
    private String sabor;
    private String tamano;
    //Constructor de un postre
    public Postre(String categoria, int id, String nombre, int cantDisponible, int cantVendidos, float precio,
            String sabor, String tamano) {
        super(categoria, id, nombre, cantDisponible, cantVendidos, precio);
        this.sabor = sabor;
        this.tamano = tamano;
    }
    
}
