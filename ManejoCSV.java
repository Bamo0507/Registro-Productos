//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 13/10/2023
//Última modificación: 13/10/2023

//Librerís a utilizar
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class ManejoCSV {
    //Método para leer la información del CSV y obtener todos los productos que se tienen
    public ArrayList<Producto> leerCSV(String archivo){
        ArrayList<Producto> productos = new ArrayList<>();
        //Se intenta leer el archivo
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            br.readLine(); //Se salta el encabezado
            String linea;
            //Comenzamos a leer todas las líneas del CSV
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(",");
                if(datos.length >= 7){
                    //Recopilamos la información
                    int ID = Integer.parseInt(datos[0].trim());
                    String nombre = datos[1].trim();
                    int cantDisponible = Integer.parseInt(datos[2].trim());
                    int cantVendidos = Integer.parseInt(datos[3].trim());
                    String estado = datos[4].trim();
                    float precio = Float.parseFloat(datos[5].trim());
                    String categoria = datos[6].trim();
                    //Dependiendo del producto se genera el objeto correspondiente
                    if(categoria.equals("Bebida")){
                        int mililitros = Integer.parseInt(datos[7].trim());
                        String tipo = datos[8].trim();
                        productos.add(new Bebida(categoria, ID, nombre, cantDisponible, cantVendidos, precio, mililitros, tipo));
                    } else if(categoria.equals("Snack")){
                        int gramos = Integer.parseInt(datos[9].trim());
                        String sabor = datos[10].trim();
                        String tamano = datos[11].trim();
                        productos.add(new Snack(categoria, ID, nombre, cantDisponible, cantVendidos, precio, gramos, sabor, tamano));
                    } else if(categoria.equals("Postre")){
                        String sabor = datos[10].trim();
                        String tamano = datos[11].trim();
                        productos.add(new Postre(categoria, ID, nombre, cantDisponible, cantVendidos, precio, sabor, tamano));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("No se ha encontraodo el archivo que proporcionaste :(");
        }
        return productos;
    }

    //Método para agregar nuevos productos en el CSV
    public void agregarProductos(String archivo, ArrayList<Producto> productos){
        String estado = "";
        boolean valid = true;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int ID = 0;
        //Se agrega un número aleatorio para el ID y se verificará que no se repita con el ID de algún otro producto
        do {
            valid = true;
            ID = rand.nextInt(90000) + 10000;
            for (Producto producto : productos) {
                if (producto.getId() == ID) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        valid = true;
        //Solicitamos información en común de todas las categorías
        System.out.println("¿Cuál es el nombre del producto?");
        String nombre = sc.nextLine();
        System.out.println("¿Cuántos productos te quedan disponibles?");
        int cantDisponibles = obtenerEnteroValido(sc);
        System.out.println("¿Cuántas ventas de este producto has tenido?");
        int cantVendidos = obtenerEnteroValido(sc);
        if(cantDisponibles <= 0){
            estado = "No disponible";
        } else{
            estado = "Disponible";
        }
        System.out.println("¿Cuál es el precio de este producto?");
        float  precio = obtenerFloatValido(sc);
        //Inicializamos el String Builder, esto nos permitirá construir la información de una fila
        StringBuilder lineaCSV = new StringBuilder();
        //Agregamos la información que ya tenemos a la fila
        lineaCSV.append(ID).append(",").append(nombre).append(",").append(cantDisponibles).append(",").append(cantVendidos).append(",").append(estado).append(",").append(precio).append(",");
        int selecciontipo = 0;
        String categoria = "";
        //Ciclo para generar un producto del tipo seleccionado
        while(!(selecciontipo >= 1 && selecciontipo <= 3)){
            System.out.println(tipoProducto());
            selecciontipo = obtenerEnteroValido(sc);
            switch(selecciontipo){
                case 1:
                    //Establecemos la categoría y solicitamos información especifica
                    selecciontipo = 0;
                    categoria = "Bebida";
                    String tipoBebida ="";
                    System.out.println("¿Cuantos militros tiene la bebida?");
                    int mililitros = obtenerEnteroValido(sc);
                    //Nos aseguramos que el tipo sea uno válido
                    while(!(selecciontipo >= 1 && selecciontipo <= 4)){
                        System.out.println("¿Qué tipo de bebida es?");
                        System.out.println(tipoBebida());
                        selecciontipo = obtenerEnteroValido(sc);
                        switch(selecciontipo){
                            case 1:
                                tipoBebida = "Energética";
                                break;
                            case 2:
                                tipoBebida = "Alcohólica";
                                break;
                            case 3:
                                tipoBebida = "Natural";
                                break;
                            case 4:
                                tipoBebida = "Agua pura";
                                break;
                            default:
                                System.out.println("Por favor, selecciona algo válido.");
                                break;
                        }
                    }
                    //Se termina de construir la fila
                    lineaCSV.append(categoria).append(",").append(mililitros).append(",").append(tipoBebida);
                    break;
                
                case 2:
                    //Esteblecemos la categoría y solicitamos la información especifica de un Snack
                    selecciontipo  = 0;
                    String sabor = "";
                    String tamano = "";
                    categoria = "Snack";
                    System.out.println("¿Cuántos gramos tiene el snack?");
                    int gramos = obtenerEnteroValido(sc);
                    //Verificamos que el sabor sea un tipo específico
                    while(!(selecciontipo >= 1 && selecciontipo <= 3)){
                        System.out.println("¿De qué sabor es?");
                        System.out.println(decidirSabor(categoria));
                        selecciontipo = obtenerEnteroValido(sc);
                        switch(selecciontipo){
                            case 1:
                                sabor = "Barbacoa";
                                break;
                            case 2:
                                sabor = "Picante";
                                break;
                            case 3:
                                sabor = "Natural";
                                break;
                            default:
                                System.out.println("Por favor, ingresa algo válido...");
                                break;
                        }
                    }
                    selecciontipo = 0;
                    //Verificamos que el tamaño sea uno válido
                    while(!(selecciontipo >= 1 && selecciontipo <= 2)){
                        System.out.println("¿De qué tamaño es?");
                        System.out.println(decidirTamano());
                        selecciontipo = obtenerEnteroValido(sc);
                        switch(selecciontipo){
                            case 1:
                                tamano = "Individual";
                                break;
                            case 2:
                                tamano = "Familiar";
                                break;
                            default:
                                System.out.println("Por favor, selecciona una opción válida.");
                                break;
                        }
                    }
                    //Se termina de construir la fila
                    lineaCSV.append(categoria).append(",").append(" ").append(",").append(" ").append(",").append(gramos).append(",").append(sabor).append(",").append(tamano);
                    break;
                
                case 3:
                    //Establecemos la categoría, y solicitamos la información específica
                    selecciontipo = 0;
                    String sabor1 = "";
                    String tamano1 = "";
                    categoria = "Postre";
                    //Aseguramos de que el sabor sea uno disponible
                    while(!(selecciontipo >=  1 && selecciontipo <= 2)){
                        System.out.println("¿De qué sabor es el postre?");
                        System.out.println(decidirSabor(categoria));
                        selecciontipo = obtenerEnteroValido(sc);
                        switch(selecciontipo){
                            case 1:
                                sabor1 = "Chocolate";
                                break;
                            case 2:
                                sabor1 = "Vainilla";
                                break;
                            default:
                                System.out.println("Selecciona una opción válida");
                                break;
                        }
                    }
                    selecciontipo = 0;
                    //Aseguramos que el tamaño sea válido
                    while(!(selecciontipo >= 1 && selecciontipo <= 2)){
                        System.out.println("¿De qué tamaño es?");
                        System.out.println(decidirTamano());
                        selecciontipo = obtenerEnteroValido(sc);
                        switch(selecciontipo){
                            case 1:
                                tamano1 = "Individual";
                                break;
                            case 2:
                                tamano1 = "Familiar";
                                break;
                            default:
                                System.out.println("Por favor, selecciona una opción válida.");
                                break;
                        }
                    }
                    //Terminamos de generar la fila
                    lineaCSV.append(categoria).append(",").append(" ").append(",").append(" ").append(",").append(" ").append(",").append(sabor1).append(",").append(tamano1);
                    break;

                default:
                    //Mensaje a mostrar si no se muestra algo malo
                    System.out.println("Por favor, seleccione una categoría válida...");
                    break;
            }
        }    
        //Tratamos de escribir sobre el archivo
        try(BufferedWriter w = new BufferedWriter(new FileWriter(archivo, true))){
            w.write(lineaCSV.toString());
            w.newLine();
        } catch(IOException e) { //Mensaje a mostrar si no logramos escribir sobre el CSV
            System.out.println("No se ha logrado agregar el jugador.");
            e.printStackTrace();
        }
    }

    //Menú para la selección de la categoría
    public String tipoProducto(){
        return "1. Bebida\n" + "2. Snack\n" + "3. Postre\n";
    }

    //Menú para los tipos de bebida
    public String tipoBebida(){
        return "1. Energética\n" + "2. Alcohólica\n" + "3. Natural\n" + "4. Agua pura\n";
    }

    //Menú para los sabores disponibles
    public String decidirSabor(String categoria){
        if(categoria.equals("Snack")){
            return "1. Barbacoa\n" + "2. Picante\n" + "3. Natural\n";
        } else if(categoria.equals("Postre")){
            return "1. Chocolate\n" + "2. Vainilla\n";
        }
        return "";
    }

    //Menú para los tamaños
    public String decidirTamano(){
        return "1. Individual\n" + "2. Familiar\n";
    }

    //Método para asegurarse que se ingrese un entero en los campos necesarios
    public static int obtenerEnteroValido(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número entero: ");
                String entrada = scanner.nextLine();
                numero = Integer.parseInt(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
            }
        } while (!entradaValida);

        return numero;
    }
    //Método para asegurarse que se ingrese un float en los campos necesarios
    public static float obtenerFloatValido(Scanner scanner) {
        float numero = 0.0f;  // Inicializado a 0.0f
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número decimal: ");
                String entrada = scanner.nextLine();
                numero = Float.parseFloat(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número decimal.");
            }
        } while (!entradaValida);
    
        return numero;
    }    

}
