//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 16/10/2023
//Última modificación: 16/10/2023
import java.util.ArrayList;
import java.util.Scanner;

public class Emprendimiento {
    //Variables a emplear para el analísis
    private static ManejoCSV manejoCSV = new ManejoCSV();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static String seleccion;
    private static boolean systemON = true;
    private static boolean systemON2 = true;
    private static int cuenta1;
    private static int cuenta2;
    private static int cuenta3;
    private static float ventas = 0;
    private static float ventas2 = 0;

    public static void main(String[] args){
        //Se cargan una vez los datos desde el inicio
        productos = manejoCSV.leerCSV("RegistroProductos.csv");
        //Mensaje de bienvenida
        System.out.println("***********************************************************");
        System.out.println("*  __        _______ _     ____ ___  __  __ _____ _ _ _   *");
        System.out.println("*  \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|_|_|  *");
        System.out.println("*     \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_|_|_)  *");
        System.out.println("***********************************************************");
        System.out.println("\nBuenos días querido usuario ;)\n" + "¿Qué deseas hacer el día de hoy?\n");
        
        while(systemON){
            System.out.println(menuPrincipal());
            switch(seleccion = sc.nextLine()){
                //Se agregan productos y se actualiza el ArrayList de productos
                case "1":
                    manejoCSV.agregarProductos("RegistroProductos.csv", productos);
                    productos = manejoCSV.leerCSV("RegistroProductos.csv");
                    break;
                case "2":
                    System.out.println();
                    systemON2 = true;
                    while(systemON2){
                        System.out.println(menuInfoProductos());
                        switch(seleccion = sc.nextLine()){
                            //Mostrar un producto con el ID
                            case "1":
                                System.out.println("Proporcione el ID del producto que quiere conocer: ");
                                int ID = manejoCSV.obtenerEnteroValido(sc);
                                boolean encontrado = false;
                                for (Producto p : productos) {
                                    if (p.getId() == ID) {
                                        System.out.println(p);
                                        encontrado = true;
                                    }
                                }
                                if (!encontrado) {
                                    System.out.println("No se ha encontrado el ID dado...");
                                }
                                System.out.println();
                                break;
                            
                            //Mostrar productos de una categoría
                            case "2":
                                int selecciontipo = 0;
                                while(!(selecciontipo >= 1 && selecciontipo <= 3)){
                                    System.out.println("¿De qué categoría quiere conocer información?");
                                    System.out.println(manejoCSV.tipoProducto());
                                    switch(selecciontipo = manejoCSV.obtenerEnteroValido(sc)){
                                        //Productos de tipo Bebida
                                        case 1:
                                            for(Producto p: productos){
                                                if(p instanceof Bebida){
                                                    System.out.println(p);
                                                }
                                            }
                                            break;
                                        case 2:
                                            for(Producto p: productos){
                                                if(p instanceof Snack){
                                                    System.out.println(p);
                                                }
                                            }
                                            break;
                                        case 3:
                                            for(Producto p: productos){
                                                if(p instanceof Postre){
                                                    System.out.println(p);
                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println("Por favor, seleccione una categoría válida.\n");
                                    }
                                } 
                                System.out.println();
                                break;
                            
                            //Conocer ventas actuales y comisión a pagar
                            case "3":
                                ventas = 0;
                                ventas2 = 0;
                                for(Producto p: productos){
                                    ventas += (p.getCantVendidos() * p.getPrecio());
                                    if (p instanceof Postre){
                                        ventas2 += 0.20*(p.getCantVendidos() * p.getPrecio());
                                    }
                                }  
                                System.out.println("------------------------------------------");
                                System.out.println("Actualmente se han generado Q" + ventas + " en ventas :))");
                                System.out.println("-------------------");
                                System.out.println("Se deben Q" + ventas2 + " en comisión ;)");
                                System.out.println("------------------------------------------");
                                System.out.println();
                                break;
                            
                            //Informe general de productos
                            case "4":
                                cuenta1 = 0;
                                cuenta2 = 0;
                                cuenta3 = 0;
                                ventas = 0;
                                ventas2 =0;
                                //StringBuilders para ir construyendo un String que contenga el nombre de todos los productos por categoría
                                StringBuilder bebidas = new StringBuilder();
                                StringBuilder snacks = new StringBuilder();
                                StringBuilder postres = new StringBuilder();
                                //Recorre el ArrayList completo para recopilar toda la información que se mostrará
                                for(Producto p: productos){
                                    ventas += (p.getCantVendidos() * p.getPrecio());
                                    if(p instanceof Bebida){
                                        cuenta1++;
                                        bebidas.append(p.getNombre()).append("\n");
                                    } else if(p instanceof Snack){
                                        cuenta2++;
                                        snacks.append(p.getNombre()).append("\n");
                                    } else if(p instanceof Postre){
                                        cuenta3++;
                                        ventas2 += 0.20*(p.getCantVendidos() * p.getPrecio());
                                        postres.append(p.getNombre()).append("\n");
                                    }
                                }
                                //Impresión del informe completo
                                System.out.println("------------------------------------------------------");
                                System.out.println("1. Listado de categorías con total de productos: ");
                                System.out.println();
                                System.out.println("Bebidas - " + cuenta1 + "\nSnacks - " + cuenta2 + "\nPostres - " + cuenta3);
                                System.out.println();
                                System.out.println("2. Listado de productos por categoria: ");
                                System.out.println();
                                System.out.println("-----------");
                                System.out.println("Bebidas: ");
                                System.out.println(bebidas.toString());
                                System.out.println("-----------");
                                System.out.println("Snacks: ");
                                System.out.println(snacks.toString());
                                System.out.println("-----------");
                                System.out.println("Postres: ");
                                System.out.println("-----------");
                                System.out.println(postres.toString());
                                System.out.println("3. Total de ventas: ");
                                System.out.println("-------------------------------------------");
                                System.out.println("Actualmente se han generado Q" + ventas + " en ventas :))");
                                System.out.println("-------------------");
                                System.out.println("Se deben Q" + ventas2 + " en comisión ;)");
                                System.out.println("-------------------------------------------");
                                System.out.println("------------------------------------------------------");
                                System.out.println();
                                break;   
                            
                            //Regresar al menú principal
                            case "5":
                                systemON2 = false;
                                System.out.println("Regresando al menú principal.....");
                                break;
                            
                            default:
                                System.out.println("Por favor, ingresa algo válido.");
                                break;
                        }
                    }
                    break;
                //Salimos de todo el programa
                case "3":
                    System.out.println("Que tenga un buen día :)");
                    systemON = false;
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida...");
                    break;
            }
        }
      

    }
    //Display de menú principal
    public static String menuPrincipal(){
        return "1. Agregar un nuevo producto\n" + "2. Acceso a menú de estadísticas\n" + "3. Salir";
    }
    //Display de menú secundario
    public static String menuInfoProductos(){
        return "1. Encontrar un producto dado un id.\n" + "2. Lista de productos acorde a una categoría.\n" + "3. Conocer total de ventas actuales y comisión a pagar.\n" + "4. Reporte completo\n" + "5. Salir";
    }
}


