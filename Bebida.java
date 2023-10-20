//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 16/10/2023
//Última modificación: 16/10/2023

public class Bebida extends Producto{
    //Atributos únicos de una bebida
    private int mililitros;
    private String tipo;
    //Constructor de una bebida
    public Bebida(String categoria, int id, String nombre, int cantDisponible, int cantVendidos, float precio,
            int mililitros, String tipo) {
        super(categoria, id, nombre, cantDisponible, cantVendidos, precio);
        this.mililitros = mililitros;
        this.tipo = tipo;
    }

}
