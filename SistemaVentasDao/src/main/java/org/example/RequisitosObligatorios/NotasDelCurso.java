package org.example.RequisitosObligatorios;

import java.util.Scanner;

public class NotasDelCurso {

    public static void calcularMedia(double[] notas){
        double suma = 0.0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        double media = suma / notas.length;
        System.out.println("La media de las notas es: " + String.format("%.2f", media));
    }

    public static void calcularNotaMasAlta(double[] notas){
        double notaMasAlta = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > notaMasAlta) {
                notaMasAlta = notas[i];
            }
        }
        System.out.println("La nota mÃ¡s alta es: " + String.format("%.2f", notaMasAlta));
    }
    public static void calcularNotaMasBaja(double[] notas){
        double notaMasBaja = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < notaMasBaja) {
                notaMasBaja = notas[i];
            }
        }
        System.out.println("La nota mÃ¡s baja es: " + String.format("%.2f", notaMasBaja));
    }

    public static void calcularCantidadDeAprobados(double[] notas){
        int cantidadDeAprobados = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] >= 6.0) {
                cantidadDeAprobados++;
            }
        }
        System.out.println("La cantidad de aprobados es: " + cantidadDeAprobados);
    }

    public static void calcularCantidadDeDesaprobados(double[] notas){
        int cantidadDeDesaprobados = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 6.0) {
                cantidadDeDesaprobados++;
            }
        }
        System.out.println("La cantidad de desaprobados es: " + cantidadDeDesaprobados);
    }

    public static void mostrarMenu(){
        System.out.println();
        System.out.println("--- MENÃš - Requisitos obligatorios ---");
        System.out.println("1. Registrar 10 notas");
        System.out.println("2. Mostrar todas las notas");
        System.out.println("3. Calcular media");
        System.out.println("4. Mostrar nota mÃ¡s alta");
        System.out.println("5. Mostrar nota mÃ¡s baja");
        System.out.println("6. Contar aprobados");
        System.out.println("7. Contar suspensos");
        System.out.println("8. Buscar nota por posiciÃ³n");
        System.out.println("9. Ordenar notas de mayor a menor");
        System.out.println("10. Salir");
        System.out.print("Elige una opciÃ³n (1-10): ");
    }

    // Lee un entero con validaciÃ³n de rango
    public static int leerEntero(Scanner sc, String mensaje, int min, int max){
        while(true){
            System.out.print(mensaje);
            String linea = sc.nextLine();
            try{
                int valor = Integer.parseInt(linea.trim());
                if(valor < min || valor > max){
                    System.out.println("Por favor introduce un nÃºmero entre " + min + " y " + max + ".");
                    continue;
                }
                return valor;
            }catch(NumberFormatException e){
                System.out.println("Entrada no vÃ¡lida. Introduce un nÃºmero entero.");
            }
        }
    }

    // Lee un double con validaciÃ³n de rango y permite decimales
    public static double leerDouble(Scanner sc, String mensaje, double min, double max){
        while(true){
            System.out.print(mensaje);
            String linea = sc.nextLine();
            try{
                double valor = Double.parseDouble(linea.trim());
                if(valor < min || valor > max){
                    System.out.println("Por favor introduce un nÃºmero entre " + min + " y " + max + ".");
                    continue;
                }
                return valor;
            }catch(NumberFormatException e){
                System.out.println("Entrada no vÃ¡lida. Introduce un nÃºmero (puede tener decimales).");
            }
        }
    }

    // Registrar 10 notas en el array de 0 a 10 permitiendo decimales
    public static void registrarNotas(double[] notas, Scanner sc){
        System.out.println("Introduce 10 notas (0.00 - 10.00):");
        for(int i = 0; i < notas.length; i++){
            double n = leerDouble(sc, "Nota " + (i+1) + ": ", 0.0, 10.0);
            notas[i] = n;
        }
        System.out.println("Notas registradas correctamente.");
    }

    public static void mostrarNotas(double[] notas){
        System.out.println("Listado de notas:");
        for(int i = 0; i < notas.length; i++){
            System.out.println("PosiciÃ³n " + (i+1) + ": " + String.format("%.2f", notas[i]));
        }
    }

    public static void buscarNotaPorPosicion(double[] notas, int posicion){
        if(posicion < 1 || posicion > notas.length){
            System.out.println("PosiciÃ³n fuera de rango. Debe estar entre 1 y " + notas.length + ".");
            return;
        }
        System.out.println("La nota en la posiciÃ³n " + posicion + " es: " + String.format("%.2f", notas[posicion-1]));
    }

    public static void ordenarDescendente(double[] notas){
        // simple ordenaciÃ³n por burbuja o selecciÃ³n (in-place)
        for(int i = 0; i < notas.length - 1; i++){
            for(int j = i + 1; j < notas.length; j++){
                if(notas[j] > notas[i]){
                    double tmp = notas[i];
                    notas[i] = notas[j];
                    notas[j] = tmp;
                }
            }
        }
        System.out.println("Notas ordenadas de mayor a menor.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] notas = new double[10];
        boolean notasCargadas = false;

        while(true){
            mostrarMenu();
            int opcion = leerEntero(sc, "", 1, 10);
            System.out.println();
            switch(opcion){
                case 1:
                    registrarNotas(notas, sc);
                    notasCargadas = true;
                    break;
                case 2:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else mostrarNotas(notas);
                    break;
                case 3:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else calcularMedia(notas);
                    break;
                case 4:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else calcularNotaMasAlta(notas);
                    break;
                case 5:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else calcularNotaMasBaja(notas);
                    break;
                case 6:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else calcularCantidadDeAprobados(notas);
                    break;
                case 7:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else calcularCantidadDeDesaprobados(notas);
                    break;
                case 8:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else {
                        int pos = leerEntero(sc, "Introduce la posiciÃ³n (1-" + notas.length + "): ", 1, notas.length);
                        buscarNotaPorPosicion(notas, pos);
                    }
                    break;
                case 9:
                    if(!notasCargadas) System.out.println("Primero registra las notas (opciÃ³n 1).\n");
                    else {
                        ordenarDescendente(notas);
                        mostrarNotas(notas);
                    }
                    break;
                case 10:
                    System.out.println("Saliendo. Hasta luego.");
                    sc.close();
                    return;
                default:
                    System.out.println("OpciÃ³n no vÃ¡lida.");
            }
        }
    }

}


