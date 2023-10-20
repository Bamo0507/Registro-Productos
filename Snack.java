//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 16/10/2023
//Última modificación: 16/10/2023

public class Snack extends Producto {
    //Atributos únicos de un Snack
    private int gramos;
    private String sabor;
    private String tamano;
    //Constructor de un Snack
    public Snack(String categoria, int id, String nombre, int cantDisponible, int cantVendidos, float precio,
            int gramos, String sabor, String tamano) {
        super(categoria, id, nombre, cantDisponible, cantVendidos, precio);
        this.gramos = gramos;
        this.sabor = sabor;
        this.tamano = tamano;
    }
}
